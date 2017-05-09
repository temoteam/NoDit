package ru.temoteam.nodit.Code;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Global {

    public static String[][][] lessons;
    public static SharedPreferences sharedPreferences;
    public static Activity activity;


    public static void initilizate(Activity activity) {
        sharedPreferences = activity.getSharedPreferences("global", Context.MODE_PRIVATE);
    }

    public static List<String[]> genTable(){
        List<String[]> res = new LinkedList<>();
        for (int i = 0; i < lessons.length; i++) {
            for (int j = 0; j < lessons[i].length; j++) {
                res.add(lessons[i][j]);
            }
            res.add(new String[]{"","",""});
        }

    return res;
    }



    public interface appInterface {
        void returner();
    }

    public class SharedPreferencesTags{
        public final static String S_LOGIN = "s_login";
        public final static String S_PASS = "S_password";
    }

}
