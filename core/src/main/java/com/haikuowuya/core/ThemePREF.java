package com.haikuowuya.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;

/**
 * Created by Adrian on 11/20/2015
 */
public class ThemePref
{
    private static volatile Context context;
    private static volatile SharedPreferences prefs;
    public static final String PREF_BASE_THEME = "base_theme";
    public static final String PREF_THEME_PRIMARY = "theme_primary";
    public static final String PREF_THEME_ACCENT = "theme_accent";

    private ThemePref(Context context)
    {

    }

    public static void init(Context context)
    {
        ThemePref.context = context;
        ThemePref.prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void set(String key, boolean value)
    {
        prefs.edit().putBoolean(key, value).apply();
    }

    public static void set(String key, int value)
    {
        prefs.edit().putInt(key, value).apply();
    }

    public static void setBaseTheme(int baseTheme)
    {
        set(PREF_BASE_THEME, baseTheme);
    }

    public static int getInt(String key)
    {
        return prefs.getInt(key, 0);
    }

    public static int getInt(String key, int defaultInt)
    {
        return prefs.getInt(key, defaultInt);
    }

    public static int getBaseTheme()
    {
        return getInt(PREF_BASE_THEME);
    }

    public static void setPrimaryColor(@ColorInt int color)
    {
        set(PREF_THEME_PRIMARY, color);
    }

    @ColorInt
    public static int getPrimaryColor()
    {
        return getInt(PREF_THEME_PRIMARY, ContextCompat.getColor(context, R.color.colorPrimary_light));
    }

    public static void setAccentColor(@ColorInt int color)
    {
        set(PREF_THEME_ACCENT, color);
    }

    @ColorInt
    public static int getAccentColor()
    {
        return getInt(PREF_THEME_ACCENT, ContextCompat.getColor(context, R.color.colorAccent_light));
    }
}
