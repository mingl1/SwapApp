package com.example.swapapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class mainActivity extends AppCompatActivity {
    public mainActivity(){
        super(R.layout.layout_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){
            //use to pass user identity when login
            Bundle bundle = new Bundle();
            bundle.putInt("OSIS", 233613306);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,fragment_main.class,null)
                    .commit();
        }
    }
}
