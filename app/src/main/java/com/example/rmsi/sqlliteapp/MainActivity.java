package com.example.rmsi.sqlliteapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static TextView myText;
    private static EditText inputText;
    private static DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         myText = (TextView)findViewById(R.id.abyText);
        inputText = (EditText)findViewById(R.id.abyEditText);
        //dbHandler = new DBHandler(this, null, null, 1);
        dbHandler = new DBHandler(this);
        printDatabase();

    }

    public void addClick(View view){
        String productName = inputText.getText().toString();
        Product product = new Product(productName);
        dbHandler.addProduct(product);
        printDatabase();
    }

    public void deleteClick(View view){
        String productName = inputText.getText().toString();
        dbHandler.deleteProduct(productName);
        printDatabase();
    }

    public void printDatabase(){
       String dbString = dbHandler.databaseToString();
       myText.setText(dbString);
        inputText.setText("");  //Emptying the edit box
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
}
