package dduwcom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<MyData> myDataList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<MyData> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int pos) {
        return myDataList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return myDataList.get(pos).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        final int position = pos;

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView title = convertView.findViewById(R.id.title);
        TextView date = convertView.findViewById(R.id.date);
        ImageView weatherImg = convertView.findViewById(R.id.weatherImg);

        title.setText(String.valueOf(myDataList.get(position).getTitle()));
        date.setText(String.valueOf(myDataList.get(position).getDate()));

        int weatherNum = myDataList.get(position).getWeather();
        weatherImg.setMaxHeight(41);

        switch (weatherNum) {
            case 0 :
                weatherImg.setImageResource(R.mipmap.sun);
                break;
            case 1 :
                weatherImg.setImageResource(R.mipmap.cloud);
                break;
            case 2 :
                weatherImg.setImageResource(R.mipmap.storm);
                break;
        }

        return convertView;
    }

    public void upDateItemList(ArrayList<MyData> listItems){
        myDataList = listItems;
        notifyDataSetChanged();
    }
}
