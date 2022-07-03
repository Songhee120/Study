package dduwcom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DiaryDBHelper extends SQLiteOpenHelper {

    final static String TAG = "DiaryDBHelper";
    final static String DB_NAME = "diary.db";

    public final static String TABLE_NAME = "diary_table";
    public final static String COL_ID = "_id";
    public final static String COL_TITLE = "title";
    public final static String COL_DATE = "date";
    public final static String COL_WEATHER = "weather";
    public final static String COL_CONTENT = "content";

    public DiaryDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + COL_ID + " integer primary key autoincrement, " + COL_TITLE + " text, "
                + COL_DATE + " text, " + COL_WEATHER + " integer, " + COL_CONTENT + " text)";
        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql);
        insertSample(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void insertSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'DB 몰라 어떻게든 되겠지~~~', '2020년 6월 20일', 2, '룰루...');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'DB 제주도 여행 마지막 날', '2020년 6월 22일', 2, '제주도 재밌었다! 얼른 집가서 과제해야지');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, 'DB 와 클났다', '2020년 6월 23일', 2, '과제 언제 다하지...');");
    }
}
