package ddwu.mobile.week07.customadaptertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

    //화면 몇 개 만들어줄거야?
    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        final int position = pos;

        if(convertView == null){
            convertView = layoutInflater.inflate(layout, viewGroup, false);
        }

        TextView tvNo = convertView.findViewById(R.id.tvNo);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhone = convertView.findViewById(R.id.tvPhone);
        TextView btnCheck = convertView.findViewById(R.id.btnCheck);
        btnCheck.setFocusable(false);

        tvNo.setText(String.valueOf(myDataList.get(position).get_id()));
        tvName.setText(myDataList.get(position).getName());
        tvPhone.setText(myDataList.get(position).getPhone());
        btnCheck.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(context, myDataList.get(position).getPhone() + "  선택", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
