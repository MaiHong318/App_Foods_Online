package com.example.epapp_demo.feature.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.KhachHangAdapter;
import com.example.epapp_demo.model.local.database.KhachHangDAO;
import com.example.epapp_demo.model.local.modul.KhachHang;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class QlyKhachHangFragment extends Fragment {

     KhachHangDAO khachHangDAO = new KhachHangDAO(getActivity());
    private FirebaseAuth mAuth;

    public static KhachHangAdapter khachHangAdapter;
    RecyclerView lv;
    ArrayList<KhachHang> list = new ArrayList<>();


    public QlyKhachHangFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_qly_khachhang, container, false);
        lv = view.findViewById(R.id.rcvQlyKH);
        mAuth = FirebaseAuth.getInstance();

        list = khachHangDAO.getAll();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);
        khachHangAdapter = new KhachHangAdapter(getActivity(),list);
        lv.setAdapter(khachHangAdapter);
        Log.d("test1", String.valueOf(list));

        return view;
    }
}