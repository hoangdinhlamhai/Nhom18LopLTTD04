//package com.ktck124.lop124LTDD04.nhom18.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.ktck124.lop124LTDD04.nhom18.Domain.Food;
//import com.ktck124.lop124LTDD04.nhom18.Domain.ThanhVien;
//import com.ktck124.lop124LTDD04.nhom18.R;
//
//import java.util.List;
//
//public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder>{
//    private Context context;
//    private List<ThanhVien> thanhVienList;
//
//    public ThanhVienAdapter(Context context, List<ThanhVien> thanhVienList) {
//        this.context = context;
//        this.thanhVienList = thanhVienList;
//
//        // Khởi tạo Firebase Realtime Database
////        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
////        databaseReference = firebaseDatabase.getReference("ChiTietDonHang_MonAn");
//    }
//
//    @NonNull
//    @Override
//    public ThanhVienAdapter.ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_thanhvien, parent, false);
//        return new ThanhVienAdapter.ThanhVienViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ThanhVienAdapter.ThanhVienViewHolder holder, int position) {
//        ThanhVien thanhVien = thanhVienList.get(position);
//        holder.hoten.setText(thanhVien.getHoten());
//        holder.masv.setText(thanhVien.getMasv());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return thanhVienList.size();
//    }
//
//    public static class FoodViewHolder extends RecyclerView.ViewHolder {
//        TextView tvFoodName, tvFoodPrice;
//        ImageView ivFoodImage;
//
//        public FoodViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvFoodName = itemView.findViewById(R.id.foodName);
//            tvFoodPrice = itemView.findViewById(R.id.foodPrice);
//            ivFoodImage = itemView.findViewById(R.id.foodImg);
//        }
//    }
//}
