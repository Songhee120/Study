package dduwcom.mobile.finalreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubActivity extends AppCompatActivity {

    int weatherNum = 0;
    ImageView weatherImgView;
    DataManager dataManager = new DataManager(this);
    Intent reqIntent;
    String req;
    Long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        ((TextView)findViewById(R.id.editTextDate)).setText(simpleDateFormat.format(new Date()).toString());

        reqIntent = new Intent(getIntent());
        req = reqIntent.getStringExtra("req");

        switch (req){
            case "add":
                break;
            case "update":
                MyData myData = dataManager.getMyData(reqIntent.getStringExtra("title"));

                _id = myData.get_id();
                ((TextView) findViewById(R.id.editTextDate)).setText(myData.getDate());
                ((TextView) findViewById(R.id.editTextTitle)).setText(myData.getTitle());
                ((TextView) findViewById(R.id.editTextContent)).setText(myData.getContent());
                weatherNum = myData.getWeather();
                break;
        }

        weatherImgView = findViewById(R.id.weatherImgInput);
        setWeatherImgView(weatherNum);

        weatherImgView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                weatherNum = (weatherNum + 1) % 3;
                Log.d("★★★", "onLongClick 들어옴!! 변경된 weatherNum은 " + weatherNum);
                setWeatherImgView(weatherNum);
                return true;
            }
        });
    }

    private void setWeatherImgView(int weatherNum){
        switch (weatherNum) {
            case 0 :
                weatherImgView.setImageResource(R.mipmap.sun);
                break;
            case 1 :
                weatherImgView.setImageResource(R.mipmap.cloud);
                break;
            case 2 :
                weatherImgView.setImageResource(R.mipmap.storm);
                break;
        }
    }

    public void onClick(View view){
        String date = ((TextView)findViewById(R.id.editTextDate)).getText().toString();
        String title = ((TextView)findViewById(R.id.editTextTitle)).getText().toString();
        String content = ((TextView)findViewById(R.id.editTextContent)).getText().toString();

        MyData myData = new MyData(title, date, weatherNum, content);

        switch (req){
            case "add":
                dataManager.addData(myData);
                break;
            case "update":
                dataManager.upDateData(_id, myData);
                break;
        }



        finish();
    }
}