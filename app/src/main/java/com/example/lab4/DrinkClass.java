package com.example.lab4;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class DrinkClass extends AppCompatActivity {

    ListView listview;
    ArrayList<obj> arrDrink;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_drink);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mappings();
        adapter = new CustomAdapter(this, R.layout.activity_custom_adapter, arrDrink);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                obj selectedDrink = arrDrink.get(position);
                sendNotification(selectedDrink);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedDrink", selectedDrink.getName());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void sendNotification(obj food){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Oder do an")
                .setContentText(food.getName())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.white)).build();
    }


    private void Mappings() {
        listview = findViewById(R.id.FruitList);
        arrDrink = new ArrayList<>();

        arrDrink.add(new obj("Coca", "Good way to protect your health","https://www.coca-cola.com/content/dam/onexp/vn/home-image/coca-cola/Coca-Cola_OT%20320ml_VN-EX_Desktop.png/width1960.png"));
        arrDrink.add(new obj("Number1", "Orange is a good way to provide vitamin C", "https://number1.com.vn/wp-content/uploads/2015/09/nuoc-tang-luc-number-1-one-1.jpg"));
        arrDrink.add(new obj("RedBull", "Strawberry is a good for your skin", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_85,/https://cdn.tgdd.vn/Products/Images/3226/76513/bhx/nuoc-tang-luc-redbull-lon-250ml-15112018162747.JPG"));
        arrDrink.add(new obj("Doctor Thank", "Banana is yellow and good to eat","https://d1v5l30g8wuyvb.cloudfront.net/thp.com.vn/uploads/2023/11/06140950/DRT-KD330.png"));
    }
}
