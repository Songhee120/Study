package ddwu.mobile.week07.activitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int SUB_ACTIVITY_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
/*            case R.id.button:
                Intent intent = new Intent(this, SubActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;*/
/*            case R.id.button:
                Intent intent = new Intent(this, SubActivity.class);
                Food food = new Food();
                intent.putExtra("subject", "mobile software");
                intent.putExtra("food", food);
                startActivityForResult(intent, SUB_ACTIVITY_CODE);
                break;*/
            case R.id.button:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SUB_ACTIVITY_CODE:
                if(resultCode == RESULT_OK){
                    String resultData = data.getStringExtra("result_data");
                    Toast.makeText(this, resultData, Toast.LENGTH_LONG).show();
                }
            break;
        }
    }
}