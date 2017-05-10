package ru.temoteam.nodit.Code;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlexS on 5/9/2017.
 */

public class Day {
    private String date;
    private String dayOfWeek;
    public HashMap<Integer,HashMap<String,String>> lessons;

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

    public String[][] getLessons(){
        String[][] res = new String[lessons.size()][5];
        int index=0;
        for (Map.Entry<Integer,HashMap<String,String>> mentry : lessons.entrySet()){
            String[] temp = new String[5];
            temp[0] = mentry.getKey()+"";
            int t = 0;
            for (Map.Entry<String,String> entry : mentry.getValue().entrySet()){
                t++;
                temp[t] = entry.getValue();
            }
            res[index]=temp;
            index++;
        }
        return res;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public int lessonsCount(){
        return lessons.size();
    }

    public String getLessonName(int index){
        return lessons.get(index).get("name");
    }

    public String getLessonHomework(int index){
        return lessons.get(index).get("homework");
    }

    public String getLessonMark(int index){
        return lessons.get(index).get("mark");
    }

    public String getLessonComment(int index){
        return lessons.get(index).get("comment");
    }


}
