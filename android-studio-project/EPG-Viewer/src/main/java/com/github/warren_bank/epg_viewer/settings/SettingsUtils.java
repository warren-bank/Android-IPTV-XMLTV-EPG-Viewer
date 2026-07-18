package com.github.warren_bank.epg_viewer.settings;

import com.github.warren_bank.epg_viewer.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsUtils {

  public static SharedPreferences getPrefs(Context context) {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }

  // --------------------

  public static String getDefaultXmltvEpgUrlPreference(Context context) {
    return getDefaultXmltvEpgUrlPreference(context, getPrefs(context));
  }

  private static String getDefaultXmltvEpgUrlPreference(Context context, SharedPreferences prefs) {
    String pref_key    = context.getString(R.string.pref_default_xmltv_url_key);
    String val_default = context.getString(R.string.pref_default_xmltv_url_default);

    return prefs.getString(pref_key, val_default);
  }

}
