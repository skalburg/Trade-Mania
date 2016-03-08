package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    Button signUp;
    SharedPreferences sharedpreferences;
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* signUp=(Button)findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            Intent intentSignUp=new Intent(getApplicationContext(),signupPage.class);
                startActivity(intentSignUp);
            }
        }
        );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void signuppage(View view) {
        if (view.getId() == R.id.signUp) {
            Intent intent = new Intent(this, signupPage.class);
            startActivity(intent);
        }
    }
    public void security(View view)
    {
        if(view.getId()==R.id.forgotPassword) {
            Intent intent = new Intent(this, Security.class);
            startActivity(intent);
        }
    }

    public void homescreen(View view) {
        Hashing h=new Hashing();
        if (view.getId() == R.id.logIn) {
     EditText phone=(EditText)findViewById(R.id.userName);
        String phoneStr=phone.getText().toString();
            EditText password=(EditText)findViewById(R.id.Pass);
            String upass=password.getText().toString();
            String hashOfPass=h.computeSHAHash(upass);
            String dpassComp=helper.searchPass(phoneStr);
            if(hashOfPass.equals(dpassComp))
            {
                Intent intent = new Intent(this, HomeScreen.class);
                Global.phone=phoneStr;
                startActivity(intent);
            }

            else
            {
                Toast pass= Toast.makeText(MainActivity.this,"Username and  password dont match",Toast.LENGTH_SHORT);
                pass.show();
            }
        }
    }
}
