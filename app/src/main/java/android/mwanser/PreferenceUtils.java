package android.mwanser;
/*
 * Copyright 2015 John Persano
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Utility class that handles calls to {@link android.content.SharedPreferences}.
 */
public class PreferenceUtils {

    // Various Preference keys
    private static final String KEY_USER_LEARNED_NAVIGATION = "user_learned_navigation";
    private static final String KEY_USER_LEARNED_PRESS_CUTTINGS_WARING= "user_learned_press_cuttings_warning";
    private static final String KEY_USER_LOGGED_IN="user_logged_in";
    private  static final String KEY_AGE_USER="user_age";
    private  static final String KEY_RESTING_HR="user_resting_hr";
    private  static final String KEY_WEIGHT="user_weight";
    private  static final String KEY_HEIGHT="user_height";
    private  static final String KEY_UNIT="user_unit";
    private  static final String KEY_EMAIL="user_email";
    private  static final String KEY_PASSWORD="user_password";
    private  static final String KEY_VO2="user_vo2";
    private  static final String KEY_GENDER="user_gender";

    public static void setPW(Context context, String p){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(KEY_PASSWORD, p).apply();

    }
    public static String getPw(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PASSWORD, null);

    }



    public static void setGender(Context context, int g){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_GENDER, g).apply();

    }
    public static int getGender(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_GENDER, -1);

    }

    public static void setVo2(Context context, int v){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_VO2, v).apply();

    }
    public static int getVo2(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_VO2, -1);

    }



    public static void setEmail(Context context, String e){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(KEY_EMAIL, e).apply();

    }
    public static String getEmail(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_EMAIL, null);

    }

    public static void setUnit(Context context, int u){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_UNIT, u).apply();

    }
    public static int getUnit(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_UNIT, -1);

    }

    public static void setHeight(Context context, String h){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putString(KEY_HEIGHT, h).apply();

    }
    public static String getHeight(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_HEIGHT, null);

    }

    public static void setRestingHr(Context context, int hr){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_RESTING_HR, hr).apply();

    }
    public static int getRestingHr(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_RESTING_HR, -1);

    }
    public static void setWeight(Context context, int w){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_WEIGHT, w).apply();

    }
    public static int getWeight(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_WEIGHT, -1);

    }



    public static void setAgeUser(Context context, int age){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_AGE_USER, age).apply();

    }
    public static int getAgeUser(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_AGE_USER, -1);

    }



    public static void setUserLoggedIn(Context context, int loggedIn){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putInt(KEY_USER_LOGGED_IN, loggedIn).apply();

    }
    public static int getUserLoggedIn(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getInt(KEY_USER_LOGGED_IN, -1);

    }
    /**
     * Sets the value for user learning the NavigationDrawer.
     *
     * @param context           A valid {@link android.content.Context}
     * @param learnedNavigation true if user has learned the NavigationDrawer
     */
    @SuppressWarnings("SameParameterValue")
    public static void setUserLearnedNavigation(Context context, boolean learnedNavigation) {

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putBoolean(KEY_USER_LEARNED_NAVIGATION, learnedNavigation).apply();

    }

    /**
     * Returns the user learned NavigationDrawer value.
     *
     * @return true if user has learned NavigationDrawer
     */
    public static boolean getUserLearnedNavigation(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_USER_LEARNED_NAVIGATION, false);

    }

    /**
     * Sets the value for user learning the PressCuttings search warning.
     *
     * @param context           A valid {@link android.content.Context}
     * @param learnedWarning true if user has learned the warning
     */
    @SuppressWarnings("SameParameterValue")
    public static void setUserLearnedPressCuttingsWarning(Context context, boolean learnedWarning) {

        PreferenceManager.getDefaultSharedPreferences(context)
                .edit().putBoolean(KEY_USER_LEARNED_PRESS_CUTTINGS_WARING, learnedWarning).apply();

    }

    /**
     * Returns the user learned PressCuttings search value.
     *
     * @return true if user has learned the warning
     */
    public static boolean getUserLearnedPressCuttingsWarning(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_USER_LEARNED_PRESS_CUTTINGS_WARING, false);

    }


}