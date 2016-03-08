package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sagar on 11/9/2015.
 */
public class Buy extends AppCompatActivity{
    Button submit;
    EditText username;
    DatabaseHelper helper=new DatabaseHelper(this);
    String notFound="No items are found for your search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        final TextView resultTv=(TextView)findViewById(R.id.textView15);
        resultTv.setMovementMethod(new ScrollingMovementMethod());
        submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemName = (EditText) findViewById(R.id.editText2);
                EditText zip = (EditText) findViewById(R.id.editText3);

                String itemNameText = itemName.getText().toString();
                String zipText = zip.getText().toString();
                String result;
                if(itemNameText.equals("") && zipText.equals("")) {
                    Toast display1 = Toast.makeText(Buy.this, "Both cannot be blank", Toast.LENGTH_SHORT);
                    display1.show();
                }
                else {

                    result = helper.getItemAndSellerDetails(itemNameText, zipText);
                    if(result.isEmpty())
                    {
                        resultTv.setText(notFound);
                    }
                    else
                    resultTv.setText(result);
                }
                }
        });
    }
    public void maps(View view)
    {
        Intent intent=new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,HomeScreen.class);
        startActivity(setIntent);
        finish();
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
