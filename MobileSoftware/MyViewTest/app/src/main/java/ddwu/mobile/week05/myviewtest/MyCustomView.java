package ddwu.mobile.week05.myviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyCustomView extends View {

    int color;
    int x;
    int y;
    int r;

    //View 상속 시, 필수 선언해줘야 하는 생성자 네 개
    public MyCustomView(Context context) {
        super(context);
        color = Color.RED;
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        color = Color.RED;
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        color = Color.RED;
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        color = Color.RED;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setLocation(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    protected void onDraw(Canvas canvas) {

//        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
        Paint pnt = new Paint();
        pnt.setColor(Color.WHITE);
//        pnt.setColor(color);
//        canvas.drawRect(300, 300, 500, 500, pnt);
        canvas.drawCircle(x,y,r, pnt);
    }

}
