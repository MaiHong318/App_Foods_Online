package com.example.epapp_demo.feature.cuahang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.OrderApdapter;
import com.example.epapp_demo.model.local.database.OrderDAO;
import com.example.epapp_demo.model.local.modul.Order;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class StoreActivitiesFragment extends Fragment {
    RecyclerView rcvSA;
    OrderDAO orderDAO = new OrderDAO(getActivity());
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public static OrderApdapter donHangApdapter;
    ArrayList<Order> list = new ArrayList<>();
    public StoreActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_store_activities, container, false);

        String i = mAuth.getCurrentUser().getUid();
        rcvSA = view.findViewById(R.id.rcv_store_acti);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvSA.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        list = orderDAO.getDonByCuaHangID(""+ i +"");
        donHangApdapter = new OrderApdapter(list,getActivity());
        rcvSA.setAdapter(donHangApdapter);
        return view;
    }
}