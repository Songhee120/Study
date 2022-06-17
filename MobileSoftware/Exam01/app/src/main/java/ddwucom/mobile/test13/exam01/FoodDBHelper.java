package ddwucom.mobile.test13.exam01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodDBHelper extends SQLiteOpenHelper {

    final static String TAG = "FoodDBHelper";
    final static String DB_NAME = "foods.db";

    public final static String TABLE_NAME = "food_table";
    public final static String COL_ID = "_id";
    public final static String COL_FOOD = "food";
    public final static String COL_NATION = "nation";

    public FoodDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_FOOD + " text, " + COL_NATION + " text)";
        Log.d(TAG, sql);
        db.execSQL(sql);
        insertSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, '된장찌개', '한국');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '김치찌개', '한국');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '훠궈', '중국');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '딤섬', '중국');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '스시', '일본');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '오코노미야키', '일본');");
    }
}


// 샘플 데이터가 필요할 때 onCreate() 의  테이블 생성 후 마지막 부분에 추가
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '된장찌개', '한국');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '김치찌개', '한국');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '훠궈', '중국');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '딤섬', '중국');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '스시', '일본');");
//        db.execSQL("insert into " + TABLE_NAME + " values (null, '오코노미야키', '일본');");