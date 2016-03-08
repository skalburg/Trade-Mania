package com.example.saiprashanth.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sagar on 11/12/2015.
 */
public class Sold extends AppCompatActivity{
    DatabaseHelper helper=new DatabaseHelper(this);
    Toast display1;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sold);

    }

    public void fetchItemsForSale(View v)
    {
        Button submit;
        String result="";
        final TextView resultTv=(TextView)findViewById(R.id.textView16);
        resultTv.setMovementMethod(new ScrollingMovementMethod());
        submit=(Button)findViewById(R.id.button7);
        result=helper.getItemsForSale();
        if(result=="")
        {
            display1 = Toast.makeText(Sold.this, "You have nothing to sell!", Toast.LENGTH_SHORT);
            display1.show();
        }
        resultTv.setText(result);
    }

    public void deleteItem(View v)
    {
        EditText itemId=(EditText)findViewById(R.id.editText11);
        String itemIdText=itemId.getText().toString();
        String result=helper.getItem(itemIdText);
        if(itemIdText=="")
        {
            display1 = Toast.makeText(Sold.this, "ID cannot be blank!", Toast.LENGTH_SHORT);
            display1.show();
        }
        else if (result.equals(""))
        {
            display1 = Toast.makeText(Sold.this, "Item not found in database!", Toast.LENGTH_SHORT);
            display1.show();
        } else
        {
            helper.deleteItem(itemIdText);
            display1 = Toast.makeText(Sold.this, "Item deleted!", Toast.LENGTH_SHORT);
            display1.show();
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent setIntent = new Intent(this,HomeScreen.class);
        startActivity(setIntent);
        finish();
    }

}
