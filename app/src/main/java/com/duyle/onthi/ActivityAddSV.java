package com.duyle.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityAddSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sv);

        EditText edtTen = findViewById(R.id.edt_ten);
        EditText edtMssv = findViewById(R.id.edt_mssv);
        EditText edtDiemTB = findViewById(R.id.edt_diemTB);

        Button btnLuu = findViewById(R.id.btn_luu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTen.getText().toString();
                String mssv = edtMssv.getText().toString();
                String sdiemTB = edtDiemTB.getText().toString();

                int diemTB = Integer.parseInt(sdiemTB);

                SinhVienModel sinhVienModel = new SinhVienModel(ten, mssv, diemTB);

                Intent data = new Intent();
                data.putExtra("sinhvien", sinhVienModel);

                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}