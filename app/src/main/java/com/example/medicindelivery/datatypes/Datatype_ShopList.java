package com.example.medicindelivery.datatypes;

import com.google.firebase.firestore.DocumentId;

import java.util.List;

public class Datatype_ShopList {

    @DocumentId
    public String Id;
    public String shopAddress;
    public String district;
    public String location;
    public String shopName;
    public String subDistrict;
    public String union;
    public String email;
    public List<String> DragList;

    @Override
    public String toString() {
        return "Datatype_ShopList{" +
                "Id='" + Id + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", district='" + district + '\'' +
                ", location='" + location + '\'' +
                ", shopName='" + shopName + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", union='" + union + '\'' +
                ", email='" + email + '\'' +
                ", DragList=" + DragList +
                '}';
    }


}
