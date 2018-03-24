package com.lemon.realmdatabase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.lemon.realmdatabase.R;
import com.lemon.realmdatabase.concurrent.FragmentCallback;
import com.lemon.realmdatabase.utility.util.Item;
import com.lemon.realmdatabase.utility.util.ItemAdapter;
import com.lemon.realmdatabase.utility.util.impl.RecyclerListener;
import com.lemon.realmdatabase.utility.util.inf.ClickListener;

import java.util.List;

/**
 * Created by lemon on 3/23/2018.
 */

@SuppressWarnings({"DefaultFileTemplate", "unused", "FieldCanBeLocal"})
public class ListFragment extends Fragment implements ClickListener {
    private View view;
    private RecyclerView recyclerView;
    private List<Item> items;
    private FragmentCallback fragmentCallback;
    private String title;
    private ItemAdapter itemAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.list_frag,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        itemAdapter=new ItemAdapter(items);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerListener(this,getActivity(),recyclerView));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallback= (FragmentCallback) context;
        items=fragmentCallback.getItems();
        itemAdapter=new ItemAdapter(items);
    }

    @Override
    public void onClick(View view, int position) {
        showToast("Single Click On Position: "+position);
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View view, int position) {
        showToast("Long Click On Position: "+position);
    }
}
