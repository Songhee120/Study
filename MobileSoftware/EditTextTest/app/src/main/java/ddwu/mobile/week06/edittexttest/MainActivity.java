package ddwu.mobile.week06.edittexttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Log.i(null,"onclick 호출 완료");
        
        TextView textView = (TextView) findViewById(R.id.editText);
        Log.i(null,"editText 객체 생성 완료" + textView.toString());

        switch (view.getId()){
            case R.id.btnOne:
                str += "1";
                break;
            case R.id.btnTwo:
                str += "2";
                break;
            case R.id.btnThree:
                str += "3";
                break;
            case R.id.btnClear:
                str = "";
                break;
        }

        textView.setText(str);
    }
}