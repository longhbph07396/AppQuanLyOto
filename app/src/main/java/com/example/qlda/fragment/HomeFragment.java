package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlda.R;
import com.example.qlda.adapter.CarAdapter;
import com.example.qlda.model.Car;
import com.example.qlda.sql.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private List<Car> cars;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyvListHome);
        sqLiteOpenHelper = new SQLiteOpenHelper(getContext());
        sqLiteOpenHelper.createDataBase();
        cars = sqLiteOpenHelper.getAllCar();
        ganAnh();
        CarAdapter carAdapter = new CarAdapter(cars, getContext());
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void ganAnh() {
        for (Car car : cars) {
            switch (car.getAnh()) {
                case 1:
                    car.setAnh(R.drawable.a1);
                    break;
                case 2:
                    car.setAnh(R.drawable.a2);
                    break;
                case 3:
                    car.setAnh(R.drawable.a3);
                    break;
                case 4:
                    car.setAnh(R.drawable.a4);
                    break;
                case 5:
                    car.setAnh(R.drawable.a5);
                    break;
                case 6:
                    car.setAnh(R.drawable.a6);
                    break;
                case 7:
                    car.setAnh(R.drawable.a7);
                    break;
                case 8:
                    car.setAnh(R.drawable.a8);
                    break;
                case 9:
                    car.setAnh(R.drawable.a9);
                    break;
                case 10:
                    car.setAnh(R.drawable.a10);
                    break;
                default:
                    car.setAnh(R.drawable.icon_home);
                    break;
            }
        }
    }

}
