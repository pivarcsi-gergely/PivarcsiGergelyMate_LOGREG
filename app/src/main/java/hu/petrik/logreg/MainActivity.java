package hu.petrik.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText main_felh_edittext, main_jelszo_edittext;
    private Button main_bejel_button, main_reg_button;
    private DBHelper dbHelper;
    public static String felh_loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        main_reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        main_bejel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String felh = main_felh_edittext.getText().toString();
                String jelszo = main_jelszo_edittext.getText().toString();
                if (felh.isEmpty() || jelszo.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ne hagyj üresen 1 mezőt se!", Toast.LENGTH_SHORT).show();
                }/*
                else if (jelszo.length() < 8) {
                    Toast.makeText(getApplicationContext(), "A jelszónak legalább 8 karakter hosszúnak kell lennie!", Toast.LENGTH_SHORT).show();
                }
                else if (felh.length() > 3){
                    Toast.makeText(getApplicationContext(), "A felhasználónévnek legalább 4 db karakterből kell állnia!", Toast.LENGTH_SHORT).show();
                }*/
                else{
                    Intent intent2 = new Intent(MainActivity.this, LoggedInActivity.class);
                    startActivity(intent2);
                    finish();
                    felh_loggedin = felh;
                }
            }
        });
    }

    public void init() {
        main_bejel_button = findViewById(R.id.main_bejel_button);
        main_jelszo_edittext = findViewById(R.id.main_jelszo_edittext);
        main_reg_button = findViewById(R.id.main_reg_button);
        main_felh_edittext = findViewById(R.id.main_felh_edittext);
        dbHelper = new DBHelper(this);
    }
}