package ddwucom.mobile.test13.exam01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    final static String TAG = "*****";
    FoodDBHelper foodDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        foodDBHelper = new FoodDBHelper(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnAddFood :
                String foodName = ((EditText)findViewById(R.id.etAddFood)).getText().toString();
                String nation = ((EditText)findViewById(R.id.etAddNation)).getText().toString();

                Log.d(TAG, foodName + " " + nation);

                ContentValues row = new ContentValues();
                row.put(FoodDBHelper.COL_FOOD, foodName);
                row.put(FoodDBHelper.COL_NATION, nation);

                SQLiteDatabase db = foodDBHelper.getWritableDatabase();
                db.insert(FoodDBHelper.TABLE_NAME, null, row);

                foodDBHelper.close();
                break;
            case R.id.btnAddCancel:

                break;
        }
        finish();
    }
}
