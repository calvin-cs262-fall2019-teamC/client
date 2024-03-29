package edu.calvin.cs262.sensapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * A {@link SurfaceView} subclass for creating a joystick on a canvas that the user can move with touch gestures
 * Adapted from the tutorial at https://www.instructables.com/id/A-Simple-Android-UI-Joystick/
 */
public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private final int RATIO = 5; //The smaller, the more shading will occur
    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;

    /**
     * Create JoystickView
     *
     * @param context Current Context
     */
    public JoystickView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    /**
     * Create JoystickView
     *
     * @param context Current Context
     * @param attrs AttributeSet to initialize
     */
    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    /**
     * Create JoystickView
     *
     * @param context Current Context
     * @param attrs AttributeSet to initialize
     * @param defStyleAttr int of whether to def style attributes
     */
    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    /**
     * Setup appropriate dimensions for joystick window
     */
    private void setupDimensions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    /**
     * Draw the joystick at new position
     *
     * @param newX float of new X coordinate
     * @param newY float of new Y coordinate
     */
    private void drawJoystick(float newX, float newY) {
        if (getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();

            // Clear the Canvas before drawing
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR); // Clear the background

            //First determine the sin and cos of the angle that the touched point is at relative to the center of the joystick
            float hypotenuse = (float) Math.sqrt(Math.pow(newX - centerX, 2) + Math.pow(newY - centerY, 2));
            float sin = (newY - centerY) / hypotenuse; //sin = o/h
            float cos = (newX - centerX) / hypotenuse; //cos = a/h

            //Draw the base first before shading
            colors.setARGB(255, 100, 100, 100);
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            for (int i = 1; i <= (int) (baseRadius / RATIO); i++) {
                colors.setARGB(2 * (int) baseRadius / i, 0, 0, 255); //Gradually decrease the shade of black drawn to create a nice shading effect
                myCanvas.drawCircle(newX - cos * hypotenuse * (RATIO / baseRadius) * i,
                        newY - sin * hypotenuse * (RATIO / baseRadius) * i, i * (hatRadius * RATIO / baseRadius), colors); //Gradually increase the size of the shading effect
            }

            // Draw the "hat" (the movable part)
            for (int i = 1; i <= hatRadius / RATIO; i++) {
                // Gradually decrease the shade of black drawn to create a nice shading effect
                colors.setARGB(255, (int) (i * (255 * RATIO / hatRadius)), (int) (i * (255 * RATIO / hatRadius)), 255);
                // gradually decrease the drawing radius of the shading, until it reaches its minimum of 1/3 the radius of the joystick hat
                myCanvas.drawCircle(newX, newY, hatRadius - (float) i * (RATIO) / 3, colors);
            }

            getHolder().unlockCanvasAndPost(myCanvas);  // Write the new drawing to the SurfaceView
        }
    }

    /**
     * Create Surface for joystick
     *
     * @param holder SurfaceHolder
     */
    public void surfaceCreated(SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    /**
     * Empty for when surface changed
     *
     * @param holder SurfaceHolder
     * @param format int of surface format
     * @param width int of surface width
     * @param height int of surface height
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * Empty for when surface destroyed
     *
     * @param holder SurfaceHolder
     */
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    /**
     * On touching joystick animate
     *
     * @param view View touched
     * @param motionEvent MotionEvent received in touch
     * @return true
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.equals(this)) {
            if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                float displacement = (float) Math.sqrt((Math.pow(motionEvent.getX() - centerX, 2)) + Math.pow(motionEvent.getY() - centerY, 2));
                if (displacement < baseRadius) {
                    drawJoystick(motionEvent.getX(), motionEvent.getY());
                } else {
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (motionEvent.getX() - centerX) * ratio;
                    float constrainedY = centerY + (motionEvent.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                }

            } else {
                drawJoystick(centerX, centerY);
            }
        }
        return true;
    }
}
