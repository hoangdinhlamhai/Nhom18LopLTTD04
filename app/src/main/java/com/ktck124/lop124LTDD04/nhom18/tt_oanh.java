package com.ktck124.lop124LTDD04.nhom18;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ktck124.lop124LTDD04.nhom18.Oanh.ThongTinCaNhan;


public class tt_oanh extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, fullNameEditText, phoneEditText, addressEditText;
    private Button saveButton;
    private TextView firebaseDataText;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt_oanh);

        // Khởi tạo Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("thongtincanhan");

        // Khởi tạo các EditText, Button và TextView
        emailEditText = findViewById(R.id.email_edit_text);
        usernameEditText = findViewById(R.id.username_edit_text);
        fullNameEditText = findViewById(R.id.tenkh);
        phoneEditText = findViewById(R.id.phone_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        saveButton = findViewById(R.id.save_button);
        firebaseDataText = findViewById(R.id.firebase_data_text);

        // Đặt sự kiện click cho nút "Lưu"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });

        // Lấy dữ liệu từ Firebase
        fetchDataFromFirebase();
    }

    private void saveUserInfo() {
        String email = emailEditText.getText().toString().trim();
        String username = usernameEditText.getText().toString().trim();
        String fullName = fullNameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        if (email.isEmpty() || username.isEmpty() || fullName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng ThongTinCaNhan
        ThongTinCaNhan userInfo = new ThongTinCaNhan(email, username, fullName, phone, address);

        // Lưu dữ liệu vào Firebase
        String userId = databaseReference.push().getKey(); // Tạo ID duy nhất
        databaseReference.child(userId).setValue(userInfo).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(tt_oanh.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(tt_oanh.this, "Lưu thông tin thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StringBuilder dataBuilder = new StringBuilder();
                for (DataSnapshot data : snapshot.getChildren()) {
                    ThongTinCaNhan info = data.getValue(ThongTinCaNhan.class);
                    if (info != null) {
                        dataBuilder.append("Email: ").append(info.getEmail()).append("\n")
                                .append("Username: ").append(info.getUsername()).append("\n")
                                .append("Họ Tên: ").append(info.getFullName()).append("\n")
                                .append("Số Điện Thoại: ").append(info.getPhone()).append("\n")
                                .append("Địa Chỉ: ").append(info.getAddress()).append("\n\n");
                    }
                }
                firebaseDataText.setText(dataBuilder.toString());
                firebaseDataText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tt_oanh.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
