package com.example.epapp_demo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.epapp_demo.R;
import com.example.epapp_demo.model.local.database.CategoriesDAO;
import com.example.epapp_demo.model.local.modul.Categories;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HomeCategoriesAdapter extends RecyclerView.Adapter<HomeCategoriesAdapter.ViewHolder> {

    List<Categories> list;
    Context context;
    CategoriesDAO categoriesDAO;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    public HomeCategoriesAdapter(List<Categories> list, Context context) {
        this.list = list;
        this.context = context;
        categoriesDAO = new CategoriesDAO(context);
    }

    public HomeCategoriesAdapter(FragmentActivity activity) {

    }

    @NonNull
    @Override
    public HomeCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_one_categories, parent, false);
        categoriesDAO = new CategoriesDAO(context);
        return new HomeCategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoriesAdapter.ViewHolder holder, int position) {

        if(TextUtils.isEmpty(list.get(position).getHinhanh())) {
            // Load default image
            holder.iv.setImageResource(R.drawable.slider3);
        } else {
            Picasso.get().load(list.get(position).getHinhanh()).into(holder.iv);
        }



        holder.name.setText(list.get(position).getNameLoai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_name);
            iv = itemView.findViewById(R.id.category_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }


}


//    private List<Category> categories = new ArrayList<>();
//    private Context context;
//    private OnItemClickListener mListener;
//    public void setOnItemClickListener(OnItemClickListener listener){
//        mListener = listener;
//    }
//    public CategoriesAdapter(Context context){
//        this.context = context;
//        String[] categoryNames = {"Cơm Phần", "Trà Sữa",
//                "Gà Rán", "Bún/Phở","Ăn Vặt", "Món Hàn"};
//
//        int images_array[] = {
//                R.drawable.rice,
//                R.drawable.milk,
//                R.drawable.ic_fried_chicken,
//                R.drawable.ic_noodles,
//                R.drawable.ic_snack,
//                R.drawable.ic_koreanfood,
//        };
//
//        for (int i = 0; i < 6; i++){
//            Category category = new Category(categoryNames[i], images_array[i]);
//            categories.add(category);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(context).inflate(R.layout.theloai_one_item, viewGroup, false);
//        ItemHolder holder = new ItemHolder(view, mListener);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
//        final Category category =  categories.get(position);
//        holder.mCategoryName.setText(category.getCategoryName());
//        holder.mCategoryImage.setImageResource(category.getCategoryDrawable());
//    }
//
//    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        public TextView mCategoryName;
//        public ImageView mCategoryImage;
//        public View mView;
//
//
//        public ItemHolder(@NonNull View itemView, final OnItemClickListener listener) {
//            super(itemView);
//            mView = itemView;
//            mCategoryName = itemView.findViewById(R.id.category_name);
//            mCategoryImage = itemView.findViewById(R.id.category_photo);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION){
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void onClick(View v) {}
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return categories.size();
//    }