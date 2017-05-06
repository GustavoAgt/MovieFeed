package com.ggproject.gustavo.moviefeed;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView logoType = (TextView) findViewById(R.id.logoTypeLogin);
        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        logoType.setTypeface(pacifico);

    }


}
