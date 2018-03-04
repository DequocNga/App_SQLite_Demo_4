package com.russia.app_sqlite_demo_4.dbmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.russia.app_sqlite_demo_4.model.Student;

import java.util.ArrayList;

/**
 * Created by VLADIMIR PUTIN on 3/3/2018.
 */

public class DBManager extends SQLiteOpenHelper {

    Context context;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "students_manager";
    private static final String TABLE = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    /*create sql query*/
    private String SQL_QUERY = "CREATE TABLE " + TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT," + PHONE + " TEXT, " + EMAIL + " TEXT, " + ADDRESS + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void addStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getNameSV());
        contentValues.put(PHONE, student.getPhoneSV());
        contentValues.put(EMAIL, student.getEmailSV());
        contentValues.put(ADDRESS, student.getAddressSV());
        sqLiteDatabase.insert(TABLE, null, contentValues);
        sqLiteDatabase.close();
    }

    public int updateStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getNameSV());
        contentValues.put(PHONE, student.getPhoneSV());
        contentValues.put(EMAIL, student.getEmailSV());
        contentValues.put(ADDRESS, student.getAddressSV());
        /*ham nay tra ve so dong bi anh huong, neu nhu co 1 hang thi tra ve 1, neu 0 co
        * thi tra ve 0*/
        return sqLiteDatabase.update(TABLE, contentValues, ID + "=?", new String[]{String.valueOf(student.getIdSV())});
    }

    public int deleteStudent(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE, ID + "=?", new String[]{String.valueOf(id)});
    }

    /*load toan bo danh sach sinh vien sau moi lan update, hoac xoa danh sach */
    public ArrayList<Student> getAllStudent() {
        /*tao ra mot mang de luu toan bo cac phan tu, sau khi thuc hien cac thao tac voi
        * database va update len listview*/
        ArrayList<Student> studentArrayList = new ArrayList<>();
        /*SQL query*/
        String SQL_QUERY = "SELECT * FROM " + TABLE;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        /*su dung Cursor de duyet toan bo bang trong database*/
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setIdSV(cursor.getInt(0));
                student.setNameSV(cursor.getString(1));
                student.setPhoneSV(cursor.getString(2));
                student.setEmailSV(cursor.getString(3));
                student.setAddressSV(cursor.getString(4));
                studentArrayList.add(student);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return studentArrayList;
    }
}
