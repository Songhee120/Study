// 과제명: 다이어리 앱
// 분반: 02분반
// 학번: 20191236 성명: 이송희
// 제출일: 2022년 6월 23일

package dduwcom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataManager dataManager;
    private MyAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataManager = new DataManager(this);

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, dataManager.getMyDataList());
        listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), SubActivity.class);
                // 클릭한 위치의 제목을 가져오고 싶은데...?
//                String titleForSearch =
                intent.putExtra("req", "update");
                intent.putExtra("title", dataManager.getMyDataList().get(i).getTitle());
                //((TextView)findViewById(R.id.title)).getText().toString()
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                //Log.d("★★★", "onItemLongClick 실행됨, 넘어갈 id값은 " + dataManager.getMyDataList().get(i).get_id());

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataManager.removeData(dataManager.getMyDataList().get(position).get_id());
                                myAdapter.notifyDataSetChanged();
                                myAdapter.upDateItemList(dataManager.getMyDataList());
                            }
                        })
                        .setNegativeButton("취소", null);
                builder.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.introduce:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("개발자 정보")
                        .setMessage("이름: 이송희\n" +
                                "분반: 02\n" +
                                "학번: 20191236")
                        .setPositiveButton("확인", null);
                builder.show();
                break;
            case R.id.exit:
                ActivityCompat.finishAffinity(this);
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddClick(View view){
        Intent intent = new Intent(this, SubActivity.class);
        intent.putExtra("req", "add");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();
        myAdapter.upDateItemList(dataManager.getMyDataList());
        Log.d("★★★", "onResume 실행됨");
    }
}