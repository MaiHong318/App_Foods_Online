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
import com.example.epapp_demo.model.local.database.PhanLoaiDAO;
import com.example.epapp_demo.model.local.modul.PhanLoai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowPhanLoaiAdapter  extends RecyclerView.Adapter<ShowPhanLoaiAdapter.ViewHolder> {

    Context context;
    private List<PhanLoai> phanLoais = new ArrayList<>();
    ArrayList<PhanLoai> phanloai;
    PhanLoaiDAO phanLoaiDAO;

    private ShowPhanLoaiAdapter.OnStoreClickListener mListener;


    public void setOnStoreItemClickListener (ShowPhanLoaiAdapter.OnStoreClickListener onStoreItemClickListener){
        mListener = onStoreItemClickListener;
    }
    public ShowPhanLoaiAdapter(ArrayList<PhanLoai> phanloai, Context context){
        this.phanloai = phanloai;
        this.context = context;
        phanLoaiDAO = new PhanLoaiDAO(context);
    }

    @NonNull
    @Override
    public ShowPhanLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.loai_one_item,parent,false);
        phanLoaiDAO = new PhanLoaiDAO(context);
        return new ShowPhanLoaiAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowPhanLoaiAdapter.ViewHolder holder, int position) {
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
