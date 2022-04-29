package com.example.epapp_demo.feature.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CartAdapter;
import com.example.epapp_demo.adapter.OrderApdapter;
import com.example.epapp_demo.model.local.database.OrderDAO;
import com.example.epapp_demo.model.local.modul.CartDetails;
import com.example.epapp_demo.model.local.modul.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    RecyclerView rcv;
//    OrderDAO orderDAO = new OrderDAO(getActivity());
//    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//
//    public static OrderApdapter donHangApdapter;

    ArrayList<Order> list = new ArrayList<>();


    ArrayList<CartDetails> listCar=new ArrayList<>();
    CartAdapter adapter;

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

//        String i = mAuth.getCurrentUser().getUid();
        rcv = view.findViewById(R.id.rcv_DonHang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        //mAuth = FirebaseAuth.getInstance();

//        list = orderDAO.getDonByKhachID(""+ i +"");
//        donHangApdapter = new OrderApdapter(list,getActivity());
        adapter = new CartAdapter(listCar, getContext());
        rcv.setAdapter(adapter);
        getListCart();

        return view;
    }

    public void getListCart(){
        FirebaseDatabase mAuth = FirebaseDatabase.getInstance();
        DatabaseReference mdata=mAuth.getReference("Đơn hàng");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   CartDetails cartDetails=dataSnapshot1.getValue(CartDetails.class);
                   listCar.add(cartDetails);
              }
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Thất bại",Toast.LENGTH_SHORT).show();
            }
        });
    }
}