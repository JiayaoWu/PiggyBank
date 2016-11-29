package com.cornez.piggybank;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    public static double balance;
    public String balanceS;
    TextView balanceText;
    public static int toastWhich = 0;
    private SharedPreferences myPrefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.testing);


        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start NewActivity.class
                //Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);

                //startActivity(myIntent);

            }
        });



        Context context = getApplicationContext(); // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor peditor = myPrefs.edit();

        balanceS = myPrefs.getString("balanceS", "0.00");
    }

    @Override
    protected void onStart() {

        super.onStart();

        balanceS =  myPrefs.getString("balanceS", "0.00");
        balanceText = (TextView) findViewById(R.id.balanceNum);
        balanceText.setText("$ " + balanceS);



        if (toastWhich == 1) {
            Toast.makeText(getApplicationContext(), "Keep saving and you will become billionaire!", Toast.LENGTH_LONG).show();
        } else if (toastWhich == 2) {
            Toast.makeText(getApplicationContext(), "Don't use it all on toys!", Toast.LENGTH_LONG).show();
        } else if (toastWhich == 3) {
            Toast.makeText(getApplicationContext(), "Sorry, you do not have enough money :(", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putString("balanceS", balanceS);
        peditor.commit();   // TO SAVE CHANGES

        super.onPause();

    }

    @Override
    protected void onStop() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putString("balanceS", balanceS);
        peditor.commit();   // TO SAVE CHANGES

        super.onStop();

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
