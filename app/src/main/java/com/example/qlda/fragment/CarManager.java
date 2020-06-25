package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlda.R;
import com.example.qlda.model.Car;
import com.example.qlda.sql.SQLiteOpenHelper;
import com.google.android.material.textfield.TextInputEditText;

public class CarManager extends Fragment {
    private TextInputEditText edtNamSX,edtGia,edtTenXe,edtThongSo;
    private Button btnLuu;
    private SQLiteOpenHelper sqLiteOpenHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_carmanager,container,false);
        anhXa(view);
        onClick();
        sqLiteOpenHelper=new SQLiteOpenHelper(getContext());
        return view;
    }

    private void onClick() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteOpenHelper.insertUser(new Car(0,Integer.parseInt(edtNamSX.getText().toString()),-1,Integer.parseInt(edtGia.getText().toString()),edtTenXe.getText().toString(),edtThongSo.getText().toString(),-1));
                Toast.makeText(getContext(),"Thêm xe thành công!",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new HomeFragment()).commit();
            }
        });
    }

    private void anhXa(View view) {
        btnLuu=view.findViewById(R.id.btnLuu);
        edtGia=view.findViewById(R.id.edtPrice);
        edtNamSX=view.findViewById(R.id.edtNamSX);
        edtTenXe=view.findViewById(R.id.edtTenXe);
        edtThongSo=view.findViewById(R.id.edtThongSo);
    }
}
