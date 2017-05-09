package ru.temoteam.nodit.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import ru.temoteam.nodit.Code.Global;
import ru.temoteam.nodit.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableView<String[]> tw = (TableView<String[]>) findViewById(R.id.tableView);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(3);
        columnModel.setColumnWeight(0, 3);
        columnModel.setColumnWeight(1, 3);
        columnModel.setColumnWeight(2, 1);
        tw.setColumnModel(columnModel);
        tw.setDataAdapter(new MTableDataAdapter(this,Global.genTable()));
    }


    class MTableDataAdapter extends TableDataAdapter<String[]> {


        public MTableDataAdapter(Context context, List<String[]> data) {
            super(context, data);
        }

        @Override
        public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
            String[] s = getRowData(rowIndex);
            View v = LayoutInflater.from(getContext()).inflate(R.layout.item_table, parentView, false);
            TextView renderedView = (TextView) v.findViewById(R.id.textView);




            switch (columnIndex) {
                case 0:
                    renderedView.setText(s[0]);
                    break;
                case 1:
                    renderedView.setText(s[1]);
                    break;
                case 2:
                    renderedView.setText(s[2]);
                    break;


            }

            return v;
        }
    }

}
