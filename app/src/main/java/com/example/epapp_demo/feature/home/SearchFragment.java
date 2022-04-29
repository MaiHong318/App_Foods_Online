package com.example.epapp_demo.feature.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.SearchAdapter;
import com.example.epapp_demo.feature.admin.ListCategoriesFragment;
import com.example.epapp_demo.model.local.modul.Food;
import com.example.epapp_demo.model.local.modul.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    RecyclerView rcvStoreSuggest;
    RelativeLayout btnBack;

    SearchAdapter searchAdapter;
    ArrayList<Food> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        rcvStoreSuggest = view.findViewById(R.id.rcvStoreSuggest);
        btnBack = view.findViewById(R.id.btn_back);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvStoreSuggest.setLayoutManager(layoutManager);

        Bundle bundle = this.getArguments();
        String search = bundle.getString("search");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("MonAn");
        databaseReference.orderByChild("nameMonAn").startAt(search).endAt(search+"\uf8ff")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            list.add(ds.getValue(Food.class));
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("SearchFragment", "cancel", databaseError.toException());

                    }
                });

        searchAdapter =new SearchAdapter(list, getContext());
        rcvStoreSuggest.setAdapter(searchAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new HomeFragment());
                transaction.commit();
            }
        });
        return view;
    }
}
