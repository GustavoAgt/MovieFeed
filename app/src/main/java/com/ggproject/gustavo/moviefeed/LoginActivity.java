package com.ggproject.gustavo.moviefeed;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TextView logoType = (TextView) findViewById(R.id.logoTypeLogin);
        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        logoType.setTypeface(pacifico);

        loginButtonEffect();
        clickLoginButtonEffect();
    }


    private void loginButtonEffect(){
        final Button loginButton = (Button) findViewById(R.id.loginUser);

    }

    private void clickLoginButtonEffect(){
        final Button loginButton = (Button) findViewById(R.id.loginUser);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
