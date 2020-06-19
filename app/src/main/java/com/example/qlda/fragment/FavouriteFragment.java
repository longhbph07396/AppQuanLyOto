package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlda.R;
import com.example.qlda.activity.M4_HomeActivity;
import com.example.qlda.adapter.CarAdapter;
import com.example.qlda.model.Car;
import com.example.qlda.sql.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {
    private SQLiteOpenHelper sqLiteOpenHelper;
    private CarAdapter carAdapter;
    private List<Car> cars;
    private RecyclerView recyclerView;
    private M4_HomeActivity m4_homeActivity;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        anhXa(view);
        khoiTaoDuLieu();
        m4_homeActivity.ganAnh(cars);
        carAdapter = new CarAdapter(cars, getContext(), (M4_HomeActivity) getActivity());
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Tìm kiếm xe trong danh sách
        timKiemXe();
        return view;
    }


    private void timKiemXe() {
        //xử lý tìm kiếm
        List<String> strings = new ArrayList<>();
        for (Car car :
                cars) {
            strings.add(car.getTenXe());
        }
        autoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,strings));

        //Sự kiện click tìm kiếm
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=autoCompleteTextView.getText().toString();
                for (Car car :
                        cars) {
                    if (car.getTenXe().equals(s)){
                        cars.clear();
                        cars.add(car);
                        recyclerView.setAdapter(new CarAdapter(cars,getContext(), (M4_HomeActivity) getActivity()));
                        break;
                    }
                }
            }
        });
    }


    private void anhXa(View view) {
        recyclerView = view.findViewById(R.id.recyvListFavourite);
        m4_homeActivity= (M4_HomeActivity) getActivity();
        autoCompleteTextView = view.findViewById(R.id.autoTVFVRT);
        imageButton=view.findViewById(R.id.imgBtnFVRT);
    }

    private void khoiTaoDuLieu() {
        sqLiteOpenHelper = new SQLiteOpenHelper(getContext());
        sqLiteOpenHelper.createDataBase();

        cars = sqLiteOpenHelper.getAllCarFavourite(m4_homeActivity.idUser);
    }
}
