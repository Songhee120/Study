package ddwu.mobile.week07.customadaptertest;

import java.util.ArrayList;

public class DataManager {
    private ArrayList<MyData> myDataList;

    public DataManager() {
        myDataList = new ArrayList<MyData>();

        myDataList.add(new MyData(1, "홍길동", "012345"));
        myDataList.add(new MyData(2, "전우치", "123456"));
        myDataList.add(new MyData(3, "일지매", "234567"));
    }

    public ArrayList<MyData> getMyDataList() {
        return myDataList;
    }

    public void removeData(int idx){
        myDataList.remove(idx);
    }
}
