package com.example.epapp_demo.feature.admin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.adapter.CuaHangAdapter;
import com.example.epapp_demo.model.local.database.StoreDAO;
import com.example.epapp_demo.model.local.modul.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StoreFragment extends Fragment {

    StoreDAO storeDAO = new StoreDAO(getActivity());
    private FirebaseAuth mAuth;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String userID;
    public static CuaHangAdapter cuaHangAdapte;
    RecyclerView lv;
    ArrayList<Store> list = new ArrayList<>();
    FloatingActionButton add;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_store, container, false);
        lv = view.findViewById(R.id.rcvQlyCH);
        add = view.findViewById(R.id.btnAddCH);
        mAuth = FirebaseAuth.getInstance();

        list = storeDAO.getAll();
        cuaHangAdapte = new CuaHangAdapter(list,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(layoutManager);
        lv.setAdapter(cuaHangAdapte);
        Log.d("test1", String.valueOf(list));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view1 = getLayoutInflater().inflate(R.layout.add_store_dialog,null);
                final EditText tenCH = view1.findViewById(R.id.edtNameStore);
                final EditText mailCH = view1.findViewById(R.id.edtMailStore);
                final EditText passCH = view1.findViewById(R.id.edtPassStore);
                builder.setView(view1);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        final String tenCH1 = tenCH.getText().toString();
                        final String mailCH1 = mailCH.getText().toString();
                        final String passCH1 = passCH.getText().toString();

                        mAuth.createUserWithEmailAndPassword(mailCH1, passCH1)
                                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Log.v(mailCH1,passCH1);
                                            Toast.makeText(getActivity(), "Đăng kí thành công",
                                                    Toast.LENGTH_SHORT).show();
                                            userID = mAuth.getCurrentUser().getUid();
                                            Store s = new Store(userID,mailCH1,passCH1,null, tenCH1,"",5.0,"",null,null,1);
                                            mData.child("CuaHang").child(userID).push();
                                            mData.child("CuaHang").child(userID).setValue(s);
                                            Log.d("Status","Success");


                                        } else {
                                            Log.v(mailCH1,passCH1);
                                            Toast.makeText(getActivity(), "Nhập đúng định dạng email, mật khẩu 6 kí tự",
                                                    Toast.LENGTH_SHORT).show();
                                            Log.d("Status","Fail");
                                        }
                                    }
                                });
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setView(view1);
                builder.show();
            }
        });
        return view;
    }
}