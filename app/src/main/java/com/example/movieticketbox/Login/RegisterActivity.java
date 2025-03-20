package com.example.movieticketbox.Login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.ApiService;
import com.example.movieticketbox.MainActivity;
import com.example.movieticketbox.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";  // Thêm tag để log

    private EditText fullnameInput, usernameInput, passwordInput, rePasswordInput, emailInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo các view
        fullnameInput = findViewById(R.id.fullnameInput);
        emailInput = findViewById(R.id.emailInput);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        rePasswordInput = findViewById(R.id.rePasswordInput);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnameInput.getText().toString();
                String email = emailInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String rePassword = rePasswordInput.getText().toString();

                // Log các giá trị để kiểm tra
                Log.d(TAG, "Fullname: " + fullname);
                Log.d(TAG, "Email: " + email);
                Log.d(TAG, "Username: " + username);
                Log.d(TAG, "Password: " + password);
                Log.d(TAG, "RePassword: " + rePassword);

                if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(rePassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi API đăng ký
                registerUser(fullname,email, username, password, rePassword);
            }
        });
    }

    private void registerUser(String fullname,String email, String username, String password, String rePassword) {
        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://157.66.27.96:8080/api/") // URL gốc của API
                .addConverterFactory(GsonConverterFactory.create()) // Chuyển đổi JSON thành đối tượng Java
                .build();

        // Tạo đối tượng ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Tạo đối tượng RegisterRequest
        RegisterRequest registerRequest = new RegisterRequest(fullname, email, username, password, rePassword);

        // Gửi yêu cầu đăng ký
        Call<RegisterResponse> call = apiService.register(registerRequest);

        // Log yêu cầu gửi đi
        Log.d(TAG, "Sending register request: " + registerRequest.toString());

        // Xử lý kết quả trả về từ API
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    // Nếu đăng ký thành công, nhận token
                    RegisterResponse registerResponse = response.body();
                    Log.d(TAG, "Register successful: " + registerResponse.getMessage());

                    // Hiển thị thông báo đăng ký thành công
                    Toast.makeText(RegisterActivity.this, "Registration successful: " + registerResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    // Chuyển qua MainActivity (hoặc LoginActivity nếu cần)
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Đóng màn hình đăng ký
                } else {
                    // Nếu có lỗi khi đăng ký
                    Log.e(TAG, "Register failed: " + response.message());
                    Toast.makeText(RegisterActivity.this, "Register failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Xử lý khi kết nối thất bại
                Log.e(TAG, "Connection error: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

