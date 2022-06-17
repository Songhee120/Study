package ddwu.mobile.week02.calculatorsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String num1;
    String num2;
    String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        int result;

        switch (view.getId()){
            case R.id.btn_1:
                input += "1";
                display(input);
                break;
            case R.id.btn_2:
                input += "2";
                display(input);
                break;
            case R.id.btn_plus:
                num1 = input;
                input = "";
                break;
            case R.id.btn_equal:
                num2 = input;
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                display(Integer.toString(result));
                break;
        }
    }

    private void display(String output){
        TextView textView = findViewById(R.id.etDisplay);
        textView.setText(output);
    }
}