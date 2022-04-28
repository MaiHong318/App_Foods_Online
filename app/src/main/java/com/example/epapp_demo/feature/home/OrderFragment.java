package com.example.epapp_demo.feature.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.OrderApdapter;
import com.example.epapp_demo.model.local.database.DonHangDAO;
import com.example.epapp_demo.model.local.modul.DonHang;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    RecyclerView rcv;
    DonHangDAO donHangDAO = new DonHangDAO(getActivity());
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static OrderApdapter donHangApdapter;
    ArrayList<DonHang> list = new ArrayList<>();

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        String i = mAuth.getCurrentUser().getUid();
        rcv = view.findViewById(R.id.rcv_DonHang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        list = donHangDAO.getDonByKhachID(""+ i +"");
        donHangApdapter = new OrderApdapter(list,getActivity());
        rcv.setAdapter(donHangApdapter);

        return view;
    }
}