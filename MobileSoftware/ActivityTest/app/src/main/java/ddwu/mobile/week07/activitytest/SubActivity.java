package ddwu.mobile.week07.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        TextView textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String data = intent.getStringExtra("subject");

        textView.setText(data);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.sub_btn_okay:
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result_data", "SubActivity returns data");
                setResult(RESULT_OK, resultIntent);
                break;
            case R.id.sub_btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }

}
