package com.example.epapp_demo.feature.cuahang;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.ShowFoodAdapter;
import com.example.epapp_demo.adapter.SliderAdapter1;
import com.example.epapp_demo.model.local.database.FoodDAO;
import com.example.epapp_demo.model.local.modul.Food;
import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeStoreFragment extends Fragment {
    SliderView sliderView;
    RecyclerView rcvOrder, rcvMenu;
    TextView tvShowMenu;
    public static ShowFoodAdapter foodAdapter;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FoodDAO foodDAO = new FoodDAO(getActivity());
    ArrayList<Food> list = new ArrayList<>();
    public HomeStoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_store, container, false);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sliderView = view.findViewById(R.id.imgSlider);
        rcvOrder = (RecyclerView) view.findViewById(R.id.rcvOrder);
        tvShowMenu = view.findViewById(R.id.tv_show_menu);
        rcvMenu = view.findViewById(R.id.rcvMenu);


        LinearLayoutManager llmTrending = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rcvOrder.setLayoutManager(llmTrending);
        String id = mAuth.getCurrentUser().getUid();
       // rcvOrder.setAdapter(foodAdapter);


        LinearLayoutManager place = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvMenu.setLayoutManager(place);
        list = foodDAO.getAllMenu(id);
        foodAdapter = new ShowFoodAdapter(list, getActivity());
        rcvMenu.setAdapter(foodAdapter);

        //custom slider
        SliderAdapter1 adapter = new SliderAdapter1(getActivity());
        sliderView.setSliderAdapter(adapter);
        //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINDEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        tvShowMenu.setOnClickListener(view1 -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_1, new FoodOfStoreFragment());
            transaction.commit();
        });
    }
}