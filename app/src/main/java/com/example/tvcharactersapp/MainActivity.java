package com.example.tvcharactersapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvcharactersapp.adapter.CharacterAdapter;
import com.example.tvcharactersapp.model.Character;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Character> characterList = new ArrayList<>();
    private CharacterAdapter adapter;
    private EditText searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);

        // אתחול הרשימה עם דמויות מסדרה
        initializeCharacterList();

        adapter = new CharacterAdapter(characterList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // הוספת אפשרות חיפוש
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void initializeCharacterList() {
        characterList.add(new Character("Bart Simpson", "Mischievous troublemaker", R.drawable.bart));
        characterList.add(new Character("Homer Simpson", "Lazy but lovable dad", R.drawable.homer));
        characterList.add(new Character("Marge Simpson", "Caring mother", R.drawable.marge));
        characterList.add(new Character("Lisa Simpson", "Intelligent and responsible", R.drawable.lisa));
        characterList.add(new Character("Maggie Simpson", "Quiet but smart", R.drawable.maggie));
    }
}
