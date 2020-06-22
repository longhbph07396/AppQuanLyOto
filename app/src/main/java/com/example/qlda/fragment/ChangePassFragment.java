package com.example.qlda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlda.R;
import com.example.qlda.activity.M4_HomeActivity;

public class ChangePassFragment extends Fragment {
    private Button button;
    private EditText edtMatKhauCu,edtMatKhauMoi,edtConfimPass;
    private M4_HomeActivity m4_homeActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pass,container,false);
        anhXa(view);
        onClick();
        return view;
    }

    private void onClick() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matKhauCu=edtMatKhauCu.getText().toString(),matKhauMoi=edtMatKhauMoi.getText().toString(),confimPass=edtConfimPass.getText().toString();
                if (!matKhauCu.equals(m4_homeActivity.userCusor.getPassWord())){
                    Toast.makeText(getContext(),"Mật khẩu không chính xác!",Toast.LENGTH_SHORT).show();
                }else if (matKhauMoi.length()<6||matKhauMoi.length()>8){
                    Toast.makeText(getContext(),"Mật khẩu gồm 6 - 8 ký tự, mời nhập lại.",Toast.LENGTH_SHORT).show();
                }else if (!matKhauMoi.equals(confimPass)){
                    Toast.makeText(getContext(),"Chưa khớp mật khẩu, mời nhập lại.",Toast.LENGTH_SHORT).show();
                }else {
                    m4_homeActivity.userCusor.setPassWord(matKhauMoi);
                    m4_homeActivity.sqLiteOpenHelper.updateUser(m4_homeActivity.userCusor);
                    Toast.makeText(getContext(),"Thay đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                    m4_homeActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frameBottomNavigation, new ProfileFragment()).commit();
                }
            }
        });
    }

    private void anhXa(View view) {
        button=view.findViewById(R.id.btnDoiMatKhau);
        edtConfimPass=view.findViewById(R.id.edtConfimPass);
        edtMatKhauCu=view.findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi=view.findViewById(R.id.edtMatKhauMoi);
        m4_homeActivity= (M4_HomeActivity) getActivity();
    }
}
