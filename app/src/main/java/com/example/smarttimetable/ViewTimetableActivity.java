package com.example.smarttimetable;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;

public class ViewTimetableActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Button btnShare;


    // ✅ FIX 1: GLOBAL VARIABLES
    String sem, branch;

    String[] days = {"Mon","Tue","Wed","Thu","Fri","Sat"};

    String[] timeSlots = {
            "10:15-11:15",
            "11:15-12:15",
            "12:15-1:00",
            "1:00-2:00",
            "2:00-3:00",
            "3:00-5:15"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // ✅ FIX 2

        // ✅ FIX 3: assign (NOT declare)
        sem = getIntent().getStringExtra("sem");
        branch = getIntent().getStringExtra("branch");

        setContentView(R.layout.activity_view_timetable);

        tableLayout = findViewById(R.id.tableLayout);
        btnShare = findViewById(R.id.btnShare);

        createTable();

        btnShare.setOnClickListener(v -> sharePdf());
    }

    private void createTable(){

        DBHelper db = new DBHelper(this);
        Cursor cursor = db.getTimetable(sem, branch);

        String[][] table = new String[6][6];

        // default fill
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                table[i][j] = "----";
            }
        }

        // ✅ DB DATA FILL
        while(cursor.moveToNext()){
            String day = cursor.getString(1);
            String time = cursor.getString(2);
            String subject = cursor.getString(3);

            int row = getDayIndex(day);
            int col = getTimeIndex(time);

            if(row != -1 && col != -1){
                table[row][col] = subject;
            }
        }

        tableLayout.removeAllViews();

        // HEADER
        TableRow header = new TableRow(this);
        String[] headings = {"Day/Time","10:15-11:15","11:15-12:15","12:15-1:00","1:00-2:00","2:00-3:00","3:00-5:15"};

        for(String h : headings){
            header.addView(createCell(h, true));
        }

        tableLayout.addView(header);

        String[] daysFull = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        for(int i=0;i<6;i++){

            TableRow rowLayout = new TableRow(this);

            String currentDay = daysFull[i];

            rowLayout.addView(createCell(currentDay, true));

            for(int j=0;j<6;j++){

                String currentTime = timeSlots[j];

                TextView cell = createCell(table[i][j], false);

                String finalDay = currentDay;
                String finalTime = currentTime;

                cell.setOnLongClickListener(v -> {
                    showEditDialog(cell, finalDay, finalTime);
                    return true;
                });

                rowLayout.addView(cell);
            }

            tableLayout.addView(rowLayout);
        }
    }

    private int getDayIndex(String day){
        switch(day){
            case "Monday": return 0;
            case "Tuesday": return 1;
            case "Wednesday": return 2;
            case "Thursday": return 3;
            case "Friday": return 4;
            case "Saturday": return 5;
        }
        return -1;
    }

    private int getTimeIndex(String time){
        switch(time){
            case "10:15-11:15": return 0;
            case "11:15-12:15": return 1;
            case "12:15-1:00": return 2;
            case "1:00-2:00": return 3;
            case "2:00-3:00": return 4;
            case "3:00-5:15": return 5;
        }
        return -1;
    }

    private TextView createCell(String text, boolean isHeader) {

        TextView tv = new TextView(this);

        tv.setText(text);
        tv.setPadding(16,16,16,16);
        tv.setGravity(Gravity.CENTER);

        if (isHeader) {
            tv.setTextSize(16);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setBackgroundColor(Color.parseColor("#6200EE"));
            tv.setTextColor(Color.WHITE);
        } else {
            tv.setTextSize(14);
            tv.setBackgroundResource(R.drawable.cell_bg);
            tv.setTextColor(Color.BLACK);
        }

        if (text.equals("LUNCH")) {
            tv.setBackgroundColor(Color.YELLOW);
        }


        return tv;
    }

    private File createPdf() {

        File file = new File(getExternalFilesDir(null), "timetable.pdf");

        try {
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("TIMETABLE\n\n"));

            DBHelper db = new DBHelper(this);
            Cursor cursor = db.getTimetable(sem, branch);

            String[][] tableData = new String[6][6];

            for(int i=0;i<6;i++){
                for(int j=0;j<6;j++){
                    tableData[i][j] = "----";
                }
            }

            while(cursor.moveToNext()){
                String day = cursor.getString(1);
                String time = cursor.getString(2);
                String subject = cursor.getString(3);

                int row = getDayIndex(day);
                int col = getTimeIndex(time);

                if(row != -1 && col != -1){
                    tableData[row][col] = subject;
                }
            }

            float[] columnWidth = {80f,100f,100f,100f,100f,100f,100f};
            Table table = new Table(columnWidth);

            String[] headings = {"Day/Time","10:15-11:15","11:15-12:15","12:15-1:00","1:00-2:00","2:00-3:00","3:00-5:15"};

            for(String h : headings){
                table.addCell(new Cell().add(new Paragraph(h)));
            }

            String[] daysFull = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

            for(int i=0;i<6;i++){
                table.addCell(new Cell().add(new Paragraph(daysFull[i])));
                for(int j=0;j<6;j++){
                    table.addCell(new Cell().add(new Paragraph(tableData[i][j])));
                }
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    private void sharePdf() {

        try {
            File file = createPdf();

            Uri uri = FileProvider.getUriForFile(
                    this,
                    "com.example.smarttimetable.provider",
                    file
            );

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/pdf");
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            startActivity(Intent.createChooser(intent, "Share Timetable"));

        } catch (Exception e) {
            Toast.makeText(this, "Error sharing PDF", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showEditDialog(TextView tv, String day, String time) {

        EditText input = new EditText(this);
        input.setText(tv.getText().toString());

        new AlertDialog.Builder(this)
                .setTitle("Edit Subject")
                .setView(input)
                .setPositiveButton("Save", (dialog, which) -> {

                    String newText = input.getText().toString();

                    tv.setText(newText);

                    // 🔥 SAVE TO DB
                    DBHelper db = new DBHelper(this);
                    db.updateTimetable(day, time, newText, sem, branch);

                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}