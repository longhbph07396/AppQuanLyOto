package com.example.qlda.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.qlda.R;
import com.example.qlda.fragment.CallFragment;
import com.example.qlda.fragment.FavouriteFragment;
import com.example.qlda.fragment.HomeFragment;
import com.example.qlda.fragment.ProfileFragment;
import com.example.qlda.model.Car;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class M4_HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4__home);
        anhXa();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);

        //Gan anh cho list
    }


    private void anhXa() {
        bottomNavigationView = findViewById(R.id.botomNavigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.item_home:
                    fragment = new HomeFragment();
                    Toast.makeText(M4_HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_call:
                    fragment = new CallFragment();
                    Toast.makeText(M4_HomeActivity.this, "Call", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_favourite:
                    fragment = new FavouriteFragment();
                    Toast.makeText(M4_HomeActivity.this, "Favourite", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.item_profile:
                    fragment = new ProfileFragment();
                    Toast.makeText(M4_HomeActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, fragment).commit();
            return true;
        }
    };
}
