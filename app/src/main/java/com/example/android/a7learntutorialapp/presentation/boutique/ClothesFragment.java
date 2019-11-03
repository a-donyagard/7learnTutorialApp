package com.example.android.a7learntutorialapp.presentation.boutique;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android.a7learntutorialapp.util.DataFakeGenerator;
import com.example.android.a7learntutorialapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClothesFragment extends Fragment {

    private RecyclerView recyclerView;
    private View view;

    public static ClothesFragment newInstance() {

        Bundle args = new Bundle();

        ClothesFragment fragment = new ClothesFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clothes, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.clothes_recycler);
        ClothesAdapter clothesAdapter = new ClothesAdapter(getActivity(), DataFakeGenerator.getClothes(getActivity()));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(clothesAdapter);

        return view;
    }

}
