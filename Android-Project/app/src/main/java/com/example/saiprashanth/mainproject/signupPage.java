package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signupPage extends AppCompatActivity {
    Button submit;
    DatabaseHelper helper=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText name=(EditText)findViewById(R.id.Sname);
                EditText password=(EditText) findViewById(R.id.SUserPassword);
                EditText confPassword=(EditText) findViewById(R.id.SconfirmPass);
                EditText age=(EditText) findViewById(R.id.SAge);
                EditText phone=(EditText) findViewById(R.id.SEnteredPhone);
                EditText address=(EditText) findViewById(R.id.SEnteredAddress);
                EditText zip=(EditText) findViewById(R.id.SZipcode);
                EditText secQue=(EditText)findViewById(R.id.Ssecque);
                EditText secAns=(EditText)findViewById(R.id.Sanswer);
                String nameText=name.getText().toString();
                String pass=password.getText().toString();
                String passConf=confPassword.getText().toString();
                String ageText=age.getText().toString();
                String phoneText=phone.getText().toString();
                String addressText=address.getText().toString();
                String zipText=zip.getText().toString();
                String secqText=secQue.getText().toString();
                String secaText=secAns.getText().toString();
                if(!pass.equals(passConf))
                {
                    Toast display1= Toast.makeText(signupPage.this, "Passwords don't match",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(pass.isEmpty())
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter password",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(passConf.isEmpty())
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter confirm password",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(pass.length()<6)
                {
                    Toast display1= Toast.makeText(signupPage.this, "Password should be minimum 6 in length",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(nameText.isEmpty())
                {

                    Toast display1= Toast.makeText(signupPage.this, "Please enter your name",Toast.LENGTH_SHORT);
                    display1.show();
                    
                }
                else if(phoneText.isEmpty())
                {

                    Toast display1= Toast.makeText(signupPage.this, "Please enter your phone number",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(phoneText.length()<10)
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter a valid phone number with 10 digits",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(addressText.isEmpty())
                {

                    Toast display1= Toast.makeText(signupPage.this, "Please enter your address",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if (zipText.isEmpty())
                {

                    Toast display1= Toast.makeText(signupPage.this, "Please enter your zipcode",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(zipText.length()<5)
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter valid zipcode",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(ageText.length()>3)
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter correct age",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(secqText.isEmpty())
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter your security question",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else if(secaText.isEmpty())
                {
                    Toast display1= Toast.makeText(signupPage.this, "Please enter your security answer",Toast.LENGTH_SHORT);
                    display1.show();
                }
                else
                {
                    helper.insertUser(nameText,pass,ageText,phoneText,addressText,zipText,secqText,secaText);
                    Toast display2= Toast.makeText(signupPage.this, "User registered!",Toast.LENGTH_SHORT);
                    display2.show();
                    loginRedirect();
                }
            }
        });
    }
    public void loginRedirect()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup_page, menu);
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
