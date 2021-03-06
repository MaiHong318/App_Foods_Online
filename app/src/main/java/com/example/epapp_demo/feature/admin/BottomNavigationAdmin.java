package com.example.epapp_demo.feature.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.epapp_demo.R;

import com.example.epapp_demo.feature.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationAdmin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_admin);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_2);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // set fragment home đầu tiên
        if (savedInstanceState == null) {
            QlyCuaHangFragment gt  = new QlyCuaHangFragment();
            FragmentManager mn = getSupportFragmentManager();
            mn.beginTransaction()
                    .add(R.id.fragment_2, gt)
                    .commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.qlycuahang:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new QlyCuaHangFragment()).commit();
                    return true;
                case R.id.qlynguoidung:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new QlyKhachHangFragment()).commit();
                    return true;
                case R.id.theloaimonan:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_2, new PhanLoaiFragment()).commit();
                    return true;

                case R.id.dangxuat:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
            return false;
        }
    };
}