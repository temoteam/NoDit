package ru.temoteam.nodit.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import ru.temoteam.nodit.Adapters.MTableDataAdapter;
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
        tw.setDataAdapter(new MTableDataAdapter(this,Global.days.get(2)));
    }




}
