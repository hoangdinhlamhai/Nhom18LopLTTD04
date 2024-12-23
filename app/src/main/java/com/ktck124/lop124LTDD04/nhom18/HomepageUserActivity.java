package com.ktck124.lop124LTDD04.nhom18;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ktck124.lop124LTDD04.nhom18.Adapter.ListFoodAdapter;
import com.ktck124.lop124LTDD04.nhom18.Adapter.SliderAdapter;
import com.ktck124.lop124LTDD04.nhom18.Domain.Food;
import com.ktck124.lop124LTDD04.nhom18.Domain.ThanhVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomepageUserActivity extends AppCompatActivity {
    private Dialog dialog;
    private ViewPager viewPager;
    private int[] images = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};
    private int currentPageCounter = 0;
    private BottomNavigationView bottomNavigationView;

//    //danh sách món ăn
    private RecyclerView rvFoodList;
    private List<Food> foodList;
    private ListFoodAdapter foodAdapter;
    private DatabaseReference databaseReference;

    //danh sach danh mục nằm ngang
//    private RecyclerView horizontalRecyclerView;
//    private CategoryHomepageAdapter categoryHomepageAdapter;
//    private List<Category> danhMucList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_user);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SliderAdapter(images, HomepageUserActivity.this));


        final Handler handler = new Handler();
        final Runnable update = () -> {
            if (currentPageCounter == images.length) {
                currentPageCounter = 0;
            }
            viewPager.setCurrentItem(currentPageCounter++, true);
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 2500, 2500);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_nav1);

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new HomeFragment())
//                    .commit();
//            bottomNavigationView.setSelectedItemId(R.id.home_nav1); // Set default selected item
//        }


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.home_nav1) {
                // Replace only the fragment for Home
//                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.menu_nav1) {
                // Start category_user Activity for Menu
//                startActivity(new Intent(HomepageUserActivity.this, category_user.class));
                //return true; // No fragment change here, handled by activity switch

            }
            else if (itemId == R.id.cart_nav1) {
                // Start category_user Activity for Menu
//                startActivity(new Intent(HomepageUserActivity.this, CartActivity.class));
                //return true; // No fragment change here, handled by activity switch}
            }else if (itemId == R.id.person_nav1) {
                // Start profile_user Activity for User
//                startActivity(new Intent(HomepageUserActivity.this, me_user.class));
                //return true; // No fragment change here, handled by activity switch
            }


//            if (selectedFragment != null) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, selectedFragment)
//                        .commit();
//            }
            return true;
        });
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_nav1);


//        setupUIInteractions();

        // danh sach mon an
        // Khởi tạo RecyclerView
        rvFoodList = findViewById(R.id.rvFoodList);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>(); // Khởi tạo danh sách rỗng trước
        // Thiết lập Adapter sau
        foodAdapter = new ListFoodAdapter(this, foodList);
        rvFoodList.setAdapter(foodAdapter);

        // Kết nối Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("ThanhVien");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                foodList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                        String idMonAn = data.getKey();
                        String tenMonAn = data.child("tenMonAn").getValue(String.class);
                        int donGia = data.child("donGia").getValue(Integer.class);
                        String moTa = data.child("moTa").getValue(String.class);
                        String hinhAnh = data.child("hinhAnh").getValue(String.class);

                        Food food = new Food(idMonAn, tenMonAn, moTa, donGia, hinhAnh);
                        foodList.add(food);
                }

                foodAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi
                Log.e("FirebaseError", "Lỗi Firebase: " + error.getMessage());
            }
        });


    }

}
