package ru.temoteam.nodit.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;

import ru.temoteam.nodit.R;

/**
 * Created by rooh on 5/10/17.
 */

public class MyArrayAdapter extends BaseAdapter {


    private Context context;
    private HashMap<Integer,HashMap<String,String>> lessons;

    public MyArrayAdapter(Context context, HashMap<Integer,HashMap<String,String>> lessons) {
        this.context = context;
        this.lessons = lessons;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.daylistitem, parent, false);

        TextView subjView = (TextView) rowView.findViewById(R.id.subject);
        TextView markView = (TextView) rowView.findViewById(R.id.mark);
        TextView workView = (TextView) rowView.findViewById(R.id.work);

        int pos = position + 1;
        HashMap<String,String> lesson = lessons.get(pos);
        subjView.setText(lesson.get("name"));
        //String mark = "2";
        String mark = lesson.get("mark").replaceAll("\\s","").substring(0,1);
        Log.d("mark", ">" + mark + "<");
        if(mark.equals("2")) markView.setTextColor(Color.RED);
        else if(mark.equals("3")) markView.setTextColor(Color.YELLOW);


        if(mark.equals("–")) markView.setText("");
        else markView.setText(mark);
        workView.setText(lesson.get("homework"));

        return rowView;
    }
}