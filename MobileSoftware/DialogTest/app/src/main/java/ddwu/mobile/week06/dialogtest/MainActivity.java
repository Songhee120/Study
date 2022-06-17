package ddwu.mobile.week06.dialogtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("대화상자 제목");
        builder.setMessage("대화상자 메시지");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setPositiveButton("확인버튼", null);
        builder.setNeutralButton("대기버튼", null);
        builder.setNegativeButton("취소버튼", null);

        builder.show();
    }
}