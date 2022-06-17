package ddwu.mobile.week07.customadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MyData> myDataList;
    private MyAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataManager dataManager = new DataManager();
        myDataList = dataManager.getMyDataList();

        myAdapter = new MyAdapter(this, R.layout.custom_view_layout, myDataList);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                dataManager.removeData(view.getOption);
                return true;
            }
        });


    }




}