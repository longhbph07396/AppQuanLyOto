package com.example.qlda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlda.R;
import com.example.qlda.base.BaseActivity;
import com.example.qlda.model.User;
import com.example.qlda.sql.SQLiteOpenHelper;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class M2_LoginActivity extends BaseActivity {

    private Button btnLogin;
    private TextView tvDangKy;
    private LinearLayout lnSDT, lnPass, lnBtn;
    private long timeBack;
    private EditText edtPass, edtUserName;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2__login);
        anhXa();
        startAnimation();
        sqLiteOpenHelper = new SQLiteOpenHelper(this);
        sqLiteOpenHelper.createDataBase();
        users = sqLiteOpenHelper.getAllUser();
    }

    private void startAnimation() {
        makeAnimation(lnBtn, this, R.anim.m2);
        makeAnimation(lnPass, this, R.anim.m2);
        makeAnimation(lnSDT, this, R.anim.m2);
    }

    private void anhXa() {
        btnLogin = findViewById(R.id.btnLogin);
        tvDangKy = findViewById(R.id.tvDangKy);
        lnBtn = findViewById(R.id.lnBtn);
        lnPass = findViewById(R.id.lnPass);
        lnSDT = findViewById(R.id.lnSDT);
        edtUserName = findViewById(R.id.edtUserNameLogin);
        edtPass = findViewById(R.id.edtPassLogin);
    }

    public void btnDangNhapClick(View view) {
        if (edtUserName.getText().toString().length() < 10 || edtUserName.getText().toString().length() > 11) {
            sendMess(this, "Số điện thoại phải từ 10 - 11 ký tự.");
            return;
        }
        if (edtPass.getText().toString().length() < 6 || edtPass.getText().toString().length() > 8) {
            sendMess(this, "Lỗi dữ liệu, mật khẩu gồm 6 - 8 ký tự.");
            return;
        }
        int index=users.indexOf(new User(edtUserName.getText().toString(), edtPass.getText().toString()));
        if (index>=0&&users.get(index).getPassWord().equals(edtPass.getText().toString())) {
            Intent intent=new Intent(M2_LoginActivity.this,M4_HomeActivity.class);
            intent.putExtra("idUser",users.get(index).getId());
            startActivity(intent);
        } else {
            sendMess(this, "Sai tên đăng nhập hoặc mật khẩu.");
        }
    }

    public void tvDangKyClick(View view) {
        startActivity(new Intent(M2_LoginActivity.this, M3_DangKyActivity.class));
        finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_left);
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
}
