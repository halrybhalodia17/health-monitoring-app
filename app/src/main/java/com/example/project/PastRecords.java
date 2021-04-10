package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class PastRecords extends Activity {

    SharedPreferences spref;
    DBHelper dbptr;
    String email;

    TableLayout tl;
    TableRow tr;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_records);

        spref = getSharedPreferences("Project", Context.MODE_PRIVATE);
        dbptr = new DBHelper(this);
        email = spref.getString("email", "");

        tl = findViewById(R.id.tableRecords);
        addHeaders();
        addData();
    }

    /** This function add the headers to the table **/
    public void addHeaders(){
        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        /** Creating TextViews to add to the row **/
        tv = new TextView(this);
        tv.setText("Datetime");
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setPadding(5, 5, 5, 0);
        tr.addView(tv);  // Adding textView to tablerow.

        tv = new TextView(this);
        tv.setText("BP");
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);  // Adding textView to tablerow.

        tv = new TextView(this);
        tv.setText("Sugar");
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);  // Adding textView to tablerow.

        tv = new TextView(this);
        tv.setText("Calorie");
        tv.setTextColor(Color.BLACK);
        tv.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        tr.addView(tv);  // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
        TextView divider = new TextView(this);

        divider.setText("-------------------------");
        divider.setTextColor(Color.BLACK);
        divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        divider = new TextView(this);
        divider.setText("---------------");
        divider.setTextColor(Color.BLACK);
        divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        divider = new TextView(this);
        divider.setText("---------------");
        divider.setTextColor(Color.BLACK);
        divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        divider = new TextView(this);
        divider.setText("---------------");
        divider.setTextColor(Color.BLACK);
        divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    /** This function add the data to the table **/
    public void addData(){
        String datetime, bp, sugar, calorie;

        String data = dbptr.getdata(this.email);
        if (data == "") {
            Log.d("debug", "No data present.");
            return;
        }

        String rows[] = data.split("\n");
        for (String row : rows) {
            String cols[] = row.split(" | ");
            int l = 0;

            datetime = cols[2] + "  " + cols[3];
            bp = cols[5];
            sugar = cols[7];
            calorie = cols[9];

            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            tv = new TextView(this);
            tv.setText(datetime);
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            tv.setPadding(5, 5, 5, 5);
            tr.addView(tv);  // Adding textView to tablerow.

            tv = new TextView(this);
            tv.setText(bp);
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            tr.addView(tv);  // Adding textView to tablerow.

            tv = new TextView(this);
            tv.setText(sugar);
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            tr.addView(tv);  // Adding textView to tablerow.

            tv = new TextView(this);
            tv.setText(calorie);
            tv.setTextColor(Color.BLACK);
            tv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            tv.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            tr.addView(tv);  // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        }
    }
}