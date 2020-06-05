package com.example.qlda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qlda.R;
import com.example.qlda.base.BaseActivity;
import com.example.qlda.model.User;
import com.example.qlda.sql.SQLiteOpenHelper;

import java.util.List;

public class M3_DangKyActivity extends BaseActivity {
    private long timeBack;
    private CheckBox ckbDieuKhoan;
    private EditText edtPass, edtPassCF, edtUserName;
    private List<User> users;
    private SQLiteOpenHelper sqLiteOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m3_dangky);
        sqLiteOpenHelper = new SQLiteOpenHelper(this);
        sqLiteOpenHelper.createDataBase();
        users = sqLiteOpenHelper.getAllUser();
        anhXa();
    }

    private void anhXa() {
        ckbDieuKhoan = findViewById(R.id.ckbDangKy);
        edtPass = findViewById(R.id.edtPass);
        edtPassCF = findViewById(R.id.edtPassCF);
        edtUserName = findViewById(R.id.edtUserName);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(M3_DangKyActivity.this, M2_LoginActivity.class));
        overridePendingTransition(R.anim.in_left, R.anim.out_right);
        finish();
    }

    public void btnDangKyClick(View view) {
        if (edtUserName.getText().toString().length() < 10 || edtUserName.getText().toString().length() > 11) {
            sendMess(this, "Số điện thoại phải từ 10 - 11 ký tự.");
            return;
        }
        if (edtPass.getText().toString().length() < 6 || edtPass.getText().toString().length() > 8) {
            sendMess(this, "Lỗi dữ liệu, mật khẩu gồm 6 - 8 ký tự.");
            return;
        }
        if (ckbDieuKhoan.isChecked()) {
            if (edtPass.getText().toString().equals(edtPassCF.getText().toString()) && !edtPass.getText().toString().trim().equals("")) {
                User user = new User(edtUserName.getText().toString(), edtPass.getText().toString());
                if (users.contains(user)) {
                    sendMess(this, "Tên tài khoản đã tồn tại.");
                } else {
                    sqLiteOpenHelper.insertUser(user);
                    sendMess(this, "Thêm tài khoản thành công!");
                    onBackPressed();
                }
            } else {
                sendMess(this, "Hai mật khẩu phải trùng khớp.");
            }
        } else {
            sendMess(this, "Bạn chưa đồng ý với điều khoản.");
        }
    }
}
