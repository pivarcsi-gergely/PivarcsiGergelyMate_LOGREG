package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText reg_email_edittext, reg_felh_edittext, reg_jelszo_edittext, reg_teljesnev_edittext;
    private Button reg_reg_button, reg_vissza_button;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        reg_vissza_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        reg_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = reg_email_edittext.getText().toString();
                String felh = reg_felh_edittext.getText().toString();
                String jelszo = reg_jelszo_edittext.getText().toString();
                String teljesnev = reg_teljesnev_edittext.getText().toString();
                if (email.isEmpty() || felh.isEmpty() || jelszo.isEmpty() || teljesnev.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ne hagyj üresen egy mezőt se!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (dbHelper.rogzites(email, felh, jelszo, teljesnev)){
                        Toast.makeText(getApplicationContext(), "Sikeres regisztráció!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Sikertelen regisztráció", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init() {
        reg_email_edittext = findViewById(R.id.reg_email_edittext);
        reg_felh_edittext = findViewById(R.id.reg_felh_edittext);
        reg_jelszo_edittext = findViewById(R.id.reg_jelszo_edittext);
        reg_teljesnev_edittext = findViewById(R.id.reg_teljesnev_edittext);
        reg_reg_button = findViewById(R.id.reg_reg_button);
        reg_vissza_button = findViewById(R.id.reg_vissza_button);
        dbHelper = new DBHelper(this);
    }
}