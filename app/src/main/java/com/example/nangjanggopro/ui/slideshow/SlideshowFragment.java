package com.example.nangjanggopro.ui.slideshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nangjanggopro.MaterialAdapter;
import com.example.nangjanggopro.R;
import com.example.nangjanggopro.WordItemData;

import java.util.ArrayList;
import java.util.Dictionary;

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
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        recyclerMaterial = (RecyclerView)root.findViewById(R.id.recyclerMaterial);
        adapter = new MaterialAdapter(getActivity(),list);
        recyclerMaterial.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerMaterial.setAdapter(adapter);




        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

}