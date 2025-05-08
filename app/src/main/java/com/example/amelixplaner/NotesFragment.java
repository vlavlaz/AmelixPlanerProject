package com.example.amelixplaner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    private ListView listView;
    private Button btnAddNote;
    private ArrayList<Note> notesList;
    private NoteAdapter noteAdapter;
    private NoteDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        listView = view.findViewById(R.id.notesListView);
        btnAddNote = view.findViewById(R.id.btnAddNote);

        dbHelper = new NoteDatabaseHelper(getContext());

        notesList = new ArrayList<>();
        noteAdapter = new NoteAdapter(getContext(), notesList);
        listView.setAdapter(noteAdapter);

        loadNotes();

        btnAddNote.setOnClickListener(v -> {
            openAddNoteDialog();
        });

        return view;
    }

    private void loadNotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(NoteDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Получаем индексы столбцов
                int titleIndex = cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_TITLE);
                int contentIndex = cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_CONTENT);
                int isImportantIndex = cursor.getColumnIndex(NoteDatabaseHelper.COLUMN_IS_IMPORTANT);

                // Проверяем, что индексы не равны -1 (столбец существует)
                if (titleIndex != -1 && contentIndex != -1 && isImportantIndex != -1) {
                    String title = cursor.getString(titleIndex);
                    String content = cursor.getString(contentIndex);
                    boolean isImportant = cursor.getInt(isImportantIndex) == 1;

                    // Добавляем заметку в список
                    notesList.add(new Note(title, content, isImportant));
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        // Обновляем адаптер
        noteAdapter.notifyDataSetChanged();
    }

    private void openAddNoteDialog() {
        // Здесь будет код для открытия диалога добавления заметки
    }

    private void addNoteToDatabase(String title, String content, boolean isImportant) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteDatabaseHelper.COLUMN_TITLE, title);
        values.put(NoteDatabaseHelper.COLUMN_CONTENT, content);
        values.put(NoteDatabaseHelper.COLUMN_IS_IMPORTANT, isImportant ? 1 : 0);

        db.insert(NoteDatabaseHelper.TABLE_NAME, null, values);
    }
}