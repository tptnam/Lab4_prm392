package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_FOOD = 1;
    private static final int REQUEST_CODE_DRINK = 2;
    private TextView foodTextView, drinkTextView;
    private Button buttonMeal, buttonDrink, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodTextView = findViewById(R.id.food);
        drinkTextView = findViewById(R.id.drink);
        buttonMeal = findViewById(R.id.buttonMeal);
        buttonDrink = findViewById(R.id.buttonDrink);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodList.class);
                startActivityForResult(intent, REQUEST_CODE_FOOD);
            }
        });

        buttonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkClass.class);
                startActivityForResult(intent, REQUEST_CODE_DRINK);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            SharedPreferences pref = getApplication().getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            @Override
            public void onClick(View v) {
                editor.remove("us");
                editor.remove("pw");
                editor.apply();
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_crud) {
            Intent intent = new Intent(MainActivity.this, FoodCrudActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOOD) {
                String selectedFood = data.getStringExtra("selectedFood");
                if (selectedFood != null) {
                    foodTextView.setText(selectedFood);
                }
            } else if (requestCode == REQUEST_CODE_DRINK) {
                String selectedDrink = data.getStringExtra("selectedDrink");
                if (selectedDrink != null) {
                    drinkTextView.setText(selectedDrink);
                }
            }
        }
    }
}
