package com.ggproject.gustavo.moviefeed;

import android.content.Intent;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        clickLoginButton();
        clickCreateAccountTextView();

    }

    private void clickLoginButton(){
        final Button loginButton = (Button) findViewById(R.id.loginUser);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);

                startActivity(intent);
            }
        });
    }


    private void clickCreateAccountTextView(){
        TextView createOneHere = (TextView) findViewById(R.id.textview_create_one_here);
        createOneHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createAccountIntent = new Intent(v.getContext(), CreateAccount.class);
                startActivity(createAccountIntent);
            }
        });
    }
}
