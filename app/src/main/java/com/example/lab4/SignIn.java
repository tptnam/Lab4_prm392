package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignIn extends AppCompatActivity {

    private EditText us;
    private EditText pw;
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
        
        autoLogin();
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

    private void autoLogin() {
        SharedPreferences pref = getApplication().getSharedPreferences("login", Context.MODE_PRIVATE);
        String alreadySignInUS = pref.getString("us", null);
        String alreadySignInPW = pref.getString("pw", null);

        if (alreadySignInUS != null && alreadySignInPW != null) {
            if (!alreadySignInUS.isEmpty() && !alreadySignInPW.isEmpty()) {
                if (alreadySignInPW.equals(password) && alreadySignInUS.equals(usname)) {
                    navigateToMainActivity();
                }
            }
        }
    }

    private void signIn() {
        SharedPreferences pref = getApplication().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String alreadySignInUS = pref.getString("us", null);
        String alreadySignInPW = pref.getString("pw", null);

        if (alreadySignInUS != null && alreadySignInPW != null) {
            if (!alreadySignInUS.isEmpty() && !alreadySignInPW.isEmpty()) {
                if (alreadySignInPW.equals(password) && alreadySignInUS.equals(usname)) {
                    navigateToMainActivity();
                    return;
                }
            }
        }

        if (!checkInput()) {
            return;
        }

        String inputUsername = us.getText().toString().trim();
        String inputPassword = pw.getText().toString().trim();

        if (inputUsername.equals(usname) && inputPassword.equals(password)) {
            editor.putString("us", inputUsername);
            editor.putString("pw", inputPassword);
            editor.apply();
            navigateToMainActivity();
        } else {
            pw.setError("Username or password is incorrect");
        }
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
