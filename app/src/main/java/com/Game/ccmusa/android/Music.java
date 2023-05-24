package com.Game.ccmusa.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


/**
 * Created by ch120505 on 7/16/2017.
 */

public class Music {
    private static MediaPlayer mp = null;

    /** Stop old song and start new one */
    public static void play(Context context, int resource) {
        stop(context);
        // Start music only if not disabled in preferences
            if (getMusic(context)) {
            mp = MediaPlayer.create(context, resource);
            mp.setLooping(true);
            mp.start();
        }
    }

    /** Stop the music */
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
    public static boolean getMusic(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Main.OPT_MUSIC, Main.OPT_MUSIC_DEF);
    }
    public static boolean getHints(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Main.OPT_HINTS, Main.OPT_HINTS_DEF);
    }
}
