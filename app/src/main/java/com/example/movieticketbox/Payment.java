package com.example.movieticketbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.OrderPayment;
import com.example.movieticketbox.R;

public class Payment extends AppCompatActivity {

    Button btnContinue;
    EditText edtAmount;
    TextView txtSeatNo; //Hiển thị số ghế đã chọn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        btnContinue = findViewById(R.id.btnContinue);
        edtAmount = findViewById(R.id.edtAmount);
        txtSeatNo = findViewById(R.id.txtSeatNo); // Gán view


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAmount.getText() == null || edtAmount.getText().toString().isEmpty()){
                    Toast.makeText(Payment.this, "Nhập số lượng vé", Toast.LENGTH_SHORT).show();
                    return;
                }

                String amountString = edtAmount.getText().toString();
                double total = Double.parseDouble(amountString) * (double) 100000;; // <-- chuyển trực tiếp chuỗi sang double
                Intent intent = new Intent(Payment.this, OrderPayment.class);
                intent.putExtra("soluong", edtAmount.getText().toString());
                intent.putExtra("total", total);
                startActivity(intent);
            }
        });
    }
}