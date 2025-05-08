package com.example.amelixplaner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.amelixplaner.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Button btnCalendar, btnNotes, btnShowNoteDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация кнопок
        btnCalendar = binding.btnCalendar;
        btnNotes = binding.btnNotes;
        btnShowNoteDetail = binding.btnShowNoteDetail;

        // Кнопка для отображения фрагмента с календарем
        btnCalendar.setOnClickListener(v ->
                startActivity(
                        new Intent(
                        this, CalendarActivity.class)));


        // Кнопка для отображения фрагмента с заметками
        btnNotes.setOnClickListener(v -> replaceFragment(new NotesFragment()));

        // Кнопка для отображения фрагмента с деталями заметки
        btnShowNoteDetail.setOnClickListener(v -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new NoteDetailFragment());
            transaction.addToBackStack(null);  // Добавляем фрагмент в стек, чтобы можно было вернуться назад
            transaction.commit();
        });
    }

    // Метод для замены фрагмента
    private void replaceFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}