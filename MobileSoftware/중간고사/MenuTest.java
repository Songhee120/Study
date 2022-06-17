package ddwu.mobile.week07.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean checkStatus[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        registerForContextMenu(textView);

        checkStatus = new boolean [4];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.textView:
                getMenuInflater().inflate(R.menu.activity_menu, menu);
                break;
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

//        menu.clear();

        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option01:
                Toast.makeText(this, "짜장면 냠냠긋~", Toast.LENGTH_SHORT).show();
                if(item.isChecked()){
                    item.setChecked(false);
                    checkStatus[0] = false;
                } else{
                    item.setChecked(true);
                    checkStatus[0] = true;
                }
                break;
            case R.id.option02:
                Toast.makeText(this, "짬뽕 한입만~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option03:
                Toast.makeText(this, "아 배고파~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option04:
                Toast.makeText(this, "순두부 열라면~", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.option01:
                Toast.makeText(this, "짜장면 냠냠긋~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option02:
                Toast.makeText(this, "짬뽕 한입만~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option03:
                Toast.makeText(this, "아 배고파~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option04:
                Toast.makeText(this, "순두부 열라면~", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void onMenuClick(MenuItem item){

    }
}