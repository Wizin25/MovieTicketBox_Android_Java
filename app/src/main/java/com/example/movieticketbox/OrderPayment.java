package com.example.movieticketbox;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.Api.CreateOrder;

import org.json.JSONObject;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class OrderPayment extends AppCompatActivity {

    private static final String TAG = "ZaloPay_Debug";

    TextView txtTotalAmount, txtAmount;
    Button btnPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_payment);

        txtAmount = findViewById(R.id.txtAmount);
        txtTotalAmount = findViewById(R.id.txtTotalAmount);
        btnPayNow = findViewById(R.id.btnPayNow);


        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Khoi tao ZaloPay SDK
        ZaloPaySDK.init(2553, Environment.SANDBOX);
        Log.d(TAG, "ZaloPay SDK initialized");

        Intent intent = getIntent();

        //Lấy tổng tiền mà user nhập từ Payment
        String soluong = intent.getStringExtra("soluong");
        Double total = intent.getDoubleExtra("total", (double) 0);
        String totalString = String.format("%.0f", total);

        Log.d(TAG, "Received Intent Data: soluong=" + soluong + ", total=" + total);

        txtAmount.setText(soluong);
        txtTotalAmount.setText(Double.toString(total));

        btnPayNow.setOnClickListener(new View.OnClickListener() { //bat dau xu ly khi user click Confirm
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button Thanh Toán clicked");

                CreateOrder orderApi = new CreateOrder();
                try { //Gọi CreateOrder để gửi request đến ZaloPay.
                    JSONObject data = orderApi.createOrder(totalString);
                    String code = data.getString("return_code");
                    Log.d(TAG, "Return Code from API: " + code); //Token giao dịch do ZaloPay Server cấp khi tạo đơn hàng thành công.
                    // được sử dụng để gọi SDK ZaloPay để thực hiện thanh toán trong ứng dụng.

                    if (code.equals("1")) { //Đây là bước kiểm tra phản hồi từ API createOrder() của ZaloPay
                        // neu return_code trong data la 1 => co the thanh toan duoc

                        String token = data.getString("zp_trans_token");
                        Log.d(TAG, "ZaloPay Token: " + token);

                        // goi ZaloPay SDK de thanh toan
                        // demozpdk:// app dung de quay lai app sau khi thanh toan thanh cong
                        ZaloPaySDK.getInstance().payOrder(OrderPayment.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                Log.d(TAG, "onPaymentSucceeded: Thanh toán thành công! Transaction ID: " + s);

                                Intent intent1 = new Intent(OrderPayment.this, PaymentNotification.class);
                                intent1.putExtra("result", "Thanh toán thành công");
                                startActivity(intent1);
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Log.d(TAG, "onPaymentCanceled: Thanh toán bị hủy");

                                Intent intent1 = new Intent(OrderPayment.this, PaymentNotification.class);
                                intent1.putExtra("result", "Hủy thanh toán");
                                startActivity(intent1);
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                Log.e(TAG, "onPaymentError: Thanh toán thất bại, lỗi: " + zaloPayError.toString());

                                Intent intent1 = new Intent(OrderPayment.this, PaymentNotification.class);
                                intent1.putExtra("result", "Lỗi thanh toán");
                                startActivity(intent1);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}
