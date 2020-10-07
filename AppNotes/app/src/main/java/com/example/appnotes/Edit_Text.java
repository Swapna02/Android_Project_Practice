package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class Edit_Text extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__text);

        EditText multiline_text = (EditText)findViewById(R.id.editText);

        Intent intent = getIntent();

        noteId = intent.getIntExtra("NoteId",-1);

        if (noteId != -1) {

            multiline_text.setText(MainActivity.Notes.get(noteId));
        }
        else {

            MainActivity.Notes.add("");
            noteId = MainActivity.Notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();

        }
        multiline_text.addTextChangedListener(new TextWatcher() {
            @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    MainActivity.Notes.set(noteId,String.valueOf(charSequence));
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.appnotes", Context.MODE_PRIVATE);

                    HashSet<String> set = new HashSet(MainActivity.Notes);

                    sharedPreferences.edit().putStringSet("Notes",set).apply();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

    }
}
