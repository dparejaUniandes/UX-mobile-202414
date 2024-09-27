package com.cucumobileux.cucu;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.azulCucu)));
            actionBar.setTitle("Completar tareas");
        }

        CheckBox checkBox1 = findViewById(R.id.checkBox1);
        CheckBox checkBox2 = findViewById(R.id.checkBox2);
        CheckBox checkBox3 = findViewById(R.id.checkBox3);

        int[] checkboxIds = {
                R.id.checkBox1,
                R.id.checkBox2,
                R.id.checkBox3
        };

        for (int id : checkboxIds) {
            CheckBox checkBox = findViewById(id);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (checkBox1.isChecked() && checkBox2.isChecked()
                                && checkBox3.isChecked()) {
                            showPopup(buttonView);
                        }

                    }
                }
            });
        }
    }

    private void showPopup(View anchorView) {
        // Inflate the popup layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        // Create the PopupWindow
        final PopupWindow popupWindow = new PopupWindow(popupView,
                1300, // Width
                3000, // Height
                true // Focusable
        );

        // Show the popup window at the center of the screen
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

        Handler handlerPopup = new Handler();
        handlerPopup.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the new activity after the delay
                Intent intent = new Intent(CompleteActivity.this, SoundActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}