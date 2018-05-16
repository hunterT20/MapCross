package vn.aqtsoft.mapcross.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.google.gson.Gson;

import vn.aqtsoft.mapcross.model.Store;
import vn.aqtsoft.mapcross.model.User;

import static android.content.Context.MODE_PRIVATE;

public class SharePreferenceUtil {
    private static String NAME = "MyPre";

    private static String VALUE_USERNAME = "Username";
    private static String VALUE_PASSWORD = "Password";
    private static String VALUE_PERMISSION = "Permission";
    private static String VALUE_USER = "User";
    private static String VALUE_STORE = "Store";
    private static String VALUE_DETAIL = "Detail";



    public static void saveUser(Context context, String username, String pass) {
        SharedPreferences MyPre =  context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPre.edit();
        editor.putString(VALUE_USERNAME, username);
        editor.putString(VALUE_PASSWORD, pass);
        editor.apply();
    }

    public static void loadUser(Context context, EditText edtUserName, EditText edtPassword) {
        SharedPreferences MyPre = context.getSharedPreferences(NAME, MODE_PRIVATE);
        String UsernameValue = MyPre.getString(VALUE_USERNAME, "");
        String PasswordValue = MyPre.getString(VALUE_PASSWORD, "");

        edtUserName.setText(UsernameValue);
        edtPassword.setText(PasswordValue);
    }

    public static void setPermission(Context context, String permission) {
        SharedPreferences MyPre =  context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPre.edit();
        editor.putString(VALUE_PERMISSION, permission);
        editor.apply();
    }

    public static String getPermission(Context context) {
        SharedPreferences MyPre = context.getSharedPreferences(NAME, MODE_PRIVATE);
        return  MyPre.getString(VALUE_PERMISSION, "");
    }

    public static void setUser(Context context, User user){
        Gson gson = new Gson();
        SharedPreferences mPrefs = context.getSharedPreferences(NAME,MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        String json = gson.toJson(user);
        prefsEditor.putString(VALUE_USER, json);
        prefsEditor.apply();
    }

    public static String getUser(Context context) {
        SharedPreferences MyPre = context.getSharedPreferences(NAME, MODE_PRIVATE);
        return  MyPre.getString(VALUE_USER, "");
    }

    public static void setStore(Context context, Store store){
        Gson gson = new Gson();
        SharedPreferences mPrefs = context.getSharedPreferences(NAME,MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        String json = gson.toJson(store);
        prefsEditor.putString(VALUE_STORE, json);
        prefsEditor.apply();
    }

    public static String getStore(Context context) {
        SharedPreferences MyPre = context.getSharedPreferences(NAME, MODE_PRIVATE);
        return  MyPre.getString(VALUE_STORE, "");
    }

    public static void setDetail(Context context, String Detail) {
        SharedPreferences MyPre =  context.getSharedPreferences(NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = MyPre.edit();
        editor.putString(VALUE_DETAIL, Detail);
        editor.apply();
    }

    public static String getDetail(Context context) {
        SharedPreferences MyPre = context.getSharedPreferences(NAME, MODE_PRIVATE);
        return  MyPre.getString(VALUE_DETAIL, "");
    }
}
