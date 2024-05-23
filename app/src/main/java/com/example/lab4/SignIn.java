package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignIn extends AppCompatActivity {

    private EditText us;
    private EditText pw;
    //    private TextView createOne;
    private Button btnSignin;

    private final String REQUIRE = "REQUIRE";

    private final String usname = "username";
    private final String password = "12345678";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        us = findViewById(R.id.us);
        pw = findViewById(R.id.ps);
        btnSignin = findViewById(R.id.btnSignin);


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signinlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private boolean checkInput() {
        if (TextUtils.isEmpty((us.getText().toString()))) {
            us.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty((pw.getText().toString()))) {
            pw.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void signIn() {
        if (!checkInput()) {
            return;
        }
        if (us.getText().toString().trim().equals(usname) && pw.getText().toString().trim().equals(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            pw.setError("Usname or pw wrong");
        }

    }
}