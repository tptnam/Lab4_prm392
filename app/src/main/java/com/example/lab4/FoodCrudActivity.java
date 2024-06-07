package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodCrudActivity extends AppCompatActivity {
    private EditText foodNameEditText, foodDescriptionEditText, foodImageEditText;
    private Button addFoodButton;
    private DatabaseHelper dbhepler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_crud);

        foodNameEditText = findViewById(R.id.editName);
        foodDescriptionEditText = findViewById(R.id.editDescription);
        foodImageEditText = findViewById(R.id.editImage);
        addFoodButton = findViewById(R.id.buttonAdd);

        dbhepler = new DatabaseHelper(this);

        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = foodNameEditText.getText().toString();
                String description = foodDescriptionEditText.getText().toString();
                String image = foodImageEditText.getText().toString();

                if (name.isEmpty() || description.isEmpty() || image.isEmpty()) {
                    Toast.makeText(FoodCrudActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    obj food = new obj(name, description, image);
                    dbhepler.addFood(food);
                    Toast.makeText(FoodCrudActivity.this, "Food added", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                }
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
        int itemId = item.getItemId();
        if (itemId == R.id.action_crud) {// Handle CRUD operations
            return true;
        } else if (itemId == R.id.action_back) {
            Intent intent = new Intent(FoodCrudActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optional: If you want to close this activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}