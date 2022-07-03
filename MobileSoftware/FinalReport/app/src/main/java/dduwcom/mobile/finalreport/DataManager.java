package dduwcom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DataManager {
    ArrayList<MyData> myDataList;
    DiaryDBHelper diaryDBHelper;

    public DataManager(Context context) {
        diaryDBHelper = new DiaryDBHelper(context);

//        myDataList = new ArrayList<MyData>();
//
//        myDataList.add(new MyData(3, "와 클났다", "2020년 6월 23일", 2,
//                "과제 언제 다하지..."));
//        myDataList.add(new MyData(2, "제주도 여행 마지막 날", "2020년 6월 22일", 0,
//                "제주도 재밌었다! 얼른 집가서 과제해야지"));
//        myDataList.add(new MyData(1, "몰라 어떻게든 되겠지~~~", "2020년 6월 20일", 2,
//                "과제 언제 다하지..."));
    }

    public ArrayList<MyData> getMyDataList() {
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();
        myDataList = new ArrayList<>();
        // select * from FoodDBHelper.TABLE_NAME
        Cursor cursor =
                db.query(diaryDBHelper.TABLE_NAME, null, null, null,
                        null, null, null, null);
        String result = "";
        while(cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String date = cursor.getString(2);
            int weather = cursor.getInt(3);
            String content = cursor.getString(4);

            MyData data = new MyData(id, title, date, weather, content);

            result += data.toString() + "\n";
            myDataList.add(data);
        }

        Log.d("★★★", result);

        db.close();
        cursor.close();
        return myDataList;
    }

    public MyData getMyData(String titleForSearch) {
        Log.d("★★★", "넘어온 titleForSearch 값은 " + titleForSearch);

        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();
        MyData myData;

        String[] columns = {"_id", "title", "date", "weather", "content"};
        String selection = "title=?";
        String[] selectArgs = new String[]{titleForSearch};

        Cursor cursor =
                db.query(diaryDBHelper.TABLE_NAME, columns, selection, selectArgs,
                        null, null, null, null);
        cursor.moveToNext();

        long id = cursor.getLong(0);
        String title = cursor.getString(1);
        String date = cursor.getString(2);
        int weather = cursor.getInt(3);
        String content = cursor.getString(4);

        MyData data = new MyData(id, title, date, weather, content);

        return data;
    }

    public void addData(MyData data){
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(diaryDBHelper.COL_TITLE, data.getTitle());
        row.put(diaryDBHelper.COL_DATE, data.getDate());
        row.put(diaryDBHelper.COL_WEATHER, data.getWeather());
        row.put(diaryDBHelper.COL_CONTENT, data.getContent());

        db.insert(DiaryDBHelper.TABLE_NAME, null, row);
        Log.d("★★★", "addData 성공!!");

        db.close();
    }

    public void upDateData(Long _id, MyData data){
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(diaryDBHelper.COL_TITLE, data.getTitle());
        row.put(diaryDBHelper.COL_DATE, data.getDate());
        row.put(diaryDBHelper.COL_WEATHER, data.getWeather());
        row.put(diaryDBHelper.COL_CONTENT, data.getContent());

        String whereClause = "_id=?";
        String[] whereArgs = new String[]{Long.toString(_id)};

        db.update(diaryDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        db.close();
    }

    public void removeData(Long _id){
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        String whereClause = "_id=?";
        String[] whereArgs = new String[] {Long.toString(_id)};

        db.delete(DiaryDBHelper.TABLE_NAME, whereClause, whereArgs);
    }






}

