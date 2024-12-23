package com.ktck124.lop124LTDD04.nhom18.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ktck124.lop124LTDD04.nhom18.Domain.Food;
import com.ktck124.lop124LTDD04.nhom18.R;

import java.util.List;

public class  ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;
    private DatabaseReference databaseReference;

    public ListFoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;

        // Khởi tạo Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ChiTietDonHang_MonAn");
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvFoodName.setText(food.getName());
        holder.tvFoodPrice.setText(food.getPrice() + "đ");

        // Load hình ảnh
//        Glide.with(context).load(food.getImage()).into(holder.ivFoodImage);

        // Thêm sự kiện click
//        holder.itemView.setOnClickListener(v -> {
//            // Tạo Intent để chuyển sang Activity chi tiết món ăn
//            Intent intent = new Intent(context, FoodDetailActivity.class);
//
//            // Truyền idMonAn qua Intent
//            intent.putExtra("idMonAn", food.getId());
//
//            // Khởi động Activity
//            context.startActivity(intent);
//        });


    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        public View addToCart;
        TextView tvFoodName, tvFoodPrice;
        ImageView ivFoodImage;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.foodName);
            tvFoodPrice = itemView.findViewById(R.id.foodPrice);
            ivFoodImage = itemView.findViewById(R.id.foodImg);
        }
    }
}
