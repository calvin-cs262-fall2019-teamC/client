package edu.calvin.cs262.sensapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * An Activity for playing various sounds
 */
public class MusicActivity extends AppCompatActivity {
    private Context context;
    private static final String SOUND_BUTTON_CLICKED = "sound button clicked";
    private LocalBroadcastManager localBroadcastManager;
    private Map<String, MediaPlayer> mediaPlayerMap;
    private ArrayList<MusicButtonData> musicButtonDataList;
    private MusicPagerAdapter adapter;

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

    // For creating History records once Activity is used for 5 or more seconds
    private HistoryManager hist_manager;

    /**
     * Create MusicActivity
     *
     * @param savedInstanceState Bundle to initialize
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hist_manager = new HistoryManager(getString(R.string.activity_six_title),
                getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        context = getApplicationContext();
        musicButtonDataList = MusicButtonFactory.getInstance().getDataList();
        mediaPlayerMap = new HashMap<>();
        for (MusicButtonData data: musicButtonDataList) {
            MediaPlayer player = MediaPlayer.create(context, data.getAudioID());
            player.setLooping(true);
            mediaPlayerMap.put(getString(data.getStringID()), player);
        }

        localBroadcastManager = LocalBroadcastManager.getInstance(context);
        localBroadcastManager.registerReceiver(appBroadcastReceiver, new IntentFilter(SOUND_BUTTON_CLICKED));

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
        adapter = new MusicPagerAdapter(
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

    /**
     * Pause MediaPlayers and reset the alphas of the drawables (so sounds are shown as not playing)
     * and create a History record of this activity if it was open for 5 or more seconds
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        hist_manager.createRecord();
        super.onPause();
        for (MediaPlayer player: mediaPlayerMap.values()) {
            if (player.isPlaying()) {
                player.pause();
                Log.d("MusicActivity", "Paused a MediaPlayer");
            }
        }

        for (MusicButtonData data: musicButtonDataList) {
            data.setIsPlaying(false);
        }

        adapter.getCurrentItem().resetButtonAlphas();
    }

    /**
     * Release MediaPlayer resources so we aren't using up resources
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(appBroadcastReceiver);

        for (MediaPlayer player: mediaPlayerMap.values()) {
            player.release();
            Log.d("MusicActivity", "Released a MediaPlayer");
        }
    }
}
