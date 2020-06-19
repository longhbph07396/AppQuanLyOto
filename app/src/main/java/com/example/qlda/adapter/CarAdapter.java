package com.example.qlda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlda.R;
import com.example.qlda.activity.M4_HomeActivity;
import com.example.qlda.fragment.InforCarFragment;
import com.example.qlda.model.Car;

import java.util.List;
import java.util.Random;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {
    private List<Car> cars, carsFVRT;
    private Context context;
    private M4_HomeActivity m4_homeActivity;

    public CarAdapter(List<Car> cars, Context context, M4_HomeActivity m4_homeActivity) {
        this.cars = cars;
        this.context = context;
        this.m4_homeActivity = m4_homeActivity;
    }

    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_car, parent, false);
        carsFVRT = m4_homeActivity.sqLiteOpenHelper.getAllCarFavourite(1);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarHolder holder, final int position) {
        int rd = new Random().nextInt(3);
        holder.imgvAVT.setImageResource(cars.get(position).getAnh()[rd]);
        holder.tvTenXe.setText(cars.get(position).getTenXe());
        holder.tvNamSX.setText("Năm " + cars.get(position).getNamSX() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = carsFVRT.indexOf(cars.get(position));
                if (carsFVRT.size() > 0 && index > 0) {
                    m4_homeActivity.car = carsFVRT.get(index);
                    m4_homeActivity.ganAnhCarCusor();
                } else {
                    m4_homeActivity.car = cars.get(position);
                }
                m4_homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new InforCarFragment()).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {
        private TextView tvTenXe, tvNamSX;
        private ImageView imgvAVT;

        public CarHolder(@NonNull View itemView) {
            super(itemView);
            tvNamSX = itemView.findViewById(R.id.tvNamSX);
            tvTenXe = itemView.findViewById(R.id.tvTenXe);
            imgvAVT = itemView.findViewById(R.id.imgvAvatar);
        }
    }
}
