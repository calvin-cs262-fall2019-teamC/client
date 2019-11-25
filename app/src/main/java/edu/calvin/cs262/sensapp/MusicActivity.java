package edu.calvin.cs262.sensapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * An Activity for playing various sounds
 */
public class MusicActivity extends AppCompatActivity {
    private Context context;
    private static final String SOUND_BUTTON_CLICKED = "sound button clicked";
    private LocalBroadcastManager localBroadcastManager;
    private Map<String, MediaPlayer> mediaPlayerMap = new HashMap<>();

    private final BroadcastReceiver appBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null & intent.getAction().equals(SOUND_BUTTON_CLICKED)) {
                String button_clicked = intent.getStringExtra("button_clicked");
                Log.d("MusicActivity", "button clicked: " + button_clicked);
                if (mediaPlayerMap.get(button_clicked) != null) {
                    MediaPlayer player = mediaPlayerMap.get(button_clicked);
                    if (player.isPlaying()) {
                        player.pause();
                    }
                    else {
                        player.start();
                    }
                }
            }
        }
    };

    /**
     * Create MusicActivity
     *
     * @param savedInstanceState Bundle to initialize
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        context = getApplicationContext();

        localBroadcastManager = LocalBroadcastManager.getInstance(context);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        buildTabs(tabLayout);
        buildPagerAdapter(tabLayout);

    }

    /**
     * Build the tabs for predefined sound categories
     *
     * @param tabs the TabLayout that holds tabs for each music category
     * @author cmd16
     */
    private void buildTabs(TabLayout tabs) {
        tabs.addTab(tabs.newTab().setText(R.string.animal_sounds_label));
        tabs.addTab(tabs.newTab().setText(R.string.nature_sounds_label));
        tabs.addTab(tabs.newTab().setText(R.string.water_sounds_label));
    }

    /**
     * Build the PagerAdapter so that we can actually use our TabLayout
     *
     * @param tabs the TabLayout that holds tabs for each music category
     */
    private void buildPagerAdapter(TabLayout tabs) {
        final ViewPager viewPager = findViewById(R.id.pager);
        final MusicPagerAdapter adapter = new MusicPagerAdapter(
                getSupportFragmentManager(), tabs.getTabCount(), context);
        viewPager.setAdapter(adapter);

        // Setting a listener for clicks.
        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //do nothing because a tab cannot actually be unselected here.
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //do nothing because re-selecting a tab is the same as selecting a tab, here.
            }
        });
    }

    public Map<String, MediaPlayer> getMediaPlayerMap() {
        return mediaPlayerMap;
    }

    /**
     * Register a receiver so the Activity knows to listen for the button clicked message
     */
    @Override
    protected void onResume() {
        super.onResume();
        localBroadcastManager.registerReceiver(appBroadcastReceiver, new IntentFilter(SOUND_BUTTON_CLICKED));
    }

    /**
     * Release MediaPlayer resources so we aren't using up resources and so the sound will stop
     */
    @Override
    protected void onPause() {
        super.onPause();
        localBroadcastManager.unregisterReceiver(appBroadcastReceiver);
        for (MediaPlayer player: mediaPlayerMap.values()) {
            player.release();
            Log.d("MusicActivity", "Released a MediaPlayer");
        }
    }
}
