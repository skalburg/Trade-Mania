package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sagar on 11/8/2015.
 */
public class PasswordReset extends AppCompatActivity {
    Button submit;
    DatabaseHelper helper=new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_reset_screen);

        submit=(Button)findViewById(R.id.button5);
        final Intent intent  = new Intent(this, MainActivity.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast display1;
                EditText password=(EditText) findViewById(R.id.editText9);
                EditText passwordConfirm=(EditText) findViewById(R.id.editText10);
                String pass=password.getText().toString();
                String passConf=passwordConfirm.getText().toString();
                if(!pass.equals(passConf))
                {
                    display1= Toast.makeText(PasswordReset.this, "Passwords don't match",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else
                {
                    helper.updatePassword(Global.phone,pass);
                    display1= Toast.makeText(PasswordReset.this, "Password updated successfully!",Toast.LENGTH_SHORT);
                    display1.show();
                    startActivity(intent);
                }
            }
            });
    }
}