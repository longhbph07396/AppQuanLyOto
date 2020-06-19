package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

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


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Car> cars;
    private M4_HomeActivity m4_homeActivity;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyvListHome);
        autoCompleteTextView = view.findViewById(R.id.autoTV);
        imageButton=view.findViewById(R.id.imgBtnSeachHome);
        m4_homeActivity = (M4_HomeActivity) getActivity();

        SQLiteOpenHelper sqLiteOpenHelper=new SQLiteOpenHelper(getContext());
        sqLiteOpenHelper.createDataBase();
        cars=sqLiteOpenHelper.getAllCar();
        m4_homeActivity.ganAnh(cars);
        CarAdapter carAdapter = new CarAdapter(cars, getContext(), (M4_HomeActivity) getActivity());
        recyclerView.setAdapter(carAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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


}
