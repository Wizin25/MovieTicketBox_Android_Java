package com.example.movieticketbox.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieticketbox.ApiService;
import com.example.movieticketbox.MainActivity;
import com.example.movieticketbox.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button signInButton, signUpButton;
    private EditText emailInput, passwordInput;

    private static final String TAG = "LoginActivity";  // Thêm tag để log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String username = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                // Log các giá trị để kiểm tra
                Log.d(TAG, "Username: " + username);
                Log.d(TAG, "Password: " + password);

                // Kiểm tra nếu trường hợp email hoặc password rỗng
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi API đăng nhập
                loginUser(username, password);
            }
        });
        // Xử lý sự kiện khi nhấn nút "Sign Up"
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở màn hình đăng ký (RegisterActivity)
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String username, String password) {
        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://157.66.27.96:8080/api/") // URL gốc của API
                .addConverterFactory(GsonConverterFactory.create()) // Chuyển đổi JSON thành đối tượng Java
                .build();

        // Tạo đối tượng ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Tạo đối tượng LoginRequest
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Gửi yêu cầu login
        Call<LoginResponse> call = apiService.login(loginRequest);

        // Log yêu cầu gửi đi
        Log.d(TAG, "Sending login request: " + loginRequest.toString());

        // Xử lý kết quả trả về từ API
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    // Nếu đăng nhập thành công, chuyển đến MainActivity
                    LoginResponse loginResponse = response.body();
                    Log.d(TAG, "Login successful: " + loginResponse.getMessage());

                    // Hiển thị thông báo đăng nhập thành công
                    Toast.makeText(LoginActivity.this, "Login successful: " + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    // Chuyển qua MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Đóng màn hình đăng nhập
                } else {
                    // Nếu có lỗi khi đăng nhập
                    Log.e(TAG, "Login failed: " + response.message());
                    Toast.makeText(LoginActivity.this, "Login failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý khi kết nối thất bại
                Log.e(TAG, "Connection error: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
