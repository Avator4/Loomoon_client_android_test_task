package com.sem.loomoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {

    static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        textView = (TextView) findViewById(R.id.textView);

        if(MainActivity.logpass.getStr() == "1") {Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Логин или пароль введен неверно", Toast.LENGTH_SHORT).show();}
        else {
            textView.setText(MainActivity.logpass.getStr());
        }

        Button exitBut = (Button) findViewById(R.id.buttonexit);
        exitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.logpass.setCookie(null);
                Intent intent = new Intent(UserInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
