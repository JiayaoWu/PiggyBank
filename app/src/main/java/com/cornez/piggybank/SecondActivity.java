package com.cornez.piggybank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class SecondActivity extends AppCompatActivity {

    Stack<Double> amount;
    public double total = 0;
    TextView money;
    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        amount = new Stack<>();
        money = (TextView) findViewById(R.id.amountMoney);

        Context context = getApplicationContext(); // app level storage
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

        ImageButton pennyB = (ImageButton) findViewById(R.id.penny);
        // Capture button clicks
        pennyB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(0.01);
                total = total + 0.01;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton dimeB = (ImageButton) findViewById(R.id.dime);
        // Capture button clicks
        dimeB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(0.10);
                total = total + 0.10;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton quarterB = (ImageButton) findViewById(R.id.quarter);
        // Capture button clicks
        quarterB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(0.25);
                total = total + 0.25;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton nickelB = (ImageButton) findViewById(R.id.nickel);
        // Capture button clicks
        nickelB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(0.05);
                total = total + 0.05;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton dollarB = (ImageButton) findViewById(R.id.OneDollar);
        // Capture button clicks
        dollarB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(1.00);
                total = total + 1;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton fiveB = (ImageButton) findViewById(R.id.FiveDollar);
        // Capture button clicks
        fiveB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(5.00);
                total = total + 5;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });


        ImageButton tenB = (ImageButton) findViewById(R.id.TenDollar);
        // Capture button clicks
        tenB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(10.00);
                total = total + 10;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });

        ImageButton twentyB = (ImageButton) findViewById(R.id.TwentyDollar);
        // Capture button clicks
        twentyB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                amount.push(20.00);
                total = total + 20;
                String stringtotal = String.format("%.02f", total);
                money.setText("$" + stringtotal);

            }
        });


        ImageButton undo = (ImageButton) findViewById(R.id.undo);
        // Capture button clicks
        undo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //edge case
                if (amount.empty()) {
                    //do nothing
                } else {
                    double subtract = amount.pop();
                    total = total - subtract;
                    String stringtotal = String.format("%.02f", total);
                    money.setText("$" + stringtotal);
                }
            }
        });


        ImageButton deposit = (ImageButton) findViewById(R.id.deposit);
        // Capture button clicks
        deposit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(SecondActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to deposit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String current = myPrefs.getString("balanceS","");
                                MainActivity.balance = Double.parseDouble(current);
                                MainActivity.balance = MainActivity.balance + total;
                                String b = String.format("%.02f", MainActivity.balance);

                                SharedPreferences.Editor peditor = myPrefs.edit();
                                peditor.putString("balanceS", b);
                                peditor.commit();   // TO SAVE CHANGES


                                MainActivity.toastWhich = 1;  //for toast deposit
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });


        ImageButton withdraw = (ImageButton) findViewById(R.id.withdraw);
        // Capture button clicks
        withdraw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new AlertDialog.Builder(SecondActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Confirmation")
                        .setMessage("Are you sure you want to withdraw?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String current = myPrefs.getString("balanceS","");
                                MainActivity.balance = Double.parseDouble(current);
                                if (MainActivity.balance < total) {
                                    System.out.println("total: " + total);
                                    MainActivity.toastWhich = 3;
                                    finish();
                                } else {
                                    MainActivity.balance = MainActivity.balance - total;
                                    String b = String.format("%.02f", MainActivity.balance);

                                    SharedPreferences.Editor peditor = myPrefs.edit();
                                    peditor.putString("balanceS", b);
                                    peditor.commit();   // TO SAVE CHANGES

                                    MainActivity.toastWhich = 2;  //for toast deposit
                                    finish();
                                }
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }


}
