package com.example.exam01;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam01.Model.TienChi;
import com.example.exam01.adapter.adapterChi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;

    public MainActivity() {
        instance = this;
    }

    public TextView txtTT, txtCL, txtNgay;
    public Spinner spinner;
    EditText editSTC;
    RadioGroup radioGroup;
    Button btnXoa, btnThem,btnTK, btnNgay;
    RecyclerView recyclerView;
    ArrayList<String> loaiChi = new ArrayList<>();
    ArrayList<TienChi> myList = new ArrayList<TienChi>();
    adapterChi adapter;
    String loaikhoanChi = "";
    public int positonTenkhoanChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
        addData();
        btnNgay.setOnClickListener(new ProcessButton());
        btnXoa.setOnClickListener(new ProcessButton());
        spinner.setOnItemSelectedListener(new ProcessSpinner());
        btnThem.setOnClickListener(new ProcessButton());
        radioGroup.setOnCheckedChangeListener(new ProcessRad());
        btnTK.setOnClickListener(new ProcessButton());
        adapter = new adapterChi(myList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    private void addData() {
        loaiChi.add("Hoc phi");
        loaiChi.add("Hoc tang cuong");
        loaiChi.add("Giao trinh");
        loaiChi.add("Khac");

        ArrayAdapter spinneradapter = new ArrayAdapter(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, loaiChi);
        spinner.setAdapter(spinneradapter);
    }

    private class ProcessButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(v==btnNgay){
                DatePickerDialog pic = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtNgay.setText(dayOfMonth+"/"+(month+1) +"/"+year);
                    }
                }, 2024,12,30);
                pic.show();
            }
            if(v==btnXoa){
                txtNgay.setText("");
                editSTC.setText("");
                txtCL.setText("");
                RadioButton rad = findViewById(R.id.radTN);
                rad.setChecked(true);
                spinner.setSelection(0);
                editSTC.setFocusable(true);
            }
            if(v == btnThem){
                int soTC = Integer.parseInt(editSTC.getText().toString());
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                if(soTC < TienChi.getTongTien()){
                    try {
                        Date d = dd.parse(txtNgay.getText().toString());
                        TienChi t = new TienChi(
                                loaikhoanChi,
                                d,
                                loaiChi.get(positonTenkhoanChi),
                                Integer.parseInt(editSTC.getText().toString())

                        );
                        int conlai = TienChi.getTongTien() - soTC;
                        txtCL.setText(conlai+"");
                        TienChi.setTongTien(conlai);
                        txtTT.setText(TienChi.getTongTien() + "");
                        myList.add(t);



                    } catch (ParseException e) {

                    }


                }
            }
            if(v == btnTK){
                adapter.notifyDataSetChanged();

            }
        }
    }
    private void getWidget() {
        txtTT = findViewById(R.id.txtTT);
        txtCL = findViewById(R.id.txtCL);
        txtNgay = findViewById(R.id.txtNgay);
        spinner = findViewById(R.id.spinner);
        editSTC = findViewById(R.id.editSTC);
        radioGroup = findViewById(R.id.radGroup);
        btnXoa = findViewById(R.id.btnXoa);
        btnThem = findViewById(R.id.btnThem);
        btnTK = findViewById(R.id.btnTK);
        btnNgay = findViewById(R.id.btnNgay);
        recyclerView = findViewById(R.id.recycleview);
    }

    private class ProcessSpinner implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            positonTenkhoanChi = position;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class ProcessRad implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton rad = findViewById(checkedId);
            loaikhoanChi = rad.getText().toString();
        }
    }
}