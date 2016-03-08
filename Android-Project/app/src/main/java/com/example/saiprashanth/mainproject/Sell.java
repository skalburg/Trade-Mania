package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sagar on 11/7/2015.
 */
public class Sell extends AppCompatActivity {

    Button submit;
    EditText username;
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_register_screen);

        username=(EditText)findViewById(R.id.userName);
        submit=(Button)findViewById(R.id.button2);
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          EditText name = (EditText) findViewById(R.id.editText4);
                                          EditText description = (EditText) findViewById(R.id.editText5);
                                          EditText amount = (EditText) findViewById(R.id.editText6);
                                          String nameText = name.getText().toString();
                                          String descText = description.getText().toString();
                                          String amountText = amount.getText().toString();

                                          if (nameText.isEmpty()) {
                                              Toast display1 = Toast.makeText(Sell.this, "Please enter the item name", Toast.LENGTH_SHORT);
                                              display1.show();
                                          } else if (descText.isEmpty()) {
                                              Toast display1 = Toast.makeText(Sell.this, "Please enter the item description", Toast.LENGTH_SHORT);
                                              display1.show();
                                          } else if (amountText.isEmpty()) {
                                              Toast display1 = Toast.makeText(Sell.this, "Please enter the item amount", Toast.LENGTH_SHORT);
                                              display1.show();
                                          } else {
                                              helper.insertItem(Global.phone,nameText, descText, amountText);
                                              Toast display2 = Toast.makeText(Sell.this, "Item is now for sale! " + Global.name, Toast.LENGTH_SHORT);
                                              display2.show();
                                              Intent intent = new Intent(Sell.this, HomeScreen.class);
                                              startActivity(intent);
                                          }
                                      }
                                  }
        );
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

    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,HomeScreen.class);
        startActivity(setIntent);
        finish();
    }
}


