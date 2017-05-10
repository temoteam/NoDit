package ru.temoteam.nodit.Adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.temoteam.nodit.Code.Day;
import ru.temoteam.nodit.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        subjView.setText(lessons.get(pos).get("name"));
        markView.setText("Оценка " + lessons.get(pos).get("mark"));
        workView.setText(lessons.get(pos).get("homework"));

        return rowView;
    }
}