package com.juphoon.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.juphoon.JCEngine;

public class JCEngineManager {

    public static JCEngineManager getInstance() {
        return JCEngineManagerHolder.INSTANCE;
    }

    private JCEngine mJCEngine;
    private USEventHandler mUSEventHandler;
    private static final String PREFERENCES_NAME = "preference_server_domain";
    private static final String PREFERENCES_SELECT_POS_KEY = "preference_select_position_key";

    private JCEngineManager() {
    }

    public void initialize(Context context, String appKey) {
        mUSEventHandler = new USEventHandler();
        mJCEngine = JCEngine.create(context.getApplicationContext(), appKey, mUSEventHandler.eventHandler);
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        int position = preferences.getInt(PREFERENCES_SELECT_POS_KEY, 0);
        String domain = preferences.getString(String.valueOf(position), "");
        if (!TextUtils.isEmpty(domain)) mJCEngine.setServerAddress(domain);
    }

    public void destroy() {
        if (mJCEngine != null) {
            mJCEngine.destroy();
            mJCEngine = null;
        }
        mUSEventHandler = null;
    }

    public JCEngine getJCEngine() {
        return mJCEngine;
    }

    public USEventHandler getEventHandler() {
        return mUSEventHandler;
    }

    private static final class JCEngineManagerHolder {
        private static final JCEngineManager INSTANCE = new JCEngineManager();
    }
}
