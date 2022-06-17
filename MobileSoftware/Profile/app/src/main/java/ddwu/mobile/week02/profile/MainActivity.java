package ddwu.mobile.week02.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnHello(View view){
        EditText etName = (EditText)findViewById(R.id.etName);
        EditText etPhone = (EditText)findViewById(R.id.etPhone);
        String msg = "안녕하세요. 저는 " + etName.getText() + " 입니다. 전화번호는 " + etPhone.getText() + " 입니다.";

        Toast.makeText(this.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void btnExit(View view){
        finish();
    }
}