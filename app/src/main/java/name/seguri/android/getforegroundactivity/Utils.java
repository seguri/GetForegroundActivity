package name.seguri.android.getforegroundactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    /**
     * This is the only method reading prefs_name from strings.xml
     */
    private static SharedPreferences getSharedPrefs(final Context context) {
        final String prefsName = context.getString(R.string.sharedprefs_name);
        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public static boolean getSharedPref(final Context context, final int keyId, final boolean defValue) {
        return getSharedPref(context, context.getString(keyId), defValue);
    }

    public static boolean getSharedPref(final Context context, final String key, final boolean defValue) {
        boolean value = getSharedPrefs(context).getBoolean(key, defValue);
        Log.i(TAG, "Reading bool pref \"" + key + "\" = " + value);
        return value;
    }

    public static boolean setSharedPref(final Context context, final int keyId, final String value) {
        return setSharedPref(context, context.getString(keyId), value);
    }

    public static boolean setSharedPref(final Context context, final String key, final String value) {
        Log.i(TAG, "Setting string pref \"" + key + "\" = " + value);
        return getSharedPrefs(context)
                .edit()
                .putString(key, value)
                .commit();
    }

    public static boolean setSharedPref(final Context context, final int keyId, final boolean value) {
        return setSharedPref(context, context.getString(keyId), value);
    }

    public static boolean setSharedPref(final Context context, final String key, final boolean value) {
        Log.i(TAG, "Setting bool pref \"" + key + "\" = " + value);
        return getSharedPrefs(context)
                .edit()
                .putBoolean(key, value)
                .commit();
    }
}
