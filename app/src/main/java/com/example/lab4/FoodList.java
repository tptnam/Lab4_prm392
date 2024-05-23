package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FoodList extends AppCompatActivity {


    ListView listview;
    ArrayList<obj> arrFood;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mappings();
        adapter = new CustomAdapter(this, R.layout.activity_custom_adapter, arrFood);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                obj selectedFood = arrFood.get(position);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedFood", selectedFood.getName());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
        private void Mappings() {
            listview = findViewById(R.id.FruitList);
            arrFood = new ArrayList<>();

            arrFood.add(new obj("Bún bò", "Good way to protect your health", R.drawable.bunboa));
            arrFood.add(new obj("ComTam", "Orange is a good way to provide vitamin C", R.drawable.bunboa));
            arrFood.add(new obj("Pho", "Strawberry is a good for your skin", R.drawable.bunboa));
            arrFood.add(new obj("Bo lalot", "Spicy can make you hot in your health", R.drawable.bunboa));
            arrFood.add(new obj("Banh uot", "Starfruit has beautiful view", R.drawable.bunboa));
            arrFood.add(new obj("Banh Cuốn", "Banana is yellow and good to eat", R.drawable.bunboa));

        }

}