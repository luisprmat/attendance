package co.com.luisprmat.training.attendance.commons;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageManager {
    private Context context;
    private static StorageManager storageManager;
    private final String authToken = "AUTH_TOKEN";

    // Singleton pattern
    public static StorageManager getInstance(Context context) {
        if (storageManager == null)
            storageManager = new StorageManager(context);

        return storageManager;
    }

    private StorageManager(Context context) {
        this.context = context;
    }

    public String getToken() {
        SharedPreferences prefs = context.getSharedPreferences(authToken, Context.MODE_PRIVATE);
        String tkn = prefs.getString(authToken, "");

        if (tkn.equals("")) return null;
        else return tkn;
    }

    public void putToken(String token) {
        SharedPreferences.Editor editor = context.getSharedPreferences(authToken, Context.MODE_PRIVATE).edit();
        editor.putString(authToken, token);
        editor.apply();
    }
}
