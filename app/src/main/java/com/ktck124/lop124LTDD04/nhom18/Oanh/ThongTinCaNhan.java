package com.ktck124.lop124LTDD04.nhom18.Oanh;



public class ThongTinCaNhan {
    private String email;
    private String username;
    private String fullName;
    private String phone;
    private String address;

    // Constructor mặc định
    public ThongTinCaNhan() {
    }

    // Constructor đầy đủ
    public ThongTinCaNhan(String email, String username, String fullName, String phone, String address) {
        this.email = email;
        this.username = username;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter và Setter nếu cần
}