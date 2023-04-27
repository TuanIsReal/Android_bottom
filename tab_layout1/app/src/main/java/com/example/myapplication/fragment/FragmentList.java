package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.UpdateDeleteActivity;
import com.example.myapplication.adapter.RecycleViewAdapter;
import com.example.myapplication.dal.SQLiteHelper;
import com.example.myapplication.model.Item;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentList extends Fragment implements RecycleViewAdapter.ItemListener{
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        db = new SQLiteHelper(getContext());
//        Item i = new Item(1, "abcxyz", "abc xyz mnop", "16/04/2023", "hoàn thành", true);
//        db.insertItem(i);
        List<Item> items = db.getAll();
        adapter = new RecycleViewAdapter(items);
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);


    }

    @Override
    public void onItemClick(View view, int position) {
        Item item = adapter.getItem(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> items = db.getAll();
        adapter.setItems(items);
    }
}
