package iitgaa.summer15.manparvesh.iitgaa.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Man Parvesh on 7/9/2015.
 */
public class PrefManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared pref file name
    private static final String PREF_NAME = "IITGAA";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    // Constructor
    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createLoginSession(String email, String name, String phone, String country) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString("email", email);
        editor.putString("name", name);
        editor.putString("phone", phone);
        editor.putString("country", country);

        // commit changes
        editor.commit();
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }
    public String getName() {
        return pref.getString("name", null);
    }
    public String getPhone() {
        return pref.getString("phone", null);
    }
    public String getCountry() {
        return pref.getString("country", null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }
}
