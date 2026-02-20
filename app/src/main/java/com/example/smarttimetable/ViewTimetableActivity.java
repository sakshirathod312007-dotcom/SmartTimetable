package com.example.smarttimetable;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTimetableActivity extends AppCompatActivity {

    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String[] periods = {"1", "2", "3", "4", "5", "6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Header Row
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createCell("Day", true));

        for (String period : periods) {
            headerRow.addView(createCell(period, true));
        }

        tableLayout.addView(headerRow);

        // Data Rows
        for (int d = 0; d < days.length; d++) {

            TableRow row = new TableRow(this);
            row.addView(createCell(days[d], true));

            for (int i = 0; i < periods.length; i++) {
                TextView cell = createCell("Sub", false);

                if (d % 2 == 0) {
                    cell.setBackgroundColor(Color.parseColor("#F9F9FF"));
                }

                row.addView(cell);
            }

            tableLayout.addView(row);
        }

    }

    private TextView createCell(String text, boolean isHeader) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(30, 30, 30, 30);
        tv.setTextSize(14);

        if (isHeader) {
            tv.setBackgroundResource(R.drawable.table_header_border);
            tv.setTextColor(getResources().getColor(R.color.primaryColor));
            tv.setTextSize(16);
            tv.setTypeface(null, android.graphics.Typeface.BOLD);
        } else {
            tv.setBackgroundResource(R.drawable.table_cell_border);
            tv.setTextColor(android.graphics.Color.BLACK);
        }

        return tv;
    }

}
