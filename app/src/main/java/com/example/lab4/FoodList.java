package com.example.lab4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

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
                sendNotification(selectedFood);
            }
        });

    }

    private void sendNotification(obj food) {
        String channelId = "default_channel_id";
        String channelName = "Default Channel";

        // Create the notification channel if the API level is 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Default Channel Description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Order Food")
                .setContentText(food.getName())
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.white))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(0, builder.build());
    }


        private void Mappings() {
            listview = findViewById(R.id.FruitList);
            arrFood = new ArrayList<>();

            arrFood.add(new obj("Com Tam", "Good way to protect your health", "https://cdn.tgdd.vn/Files/2021/08/09/1373996/tu-lam-com-tam-suon-trung-don-gian-thom-ngon-nhu-ngoai-hang-202201151416543367.jpg"));
            arrFood.add(new obj("Bun Bo", "Orange is a good way to provide vitamin C","https://file.hstatic.net/200000395159/article/nau-bun-bo-hue-chuan-vi-tai-nha-voi-cot-co-dac-quoc-viet-foods_59b7ba1543004e67967af718d8afc32b.jpg"));
            arrFood.add(new obj("Bo Lat Lot", "Strawberry is a good for your skin","https://fullofplants.com/wp-content/uploads/2022/05/grilled-wild-betel-leaves-bo-la-lot-chay-vietnamese-dish-11-1400x2100.jpg"));
            arrFood.add(new obj("Pho", "Spicy can make you hot in your health","https://www.recipetineats.com/wp-content/uploads/2019/04/Beef-Pho_6.jpg"));
            arrFood.add(new obj("Banh uot", "Starfruit has beautiful view", "https://giochabobich.com/wp-content/uploads/2022/11/banh-uot-cha-lua-1000x565.jpg"));
            arrFood.add(new obj("Banh Cuốn", "Banana is yellow and good to eat","https://assets.epicurious.com/photos/647a294bffb3de465867f5fb/1:1/w_1920,c_limit/Banh%20Cuon-RECIPE.jpg"));

        }

}