package com.desisuci.retrofitgson;

import com.google.gson.annotations.SerializedName;

public class Kontak {
    private String id;

    @SerializedName("nama")
    private String name;

    private String email;

    @SerializedName("nohp")
    private String phone;

    @SerializedName("alamat")
    private String address;

    public Kontak(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    //GET
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    //SET
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}


