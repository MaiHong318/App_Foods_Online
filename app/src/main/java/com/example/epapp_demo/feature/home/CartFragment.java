package com.example.epapp_demo.feature.home;

import static com.example.epapp_demo.model.local.database.DbHelper.giohang;

import static java.lang.String.valueOf;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.example.epapp_demo.model.local.modul.CartDetails;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    RecyclerView rcv;
    Button Add;
    CartAdapter adapter;
    ArrayList<CartDetails> list = new ArrayList<>();
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

        final LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(place);

        list = db.listGioHang();
        adapter = new CartAdapter(list, getContext());
        rcv.setAdapter(adapter);
        Add.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference mdata = firebaseDatabase.getReference("Đơn hàng");
                if (list.isEmpty()) {
                    Toast.makeText(getContext(), "Bạn chưa thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    mdata.setValue(list, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            list.forEach((item) -> {
                                db.delete(String.valueOf(item.getMonAnId()));

                            });
                            rcv.setAdapter(null);
                            Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
        });

        return view;
    }

}