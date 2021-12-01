package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {
    private TextView logged_teljesnev_tv;
    private Button logged_kijelentkezes_button;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        init();
        logged_kijelentkezes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        String felh = MainActivity.felh_loggedin;
        Cursor teljesnev = dbHelper.teljesNevKereses(felh);
        StringBuilder sb = new StringBuilder();
        while (teljesnev.moveToNext()) {
            sb.append(teljesnev.getString(0));
        }
        logged_teljesnev_tv.setText(sb.toString());
    }

    public void init() {
        logged_kijelentkezes_button = findViewById(R.id.logged_kijelentkezes_button);
        logged_teljesnev_tv = findViewById(R.id.logged_teljesnev_tv);
        dbHelper = new DBHelper(this);
    }
}