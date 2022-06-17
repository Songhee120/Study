package ddwucom.mobile.test13.exam01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    FoodDBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        myDbHelper = new FoodDBHelper(this);
    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSelect:
                SQLiteDatabase db = myDbHelper.getReadableDatabase();
                // select * from FoodDBHelper.TABLE_NAME
                Cursor cursor =
                        db.query(myDbHelper.TABLE_NAME, null, null, null,
                                null, null, null, null);
                String result = "";
                while(cursor.moveToNext()){
                    long id = cursor.getLong(cursor.getColumnIndex(FoodDBHelper.COL_ID));
                    String foodNmae = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
                    String nation = cursor.getString(2);
                    Food food = new Food(id, foodNmae, nation);
                    result += food.toString() + "\n";
                }

                tvDisplay.setText(result);

                cursor.close();
                myDbHelper.close();

                break;
            case R.id.btnAdd:
                Intent addIntent = new Intent(this, AddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.btnUpdate:

                break;
            case R.id.btnRemove:

                break;
        }

        myDbHelper.close();
    }

}
