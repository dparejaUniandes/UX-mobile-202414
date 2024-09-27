package com.cucumobileux.cucu;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GameAlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.azulCucu)));
            actionBar.setTitle("Completar la secuencia");
        }

        ViewGroup rootLayout = findViewById(R.id.main);
        List<Button> allButtons = new ArrayList<>();
        getAllButtons(rootLayout, allButtons);

        for (Button button : allButtons) {

            if (button.getId() == R.id.buttonCorrect1 ||
                    button.getId() == R.id.buttonCorrect2 ||
                    button.getId() == R.id.buttonCorrect3 ||
                    button.getId() == R.id.buttonCorrect4) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GameAlarmActivity.this, GameCorrectPatternActivity.class);
                        startActivity(intent);
                    }
                });
            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GameAlarmActivity.this, GameInvalidPatternActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void getAllButtons(ViewGroup parent, List<Button> buttonList) {
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof Button) {
                buttonList.add((Button) child);
            } else if (child instanceof ViewGroup) {
                getAllButtons((ViewGroup) child, buttonList);
            }
        }
    }
}