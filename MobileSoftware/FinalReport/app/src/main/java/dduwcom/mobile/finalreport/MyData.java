package dduwcom.mobile.finalreport;

public class MyData {
    private long _id;
    private String title;
    private String date;
    private int weather;    // 0은 해, 1은 구름, 2는 비
    private String content;

    public MyData(long _id, String title, String date, int weather, String content) {
        this._id = _id;
        this.title = title;
        this.date = date;
        this.weather = weather;
        this.content = content;
    }

    public MyData(String title, String date, int weather, String content) {
        this.title = title;
        this.date = date;
        this.weather = weather;
        this.content = content;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
