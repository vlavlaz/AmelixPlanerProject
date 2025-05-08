package com.example.amelixplaner;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class NoteDetailFragment extends Fragment {

    private EditText etNoteTitle, etNoteContent;
    private Button btnSaveNote;
    private NoteDatabaseHelper dbHelper;

    public NoteDetailFragment() {
        // Пустой конструктор
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Инфлейтинг XML макета фрагмента
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);

        // Инициализация элементов интерфейса
        etNoteTitle = view.findViewById(R.id.etNoteTitle);
        etNoteContent = view.findViewById(R.id.etNoteContent);
        btnSaveNote = view.findViewById(R.id.btnSaveNote);

        dbHelper = new NoteDatabaseHelper(getContext());

        // Логика сохранения заметки
        btnSaveNote.setOnClickListener(v -> {
            String title = etNoteTitle.getText().toString();
            String content = etNoteContent.getText().toString();

            // Вставка или обновление заметки в базе данных
            saveNoteToDatabase(title, content);
        });

        return view;
    }

    private void saveNoteToDatabase(String title, String content) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteDatabaseHelper.COLUMN_TITLE, title);
        values.put(NoteDatabaseHelper.COLUMN_CONTENT, content);
        values.put(NoteDatabaseHelper.COLUMN_IS_IMPORTANT, 0); // Например, заметка не важная по умолчанию

        // Вставляем данные в таблицу
        db.insert(NoteDatabaseHelper.TABLE_NAME, null, values);
    }
}