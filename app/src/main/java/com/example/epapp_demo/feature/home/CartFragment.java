package com.example.epapp_demo.feature.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CartAdapter;
import com.example.epapp_demo.model.local.database.DbHelper;
import com.example.epapp_demo.model.local.modul.ChiTietGioHang;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    RecyclerView rcv;
    Button Add;
    CartAdapter adapter;
    ArrayList<ChiTietGioHang> list = new ArrayList<>();
    DbHelper db;
    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        rcv = view.findViewById(R.id.rcvGioHang);
        Add = view.findViewById(R.id.btnAddGioHang);
        db = new DbHelper(getContext());

        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(place);

        list = db.listGioHang();
        adapter = new CartAdapter(list, getContext());
        rcv.setAdapter(adapter);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}