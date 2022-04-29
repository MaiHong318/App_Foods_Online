package com.example.epapp_demo.feature.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.example.epapp_demo.R;

import com.example.epapp_demo.adapter.ShowCategoriesAdapter;
import com.example.epapp_demo.feature.home.HomeFragment;
import com.example.epapp_demo.model.local.database.CategoriesDAO;
import com.example.epapp_demo.model.local.modul.Categories;

import java.util.ArrayList;

public class ListCategoriesFragment extends Fragment {
    RecyclerView rcvCategories;
    CategoriesDAO categoriesDAO;
    RelativeLayout btnBack;
    ArrayList<Categories> list = new ArrayList<>();

    public static ShowCategoriesAdapter showCategoriesAdapter;

    public ListCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list_categories, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesDAO = new CategoriesDAO(getActivity());
        rcvCategories = view.findViewById(R.id.rcvCategories);
        btnBack = view.findViewById(R.id.btn_back);
        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvCategories.setLayoutManager(place);
        list = categoriesDAO.getShowCat();
        showCategoriesAdapter = new ShowCategoriesAdapter(list,getActivity());
        rcvCategories.setAdapter(showCategoriesAdapter);
//        showPhanLoaiAdapter.setOnStoreItemClickListener(new ShowCuaHangAdapter.OnStoreClickListener() {
//            @Override
//            public void onStoreItemClick(int position) {
//                PhanLoai phanLoai = list.get(position);
//                String idCat = phanLoai.getLoaiID();
//                ShowMenuStoreFragment newFragment = new ShowMenuStoreFragment(idStore);
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.frame_layout, newFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,new HomeFragment());
                transaction.commit();
            }
        });
    }
}