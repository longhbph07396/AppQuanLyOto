package com.example.qlda.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.qlda.model.Car;
import com.example.qlda.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "QLDA.db";// Database name
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public SQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);// 1? Its database Version
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public void createDataBase() {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    //Các câu lệnh truy vấn

    //Tài khoản người dùng
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        String SELECT = "SELECT * FROM User";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(new User(cursor.getString(1), cursor.getString(2), cursor.getInt(0)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }


    //Lấy tài khoản đang đăng nhập
    public User getUser(int idUser) {
        User users = null;
        String SELECT = "SELECT * FROM User WHERE User.id=" + idUser;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users=new User(cursor.getString(1),cursor.getString(2),cursor.getInt(0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }

    //Thêm tài khoản
    public void insertUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("numberPhone", user.getUserName());
        contentValues.put("passWord", user.getPassWord());
        sqLiteDatabase.insert("User", null, contentValues);
        sqLiteDatabase.close();
    }


    //Đổi mật khẩu tài khoản
    public void updateUser(User user) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("passWord", user.getPassWord());
        sqLiteDatabase.update("User",contentValues,"id="+user.getId(),null);
        sqLiteDatabase.close();
    }

    //Lấy danh sách xe
    public List<Car> getAllCar() {
        List<Car> users = new ArrayList<>();
        String SELECT = "SELECT * FROM Car";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(new Car(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), 0));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }


    //Lấy danh sách xe yêu thích của một user
    public List<Car> getAllCarFavourite(int idUser) {
        List<Car> users = new ArrayList<>();
        String SELECT = "SELECT * FROM Car,CarFavourite WHERE Car.id=CarFavourite.idCar AND CarFavourite.idUser=" + idUser;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(new Car(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6)));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return users;
    }


    //Thêm tài xe vào danh sách yêu thích
    public void addCarFavourite(Car car, int idUser) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCar", car.getId());
        contentValues.put("idUser", idUser);
        sqLiteDatabase.insert("CarFavourite", null, contentValues);
        sqLiteDatabase.close();
    }

    //Xóa xe ra khỏi danh sách yêu thích
    public void delCarFavourite(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("CarFavourite", "id=" + id, null);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
