package com.sem.loomoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    static UserLP logpass;

    EditText editTextLogin;
    EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLogin = (EditText) findViewById(R.id.editTextL);
        editTextPass = (EditText) findViewById(R.id.editTextP);

        Button enterBut = (Button) findViewById(R.id.buttonenter);
        enterBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                logging();
                new LoginTask().execute();
                new UserInfoTask().execute();
                for (int i = 0; i < 1; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void logging() {
        String login = editTextLogin.getText().toString();
        String pass = editTextPass.getText().toString();

        logpass = new UserLP();

        logpass.setLogin(login);
        logpass.setPass(pass);
    }
}
