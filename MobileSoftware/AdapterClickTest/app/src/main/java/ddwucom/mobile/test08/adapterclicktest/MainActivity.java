package ddwucom.mobile.test08.adapterclicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SubjectManager subjectManager;
    ArrayList<String> subjectList;
    ArrayAdapter<String> adapter;
    ListView listView;

    int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectManager = new SubjectManager();
        subjectList = subjectManager.getSubjectList();

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, subjectList
        );

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);

    }

    public void onClick(View view){
        EditText editText = findViewById(R.id.etItem);

        switch (view.getId()){
            case R.id.btnInsert:
                subjectManager.addData(editText.getText().toString());
                break;
            case R.id.btnUpdate:
                subjectManager.updateData(idx, editText.getText().toString());
                break;
        }
        adapter.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i("", "onItemClick 실행됨");
                    
                    EditText editText = findViewById(R.id.etItem);
                    editText.setText(subjectList.get(i));
                    idx = i;
                }
            };

    AdapterView.OnItemLongClickListener onItemLongClickListener =
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Log.i("", "idx는 " + i);
                    subjectManager.removeData(i);
                    adapter.notifyDataSetChanged();
                    return true;
                }

            };


}
