package ru.temoteam.nodit.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.codecrafters.tableview.TableDataAdapter;
import ru.temoteam.nodit.Code.Day;
import ru.temoteam.nodit.R;

/**
 * Created by AlexS on 5/9/2017.
 */

public class MTableDataAdapter extends TableDataAdapter {

    Day day;

    public MTableDataAdapter(Context context, Day day) {
        super(context, day.getLessons());
        this.day = day;
    }

    @Override
    public int getCount() {
        return day.lessonsCount();
    }


    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_table, parentView, false);
        TextView renderedView = (TextView) v.findViewById(R.id.textView);

        switch (columnIndex) {
            case 0:
                renderedView.setText(day.getLessonName(rowIndex+1));
                break;
            case 1:
                renderedView.setText(day.getLessonHomework(rowIndex+1));
                break;
            case 2:
                renderedView.setText(day.getLessonMark(rowIndex+1));
                break;


        }

        return v;
    }
}