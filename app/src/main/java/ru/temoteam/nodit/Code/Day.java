package ru.temoteam.nodit.Code;

import java.util.HashMap;

/**
 * Created by AlexS on 5/9/2017.
 */

public class Day {
    private String date;
    private String dayOfWeek;
    private HashMap<Integer,HashMap<String,String>> lessons;

    public Day(String date,String dayOfWeek){
        this.date=date;
        this.dayOfWeek=dayOfWeek;
        lessons = new HashMap<>();
    }

    public void addLesson(int number,String name,String homework, String mark,String comment){
        HashMap<String,String> lesson = new HashMap<>();
        lesson.put("name",name);
        lesson.put("homework",homework);
        lesson.put("mark",mark);
        lesson.put("comment",comment);
        lessons.put(number,lesson);
    }

}
