package com.example.basiclistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {
    //Declare a variable for the model inflater
    LayoutInflater mInflater;
    // Declare variables to contain items in the strings file
    String[] items;
    String[] prices;
    String[] descriptions;

    //Constructor for the class, requires context (usually just "this") and the three sets of values it will populate the activity with
    public ItemAdapter(Context c, String[] i, String[] p, String[] d) {
        //Pacing the provided values into the local variables
        items = i;
        prices = p;
        descriptions = d;
        //mInflater initialised using the layout inflater service
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Gets the total number of items in the array
    @Override
    public int getCount() {
        return items.length;
    }

    //Used for getting the current position in the array
    @Override
    public Object getItem(int position) {
        return items[position];
    }

    //Gets the ID of the current array item
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Returns a view populated with the provided values
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Finds the view and inserts it into v
        View v = mInflater.inflate(R.layout.my_list_view_detail, null);
        //Gets the ID's of the TextView items so that values can be inserted into them.
        TextView nameTextView = v.findViewById(R.id.nameTextView);
        TextView descriptionTextView = v.findViewById(R.id.descriptionTextView);
        TextView priceTextView = v.findViewById(R.id.priceTextView);

        //Stores the current array item of each value in the view
        String name = items[position];
        String desc = descriptions[position];
        String cost = prices[position];

        //Sets the text of the TextViews using the ID's and then setting the text based on the current array item.
        nameTextView.setText(name);
        descriptionTextView.setText(desc);
        priceTextView.setText(cost);

        return v;
    }
}
