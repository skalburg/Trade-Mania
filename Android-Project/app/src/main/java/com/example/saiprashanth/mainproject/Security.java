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

public class Security extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button submit;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        submit=(Button)findViewById(R.id.passwordreset);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast pass;
                EditText secQue = (EditText) findViewById(R.id.secqueanswer);
                String sQue = secQue.getText().toString();
                EditText secAns = (EditText) findViewById(R.id.secansweranswer);
                String sAns= secAns.getText().toString();
                EditText phone=(EditText)findViewById(R.id.editText12);
                String phoneText=phone.getText().toString();
                String secComp = helper.searchQue(sQue,phoneText);
                if(secComp=="not found")
                {
                    pass= Toast.makeText(Security.this,"Wrong question, try again",Toast.LENGTH_SHORT);
                    pass.show();
                }
                else if(!sAns.equals(secComp))
                {
                    pass= Toast.makeText(Security.this,"Wrong answer, try again",Toast.LENGTH_SHORT);
                    pass.show();
                }
                else {
                    Global.phone=phoneText;
                    passwordreset();
                }
            }
        });
    }
    public void passwordreset()
    {
        Intent intent=new Intent(this,PasswordReset.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_security, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
