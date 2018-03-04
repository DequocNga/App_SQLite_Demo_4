package com.russia.app_sqlite_demo_4.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.russia.app_sqlite_demo_4.R;
import com.russia.app_sqlite_demo_4.adapter.Custom_Adapter;
import com.russia.app_sqlite_demo_4.dbmanager.DBManager;
import com.russia.app_sqlite_demo_4.model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextIDSV;
    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextAddress;
    Button buttonSave;
    Button buttonUpdate;
    ListView listViewShowList;
    Custom_Adapter adapter;
    ArrayList<Student> studentArrayList = new ArrayList<>();
    DBManager dbManager = new DBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();
        /*load toan bo danh sach sinh vien vao ben trong arraylist*/
        studentArrayList = dbManager.getAllStudent();
        /*sau khi load toan bo danh sach sinh vien hien co tu databse vao trong arraylist thi tien hanh set Adapter cho no 
        * de thao tac su dung voi ListView*/
        setAdapter();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = createStudent();
                if (student != null) {
                    dbManager.addStudent(student);
                }
                /*sau khi them sinh vien vao trong database thanh cong can cap nhat lai danh sach*/
                updateListStudent();
                /*cap nhat danh sach xong can set lai adapter*/
                setAdapter();
            }
        });
        listViewShowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = studentArrayList.get(i);
                editTextIDSV.setText(student.getIdSV() + "");
                editTextName.setText(student.getNameSV().toString().trim());
                editTextPhone.setText(student.getPhoneSV().toString().trim());
                editTextEmail.setText(student.getEmailSV().toString().trim());
                editTextAddress.setText(student.getAddressSV().toString().trim());
                buttonSave.setEnabled(false);
                buttonUpdate.setEnabled(true);
            }
        });
        listViewShowList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*xoa sinh vien tai vi tri thu i*/
                Student student = studentArrayList.get(i);
                /*tra ve so dong bi anh huong, xem co sinh vien nao bi xoa hay khong*/
                int result = dbManager.deleteStudent(student.getIdSV());
                if (result > 0) {
                    Toast.makeText(MainActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                } else {
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setIdSV(Integer.parseInt(String.valueOf(editTextIDSV.getText())));
                student.setNameSV(editTextName.getText() + "");
                student.setPhoneSV(editTextPhone.getText() + "");
                student.setEmailSV(editTextEmail.getText() + "");
                student.setAddressSV(editTextAddress.getText() + "");
                int resutl = dbManager.updateStudent(student);
                /*neu nhu co doi tuong bi thay doi*/
                if (resutl > 0) {
                    /*cap nhat lai danh sach sinh vien*/
                    updateListStudent();
                }else{
                    Toast.makeText(MainActivity.this, "Please select a item to update", Toast.LENGTH_SHORT).show();
                }
                /*sau khi cap nhat thanh cong danh sach sinh vien thi tien hanh luu lai*/
                buttonSave.setEnabled(true);
                /*khong duoc phep update trong pharse nay nua*/
                buttonUpdate.setEnabled(false);
            }
        });
    }

    private void updateListStudent() {
        studentArrayList.clear();
        /*load toan bo sinh vien tu database vao arraylist*/
        studentArrayList.addAll(dbManager.getAllStudent());
        if (adapter != null) {
            /*cap nhat lai adapter*/
            adapter.notifyDataSetChanged();
        }
        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextAddress.setText("");
    }

    private Student createStudent() {
        String name = editTextName.getText().toString().trim() + "";
        String phone = editTextPhone.getText().toString().trim() + "";
        String email = editTextEmail.getText().toString().trim() + "";
        String address = editTextAddress.getText().toString().trim() + "";
        Student student = new Student(name, phone, email, address);
        return student;
    }

    private void setAdapter() {
        /*neu nhu khoi chay ban dau chua co du lieu dau vao*/
        if (adapter == null) {
            adapter = new Custom_Adapter(this, R.layout.custom_listview, studentArrayList);
            listViewShowList.setAdapter(adapter);
        } else {
            /*cap nhat lai adapter*/
            adapter.notifyDataSetChanged();
            /*giam so phan tu cua listview xuong 1 don vi*/
            listViewShowList.setSelection(adapter.getCount() - 1);
        }
    }

    private void connectView() {
        editTextIDSV = findViewById(R.id.edt_idSV);
        editTextName = findViewById(R.id.edt_nameSV);
        editTextPhone = findViewById(R.id.edt_phoneSV);
        editTextEmail = findViewById(R.id.edt_emailSV);
        editTextAddress = findViewById(R.id.edt_addressSV);
        buttonSave = findViewById(R.id.btn_save);
        buttonUpdate = findViewById(R.id.btn_update);
        listViewShowList = findViewById(R.id.lv_showList);
        /*adapter = new Custom_Adapter(this, R.layout.custom_listview, studentArrayList);
        listViewShowList.setAdapter(adapter);*/
    }
}
