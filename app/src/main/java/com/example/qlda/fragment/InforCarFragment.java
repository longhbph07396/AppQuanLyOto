package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlda.R;
import com.example.qlda.activity.M4_HomeActivity;
import com.example.qlda.model.Car;
import com.example.qlda.sql.SQLiteOpenHelper;

import java.util.List;

public class InforCarFragment extends Fragment {
    private ImageView imgvAvatar;
    private TextView tvGia, tvDongCo;
    private M4_HomeActivity m4_homeActivity;
    private Car carCusor;
    private Button btnRed, btnBlack, btnWhite;
    private ImageButton imageButton;
    private List<Car> cars, carsFavourite;
    private SQLiteOpenHelper sqLiteOpenHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_car, container, false);
        imgvAvatar = view.findViewById(R.id.imgvAvatarInfor);
        tvDongCo = view.findViewById(R.id.tvDongCoInfor);
        tvGia = view.findViewById(R.id.tvGiaInFor);
        btnBlack = view.findViewById(R.id.btnBlack);
        btnWhite = view.findViewById(R.id.btnWhite);
        btnRed = view.findViewById(R.id.btnRed);
        imageButton = view.findViewById(R.id.imgBtnFavourite);

        //Lấy ra các dữ liệu ở activity
        sqLiteOpenHelper = new SQLiteOpenHelper(getContext());
        sqLiteOpenHelper.createDataBase();
        m4_homeActivity = (M4_HomeActivity) getActivity();
        carCusor = m4_homeActivity.car;
        cars = sqLiteOpenHelper.getAllCar();
        carsFavourite = sqLiteOpenHelper.getAllCarFavourite(m4_homeActivity.idUser);

        imgvAvatar.setImageResource(carCusor.getAnh()[0]);
        tvGia.setText(carCusor.getGia() + " $");
        tvDongCo.setText(carCusor.getThongSo());

        //Set ảnh cho nút yêu thích
        if (carsFavourite.contains(carCusor)) {
            imageButton.setImageResource(R.drawable.icon_tym);
        } else {
            imageButton.setImageResource(R.drawable.icon_tym0);
        }

        onClick();
        return view;
    }

    private void onClick() {
        btnWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvAvatar.setImageResource(carCusor.getAnh()[2]);
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvAvatar.setImageResource(carCusor.getAnh()[0]);
            }
        });
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgvAvatar.setImageResource(carCusor.getAnh()[1]);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!carsFavourite.contains(carCusor)) {
                    sqLiteOpenHelper.addCarFavourite(carCusor,m4_homeActivity.idUser);
                    Toast.makeText(getContext(), "Thêm xe vào danh sách yêu thích thành công!", Toast.LENGTH_SHORT).show();
                    imageButton.setImageResource(R.drawable.icon_tym);
                } else {
                    sqLiteOpenHelper.delCarFavourite(carCusor.getIdFavourite());
                    Toast.makeText(getContext(), "Xóa xe ra khỏi danh sách yêu thích thành công!", Toast.LENGTH_SHORT).show();
                    imageButton.setImageResource(R.drawable.icon_tym0);
                }
                carsFavourite = sqLiteOpenHelper.getAllCarFavourite(m4_homeActivity.idUser);
            }
        });
    }
}
