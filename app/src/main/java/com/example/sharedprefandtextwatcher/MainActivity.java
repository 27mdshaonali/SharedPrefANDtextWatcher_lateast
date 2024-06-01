package com.example.sharedprefandtextwatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, number;

    Button save_data, next;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        save_data = findViewById(R.id.save_data);
        next = findViewById(R.id.next);


        sharedPreferences = getSharedPreferences("Shaon", MODE_PRIVATE);
        editor = sharedPreferences.edit();



        save_data.setEnabled(false);

        name.addTextChangedListener(textWatcher);
        number.addTextChangedListener(textWatcher);


        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_text = name.getText().toString();
                String number_text = number.getText().toString();



                if (name_text.length()>0 && number_text.length()>0 ){

                    editor.putString("name", name_text);
                    editor.putString("number", number_text);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Name & Number Saved", Toast.LENGTH_SHORT).show();


                } else if(name_text.length()>0){

                    editor.putString("name", name_text);
                    editor.apply();

                    Toast.makeText(MainActivity.this, "Name Saved", Toast.LENGTH_SHORT).show();

                } else if(number_text.length()>0){
                    editor.putString("number", number_text);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Number Saved", Toast.LENGTH_SHORT).show();
                }


            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, SecondActivity.class));

            }
        });



    }



    //=================================================================================

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String name_text = name.getText().toString();
            String number_text = number.getText().toString();


            save_data.setEnabled(!name_text.isEmpty() || !number_text.isEmpty());

//            if(!name_text.isEmpty() || !number_text.isEmpty()) {
//
//                save_data.setEnabled(true);
//
//            } else {
//
//                Toast.makeText(MainActivity.this, "Fill input filed with information", Toast.LENGTH_SHORT).show();
//
//            }



        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}