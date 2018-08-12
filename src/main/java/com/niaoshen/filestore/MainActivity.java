package com.niaoshen.filestore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private EditText edittext;
    private EditText edittext2;
    private Button button;
    private CheckBox checkBox;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = (EditText) findViewById(R.id.et1);
        edittext2 = (EditText) findViewById(R.id.et2);
        button = (Button) findViewById(R.id.bt);
        checkBox = (CheckBox) findViewById(R.id.cb);

        final SharedPreferences sharedPreferences = getSharedPreferences("mima", MODE_PRIVATE);
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        boolean isRemember = sharedPreferences.getBoolean("isChecked", false);
        if (isRemember){
            String username = sharedPreferences.getString("username","");
            String password = sharedPreferences.getString("password","");
            edittext.setText(username);
            edittext2.setText(password);
            checkBox.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edittext.getText().toString();
                String password = edittext2.getText().toString();
                if ("admin".equals(username)&&"123456".equals(password)){
                    if (checkBox.isChecked()){
                        edit.putString("username",username);
                        edit.putString("password",password);
                        edit.putBoolean("isChecked",true);
                    }else {
                        edit.clear();
                    }
                    edit.apply();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"错了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
