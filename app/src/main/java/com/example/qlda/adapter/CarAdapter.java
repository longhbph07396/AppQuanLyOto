package com.example.qlda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qlda.R;
import com.example.qlda.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarHolder> {
    private List<Car> cars;
    private Context context;

    public CarAdapter(List<Car> cars, Context context) {
        this.cars = cars;
        this.context = context;
    }


    @NonNull
    @Override
    public CarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.recycle_car,parent,false);
        return new CarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarHolder holder, int position) {
            holder.imgvAVT.setImageResource(cars.get(position).getAnh());
            holder.tvTenXe.setText(cars.get(position).getTenXe());
            holder.tvNamSX.setText(cars.get(position).getNamSX()+"");
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class CarHolder extends RecyclerView.ViewHolder {
        private TextView tvTenXe,tvNamSX;
        private ImageView imgvAVT;
        public CarHolder(@NonNull View itemView) {
            super(itemView);
            tvNamSX=itemView.findViewById(R.id.tvNamSX);
            tvTenXe=itemView.findViewById(R.id.tvTenXe);
            imgvAVT=itemView.findViewById(R.id.imgvAvatar);
        }
    }
}
