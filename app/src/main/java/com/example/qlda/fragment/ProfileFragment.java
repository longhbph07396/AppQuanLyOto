package com.example.qlda.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlda.R;
import com.example.qlda.activity.M2_LoginActivity;
import com.example.qlda.activity.M3_DangKyActivity;
import com.example.qlda.activity.M4_HomeActivity;


public class ProfileFragment extends Fragment {
    private M4_HomeActivity m4_homeActivity;
    private TextView tvUserName,tvDoiMatKhau,tvDangXuat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        anhXa(view);
        ganDuLieuChoView();
        onClick();
        return view;
    }

    private void onClick() {
        tvDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m4_homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new ChangePassFragment()).commit();
            }
        });
        tvDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(m4_homeActivity, M2_LoginActivity.class));
                m4_homeActivity.finish();
                m4_homeActivity.overridePendingTransition(R.anim.in_left, R.anim.out_right);
            }
        });
    }

    private void ganDuLieuChoView() {
        tvUserName.setText(m4_homeActivity.userCusor.getUserName());
    }

    private void anhXa(View view) {
        m4_homeActivity = (M4_HomeActivity) getActivity();
        tvUserName = view.findViewById(R.id.tvUserNameProfile);
        tvDoiMatKhau=view.findViewById(R.id.tvDoiMatKhau);
        tvDangXuat=view.findViewById(R.id.tvDangXuat);
    }
}
