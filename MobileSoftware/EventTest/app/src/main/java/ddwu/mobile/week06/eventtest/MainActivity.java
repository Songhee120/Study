package ddwu.mobile.week06.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        MyClick myClick = new MyClick();

        TextView textView = findViewById(R.id.textView);

        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                textView.setText("이송희");
                return true;
            }
        });
        

/*        button.setOnClickListener(new View.OnClickListener(){
            //클래스, 변수도 없음
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Noname Click!!!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Click!!!", Toast.LENGTH_SHORT).show();
            //그냥 this라고 하면 MyClick 클래스이기에 정확히 명시하기
        }
    }

    //클래스의 멤버 변수
    View.OnClickListener myInterfaceClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Interface Click!!!", Toast.LENGTH_SHORT).show();
        }
    };
}