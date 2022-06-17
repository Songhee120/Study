package ddwu.mobile.week05.myviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyView myView = new MyView(this);
//        setContentView(myView);
    }

    public void onClick(View view){
        MyCustomView myCustomView = findViewById(R.id.myCustomView );
        Random random = new Random();

        int x = random.nextInt(500);
        int y = random.nextInt(800);
        int r = (random.nextInt(3) + 1) * 100;

        myCustomView.setLocation(x, y, r);

//        myCustomView.setColor(Color.WHITE);
        myCustomView.invalidate();
    }

//    class MyView extends View {
//        public MyView(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
////            super.onDraw(canvas);
//            canvas.drawColor(Color.LTGRAY);
//            Paint pnt = new Paint();
//            pnt.setColor(Color.BLUE);
//            canvas.drawCircle(100,100,80,pnt);
//        }
//    }
}