package com.example.amelixplaner;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private Calendar currentCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        currentCalendar = Calendar.getInstance();

        //отображение календаря

        return view;
    }

    private void showAddEventDialog(int dayOfMonth) {
        Toast.makeText(getContext(), "Добавление события для дня: " + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    private void saveEventToDatabase(int dayOfMonth, String eventDetails) {
        EventDatabaseHelper eventDbHelper = new EventDatabaseHelper(getContext());
        SQLiteDatabase db = eventDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventDatabaseHelper.COLUMN_DATE, dayOfMonth);
        values.put(EventDatabaseHelper.COLUMN_EVENT, eventDetails);

        db.insert(EventDatabaseHelper.TABLE_NAME, null, values);
    }
}
