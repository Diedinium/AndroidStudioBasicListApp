package com.example.basiclistapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    // Declare variables to contain listview and the items in the strings file
    ListView myListView;
    String[] items;
    String[] prices;
    String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Put the resources into a variable for quick access
        Resources res = getResources();
        // Find the listview ID
        myListView = findViewById(R.id.myListView);
        // Initialise items to hold the "titles" of the string array items
        items = res.getStringArray(R.array.items);
        // Initialise prices to store price information from string arrays
        prices = res.getStringArray(R.array.prices);
        // Initialise descriptions to hold the descriptions stored in the string arrays
        descriptions = res.getStringArray(R.array.descriptions);

        //Creates an item adaptor using the class that has been created, provided with the string array values and context of this
        ItemAdapter itemAdapter = new ItemAdapter(this, items, prices, descriptions);

        //sets the values in myListView using the adapter that was initialised in the line beforehand.
        myListView.setAdapter(itemAdapter);

        //Creating a on click listener for the item list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Runs on click
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create an intent to show the detail activity
                Intent showDetailActivity = new Intent(getApplicationContext(), detailActivity.class);
                //Pass an extra value into the activity that will be run by the intent, the array number in this case.
                showDetailActivity.putExtra("com.example.basiclistapp.INDEX", position);
                //Start the activity, passing in showDetail activity.
                startActivity(showDetailActivity);
            }
        });

    }
}
