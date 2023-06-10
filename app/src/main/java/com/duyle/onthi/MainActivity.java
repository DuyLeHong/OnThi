package com.duyle.onthi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SinhVienModel> arrSinhvien = new ArrayList<>();
    AdapterSV adapterSV;

    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {

                        Intent data = result.getData();

                        SinhVienModel sinhVienModel = (SinhVienModel) data.getSerializableExtra("sinhvien");

                        arrSinhvien.add(sinhVienModel);
                        adapterSV.notifyDataSetChanged();

                        luuDulieu();
                    }
                }
            });

    private String FILE_NAME = "sv.txt";

    private void luuDulieu() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(arrSinhvien);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {

        }

    }

    private void docDulieu() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            arrSinhvien = (ArrayList<SinhVienModel>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvSinhvien = findViewById(R.id.lv_sinhvien);

        docDulieu();
        if (arrSinhvien.size() == 0) {
            arrSinhvien.add(new SinhVienModel("Sinh vien 1", "PH11541", 8));
            arrSinhvien.add(new SinhVienModel("Sinh vien 2", "PH16531", 9));
            arrSinhvien.add(new SinhVienModel("Sinh vien 3", "PH15411", 10));
            arrSinhvien.add(new SinhVienModel("Sinh vien 4", "PH14512", 6));
            arrSinhvien.add(new SinhVienModel("Sinh vien 5", "PH13451", 7));
        }

        adapterSV = new AdapterSV(MainActivity.this, arrSinhvien);
        lvSinhvien.setAdapter(adapterSV);

        Button btnAddSV = findViewById(R.id.btn_add_sv);
        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddSV.class);

                getData.launch(intent);
            }
        });
    }

    private class AdapterSV extends BaseAdapter {

        Activity activity;
        ArrayList<SinhVienModel> list;

        public AdapterSV(Activity activity, ArrayList<SinhVienModel> list) {
            this.activity = activity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_sv, viewGroup, false);

            SinhVienModel sinhVienModel = list.get(i);

            TextView tvName = view.findViewById(R.id.tv_ten);
            TextView tvMssv = view.findViewById(R.id.tv_mssv);
            TextView tvDiemTB = view.findViewById(R.id.tv_diemTB);

            tvName.setText(sinhVienModel.getTen());
            tvMssv.setText(sinhVienModel.getMssv());
            tvDiemTB.setText(sinhVienModel.getDiemTB() + "");

            Button btnXoa = view.findViewById(R.id.btn_xoa);
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // AlertDialog

                    arrSinhvien.remove(i);
                    notifyDataSetChanged();

                    luuDulieu();
                }
            });

            return view;
        }
    }
}