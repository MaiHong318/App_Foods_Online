package com.example.epapp_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.model.local.database.FoodDAO;
import com.example.epapp_demo.model.local.modul.Food;
import com.example.epapp_demo.model.local.modul.Store;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    List<Food> list;
    List<Store> storeList;
    Context context;
    FoodDAO foodDAO;

    private SearchAdapter.OnStoreClickListener mListener;


    public SearchAdapter(List<Food> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setOnStoreItemClickListener (SearchAdapter.OnStoreClickListener onStoreItemClickListener){
        mListener = onStoreItemClickListener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_search_result,parent,false);
        foodDAO = new FoodDAO(context);
        return new SearchAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.tvStore.setText(list.get(position).getStoreID());
        holder.tvFood.setText(list.get(position).getNameMonAn());
        holder.tvCost.setText(Integer.toString(list.get(position).getGiaMonAn()));
        Picasso.get().load(list.get(position).getHinhAnhMonAn()).into(holder.ivFood);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStore, tvFood, tvCost;
        ImageView ivFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStore = itemView.findViewById(R.id.tv_store);
            tvFood = itemView.findViewById(R.id.tv_food);
            tvCost = itemView.findViewById(R.id.tv_cost);
            ivFood = itemView.findViewById(R.id.iv_food_picture);
        }
    }

    public interface OnStoreClickListener {
        void onStoreItemClick(int position);
    }
}
