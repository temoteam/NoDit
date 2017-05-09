package ru.temoteam.nodit.Code;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 Created by rooh on 5/8/17.
*/

public class Parser {

    public static String[][][] parseLessons(String unparsed) throws JSONException {
            JSONArray root_array = new JSONArray(unparsed);
            String[][][] lessons = new String[root_array.length()][][];
            for (int i=0; i < root_array.length(); i++){
                JSONArray day = root_array.getJSONObject(i).getJSONArray("lessons");
                lessons[i] = new String[day.length()][];
                for (int j=0; j < day.length(); j++){
                    JSONObject subject = day.getJSONObject(j);
                    lessons[i][j] = new String[3];
                    lessons[i][j][0] = subject.getString("name");
                    lessons[i][j][1] = subject.getString("homework");
                    lessons[i][j][2] = subject.getString("mark");
                    }
            }
        return lessons;
    }
}
