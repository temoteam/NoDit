package ru.temoteam.nodit.Code;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 Created by rooh on 5/8/17.
*/

public class Parser {

    public static String getData(String login,String passwd) throws IOException {
        Log.i("login",login+":"+passwd);
        URL url = new URL("http://debug.azurecom.ru:5000/" + login + "/" + passwd + "/1");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String res = "";
        Scanner in =  new Scanner(urlConnection.getInputStream());
        while (in.hasNextLine())
            res=res+in.nextLine()+"\n";
        return res;
    }

    public static String[][][] parseLessons(String unparsed) throws JSONException {
            JSONArray root_array = new JSONArray(unparsed);
        String[][][] lessons = new String[root_array.length()][][];
            for (int i=0; i < root_array.length(); i++){
                JSONArray day = root_array.getJSONArray(i);
                lessons[i] = new String[day.length()][];
                for (int j=0; j < day.length(); j++){
                    JSONObject subject = day.getJSONObject(j);
                    lessons[i][j] = new String[3];
                    lessons[i][j][0] = subject.getString(" Lesson ");
                    lessons[i][j][1] = subject.getString(" DZ ");
                    lessons[i][j][2] = subject.getString(" Mark ");
                    }
            }
        return lessons;
    }
}
