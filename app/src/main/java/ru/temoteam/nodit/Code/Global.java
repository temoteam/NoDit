package ru.temoteam.nodit.Code;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class Global {

    public static List<Day> days;
    public static SharedPreferences sharedPreferences;
    public static Activity activity;
    public static int currentDay;


    public static void initilizate(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("global", Context.MODE_PRIVATE);
    }





    public interface appInterface {
        void returner();
    }

    public class SharedPreferencesTags{
        public final static String S_LOGIN = "s_login";
        public final static String S_PASS = "S_password";
    }

}
