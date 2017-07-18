package com.example.dell.fridaycamp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dell on 7/18/2017.
 */

public class Sessions {





    SharedPreferences login_pref;

    SharedPreferences.Editor login_editor;

    Context _context;

    int PRIVATE_MODE = 0;


    private static final String PREF_NAME = "FridayCampLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public Sessions(Context context) {
        this._context = context;
        login_pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        login_editor = login_pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        login_editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);


        login_editor.commit();


    }


    public String getID() {

     return login_pref.getString("user_id", null);

    }


    public void setID(String id) {

        login_editor.putString("user_id", id);


        login_editor.commit();


    }

    public boolean isLoggedIn(){
        return login_pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
