package com.example.nangjanggopro.ui.slideshow;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nangjanggopro.MaterialAdapter;
import com.example.nangjanggopro.QuickMenu;
import com.example.nangjanggopro.R;
import com.example.nangjanggopro.WordItemData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView recyclerMaterial;
    private MaterialAdapter adapter;
    private ArrayList<WordItemData> list = new ArrayList<>();
    private Button Material_plus;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
       final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerMaterial = (RecyclerView)root.findViewById(R.id.recyclerMaterial);
        adapter = new MaterialAdapter(getActivity(),list);
        recyclerMaterial.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMaterial.setAdapter(adapter);

        Material_plus = (Button)root.findViewById(R.id.Material_plus);
        Material_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(container.getContext());
                View v = LayoutInflater.from(container.getContext()).inflate(R.layout.material_box,null,false);
                builder.setView(v);
                final Button input_submit = (Button)v.findViewById(R.id.input_submit);
                final EditText input_title = (EditText)v.findViewById(R.id.input_material);
                final EditText input_now = (EditText)v.findViewById(R.id.input_now);
                final EditText input_after = (EditText)v.findViewById(R.id.input_after);

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String getTime = sdf.format(date);
                input_now.setText(getTime);


                final AlertDialog dialog = builder.create();
                        input_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       final String strTitle = input_title.getText().toString();
                        final String strNow = input_now.getText().toString();
                        final String strAfter = input_after.getText().toString();
                        final String strBtn;

                        Toast.makeText(root.getContext(), "재료를 꾸욱 누르면 삭제됩니다", Toast.LENGTH_SHORT).show();

                        long now2 = System.currentTimeMillis();
                        Date date2 = new Date(now2);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        String getTime2 = sdf.format(date2);


                        final Integer IntDate = Integer.parseInt(getTime2)-Integer.parseInt(strAfter);
                        if(IntDate == 0){
                            strBtn = "오늘까지";

                        }
                        else if(IntDate >3 && IntDate<0){
                            strBtn ="마감임박";

                        }
                        else if(IntDate <0){
                            strBtn="여유";

                        }else {strBtn="상한음식";
                          };



                     WordItemData wid = new WordItemData(20,strTitle, strNow, strAfter, strBtn);
                     list.add(0,wid);
                      adapter.notifyItemInserted(0);
                        dialog.dismiss();
                    }
                });

                        dialog.show();
            }
        });


        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }



}