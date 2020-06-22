package com.example.qlda.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.qlda.R;
import com.example.qlda.fragment.CallFragment;
import com.example.qlda.fragment.FavouriteFragment;
import com.example.qlda.fragment.HomeFragment;
import com.example.qlda.fragment.ProfileFragment;
import com.example.qlda.model.Car;
import com.example.qlda.model.User;
import com.example.qlda.sql.SQLiteOpenHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.URISyntaxException;
import java.util.List;

public class M4_HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    public Car car;
    public SQLiteOpenHelper sqLiteOpenHelper;
    public int idUser;
    private long timeBack;
    public User userCusor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m4__home);
        anhXa();
        //Khởi tạo database
        sqLiteOpenHelper = new SQLiteOpenHelper(this);
        sqLiteOpenHelper.createDataBase();
        idUser = getIntent().getIntExtra("idUser", -1);
        userCusor = sqLiteOpenHelper.getUser(idUser);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(selectedListener);


        //Lấy về danh sách ô tô
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - timeBack < 2000) {
            finish();
            System.exit(0);
        } else {
            Toast.makeText(this, "Nhấn lần nữa để thoát!", Toast.LENGTH_SHORT).show();
            timeBack = System.currentTimeMillis();
        }
    }

    public void ganAnh(List<Car> cars) {
        for (Car car : cars) {
            switch (car.getAnh()[0]) {
                case 1:
                    car.getAnh()[0] = R.drawable.a11;
                    car.getAnh()[1] = R.drawable.a12;
                    car.getAnh()[2] = R.drawable.a13;
                    break;
                case 2:
                    car.getAnh()[0] = R.drawable.a21;
                    car.getAnh()[1] = R.drawable.a22;
                    car.getAnh()[2] = R.drawable.a23;
                    break;
                case 3:
                    car.getAnh()[0] = R.drawable.a31;
                    car.getAnh()[1] = R.drawable.a32;
                    car.getAnh()[2] = R.drawable.a33;
                    break;
                case 4:
                    car.getAnh()[0] = R.drawable.a41;
                    car.getAnh()[1] = R.drawable.a42;
                    car.getAnh()[2] = R.drawable.a43;
                    break;
                case 5:
                    car.getAnh()[0] = R.drawable.a51;
                    car.getAnh()[1] = R.drawable.a52;
                    car.getAnh()[2] = R.drawable.a53;
                    break;
                case 6:
                    car.getAnh()[0] = R.drawable.a61;
                    car.getAnh()[1] = R.drawable.a62;
                    car.getAnh()[2] = R.drawable.a63;
                    break;
                case 7:
                    car.getAnh()[0] = R.drawable.a71;
                    car.getAnh()[1] = R.drawable.a72;
                    car.getAnh()[2] = R.drawable.a73;
                    break;
                case 8:
                    car.getAnh()[0] = R.drawable.a81;
                    car.getAnh()[1] = R.drawable.a82;
                    car.getAnh()[2] = R.drawable.a83;
                    break;
                case 9:
                    car.getAnh()[0] = R.drawable.a91;
                    car.getAnh()[1] = R.drawable.a92;
                    car.getAnh()[2] = R.drawable.a93;
                    break;
                case 10:
                    car.getAnh()[0] = R.drawable.a101;
                    car.getAnh()[1] = R.drawable.a102;
                    car.getAnh()[2] = R.drawable.a103;
                    break;
                default:
                    car.getAnh()[0] = R.drawable.icon_home;
                    car.getAnh()[1] = R.drawable.icon_home;
                    car.getAnh()[2] = R.drawable.icon_home;
                    break;
            }
        }
    }

    public void ganAnhCarCusor() {

        switch (car.getAnh()[0]) {
            case 1:
                car.getAnh()[0] = R.drawable.a11;
                car.getAnh()[1] = R.drawable.a12;
                car.getAnh()[2] = R.drawable.a13;
                break;
            case 2:
                car.getAnh()[0] = R.drawable.a21;
                car.getAnh()[1] = R.drawable.a22;
                car.getAnh()[2] = R.drawable.a23;
                break;
            case 3:
                car.getAnh()[0] = R.drawable.a31;
                car.getAnh()[1] = R.drawable.a32;
                car.getAnh()[2] = R.drawable.a33;
                break;
            case 4:
                car.getAnh()[0] = R.drawable.a41;
                car.getAnh()[1] = R.drawable.a42;
                car.getAnh()[2] = R.drawable.a43;
                break;
            case 5:
                car.getAnh()[0] = R.drawable.a51;
                car.getAnh()[1] = R.drawable.a52;
                car.getAnh()[2] = R.drawable.a53;
                break;
            case 6:
                car.getAnh()[0] = R.drawable.a61;
                car.getAnh()[1] = R.drawable.a62;
                car.getAnh()[2] = R.drawable.a63;
                break;
            case 7:
                car.getAnh()[0] = R.drawable.a71;
                car.getAnh()[1] = R.drawable.a72;
                car.getAnh()[2] = R.drawable.a73;
                break;
            case 8:
                car.getAnh()[0] = R.drawable.a81;
                car.getAnh()[1] = R.drawable.a82;
                car.getAnh()[2] = R.drawable.a83;
                break;
            case 9:
                car.getAnh()[0] = R.drawable.a91;
                car.getAnh()[1] = R.drawable.a92;
                car.getAnh()[2] = R.drawable.a93;
                break;
            case 10:
                car.getAnh()[0] = R.drawable.a101;
                car.getAnh()[1] = R.drawable.a102;
                car.getAnh()[2] = R.drawable.a103;
                break;
            default:
                car.getAnh()[0] = R.drawable.icon_home;
                car.getAnh()[1] = R.drawable.icon_home;
                car.getAnh()[2] = R.drawable.icon_home;
                break;
        }
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
                    break;
                case R.id.item_call:
                    fragment = new CallFragment();
                    break;
                case R.id.item_favourite:
                    fragment = new FavouriteFragment();
                    break;
                case R.id.item_profile:
                    fragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, fragment).commit();
            return true;
        }
    };
}
