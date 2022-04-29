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
import com.example.epapp_demo.model.local.database.CategoriesDAO;
import com.example.epapp_demo.model.local.modul.Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoriesAdapter extends RecyclerView.Adapter<ShowCategoriesAdapter.ViewHolder> {

    Context context;
    private List<Categories> categories = new ArrayList<>();
    ArrayList<Categories> phanloai;
    CategoriesDAO categoriesDAO;

    private ShowCategoriesAdapter.OnStoreClickListener mListener;


    public void setOnStoreItemClickListener (ShowCategoriesAdapter.OnStoreClickListener onStoreItemClickListener){
        mListener = onStoreItemClickListener;
    }
    public ShowCategoriesAdapter(ArrayList<Categories> phanloai, Context context){
        this.phanloai = phanloai;
        this.context = context;
        categoriesDAO = new CategoriesDAO(context);
    }

    @NonNull
    @Override
    public ShowCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_categories,parent,false);
        categoriesDAO = new CategoriesDAO(context);
        return new ShowCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowCategoriesAdapter.ViewHolder holder, int position) {
        try {
            Picasso.get().load(phanloai.get(position).getHinhanh()).into(holder.ivPhanLoaiPicture);
            holder.tvNameLoai.setText(phanloai.get(position).getNameLoai());
            holder.tvMoTa.setText(phanloai.get(position).getMota());
        }catch (Exception ex){

        }

    }

    @Override
    public int getItemCount() {
        return phanloai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvNameLoai, tvMoTa;
        ImageView ivPhanLoaiPicture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameLoai = itemView.findViewById(R.id.Nameloai);
            tvMoTa = itemView.findViewById(R.id.tvMota);
            ivPhanLoaiPicture = itemView.findViewById(R.id.ivPhanLoaiPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onStoreItemClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {

        }
    }

    public interface OnStoreClickListener {
        void onStoreItemClick(int position);
//        void onPlaceFavoriteClick(Place place);
    }
}