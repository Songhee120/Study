package ddwu.mobile.week02.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.net.LinkAddress;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        LinearLayout linear = new LinearLayout(this);
//        linear.setOrientation(LinearLayout.VERTICAL);
//        linear.setBackgroundColor(Color.LTGRAY);
//
//        TextView text = new TextView(this);
//        text.setText("TextView");
//        text.setGravity(Gravity.CENTER);
//        text.setTextColor(Color.RED);
//        text.setTextSize(20);
//
//        linear.addView(text);
//        setContentView(linear);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_main, null);

        setContentView(layout);
    }

    public void onClick (View v){
        LinearLayout layout = findViewById(R.id.layout);

        if(layout.getOrientation() == LinearLayout.VERTICAL) layout.setOrientation(LinearLayout.HORIZONTAL);
        else layout.setOrientation(LinearLayout.VERTICAL);
    }


}