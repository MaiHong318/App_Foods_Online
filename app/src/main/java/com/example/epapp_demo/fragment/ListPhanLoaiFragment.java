package com.example.epapp_demo.fragment;

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

import com.example.epapp_demo.DAO.CuaHangDAO;
import com.example.epapp_demo.DAO.PhanLoaiDAO;
import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.ShowCuaHangAdapter;
import com.example.epapp_demo.adapter.ShowPhanLoaiAdapter;
import com.example.epapp_demo.model.CuaHang;
import com.example.epapp_demo.model.PhanLoai;

import java.util.ArrayList;

public class ListPhanLoaiFragment extends Fragment {
    RecyclerView recyclerPhanLoai;
    PhanLoaiDAO phanLoaiDAO;
    ArrayList<PhanLoai> list = new ArrayList<>();

    public static ShowPhanLoaiAdapter showPhanLoaiAdapter;

    public ListPhanLoaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_list_phan_loai, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phanLoaiDAO = new PhanLoaiDAO(getActivity());
        recyclerPhanLoai = view.findViewById(R.id.recycler_PhanLoai);
        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerPhanLoai.setLayoutManager(place);
        list = phanLoaiDAO.getShowCat();
        showPhanLoaiAdapter = new ShowPhanLoaiAdapter(list,getActivity());
        recyclerPhanLoai.setAdapter(showPhanLoaiAdapter);
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
    }
}