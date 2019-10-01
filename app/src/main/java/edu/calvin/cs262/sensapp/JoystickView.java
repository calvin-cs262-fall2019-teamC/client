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

/*
 * JoystickView is from this tutorial: https://www.instructables.com/id/A-Simple-Android-UI-Joystick/
 */
public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRadius;

    public JoystickView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    private void setupDimensions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hatRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    private void drawJoystick(float newX, float newY) {
        if(getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();

            // Clear the Canvas before drawing
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            // Draw the base first before shading
            colors.setARGB(255, 50, 50, 50);  // light grey
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);

            // Draw the "hat" (the movable part)
            colors.setARGB(255, 0,255,157);  // TODO: change to colorAccent
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            myCanvas.drawCircle(newX, newY, hatRadius, colors);

            getHolder().unlockCanvasAndPost(myCanvas);  // Write the new drawing to the SurfaceView
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.equals(this)) {
            if(motionEvent.getAction() != MotionEvent.ACTION_UP) {
                float displacement = (float) Math.sqrt((Math.pow(motionEvent.getX() - centerX, 2)) + Math.pow(motionEvent.getY() - centerY, 2));
                if(displacement < baseRadius) {
                    drawJoystick(motionEvent.getX(), motionEvent.getY());
                } else {
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (motionEvent.getX() - centerX) * ratio;
                    float constrainedY = centerY + (motionEvent.getY() - centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                }

            }
            else {
                drawJoystick(centerX, centerY);
            }
        }
        return true;
    }
}
