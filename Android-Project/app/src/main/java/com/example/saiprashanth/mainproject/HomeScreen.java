package com.example.saiprashanth.mainproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHelper d=new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        String phone=Global.phone;
        TextView tv=(TextView)findViewById(R.id.TVUserID);
        Global.name=d.getName(DatabaseHelper.COLUMN_PHONE,phone);
        tv.setText(Global.name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
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
    public void thankyou(View v)
    {
        Intent intent=new Intent(this,ThankYou.class);
        startActivity(intent);
    }

    public void sell(View v)
    {
        Intent intent=new Intent(this,Sell.class);
        startActivity(intent);
    }

    public void buy(View v)
    {
        Intent intent=new Intent(this,Buy.class);
        startActivity(intent);
    }

    public void sold(View v)
    {
        Intent intent=new Intent(this,Sold.class);
        startActivity(intent);
    }

    public  void logout(View view){
       /* SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();*/
        Intent intent  = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Toast display2= Toast.makeText(HomeScreen.this, "Thanks for visiting, you have been successfully logged out!",Toast.LENGTH_SHORT);
        display2.show();
        startActivity(intent);
    }

    public void close(View view){
        finish();
    }
}
