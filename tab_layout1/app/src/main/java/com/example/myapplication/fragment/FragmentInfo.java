package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
//import com.example.th2.UpdateDeleteActivity;
//import com.example.th2.adapter.RecycleViewAdapter;
//import com.example.th2.dl.SQLiteHelper;
//import com.example.th2.model.Item;


public class FragmentInfo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        recyclerView = view.findViewById(R.id.recycleView);
//        adapter = new RecycleViewAdapter();
//        db = new SQLiteHelper(getContext());
//        Item i = new Item(1, "Mua dien thoai", "Mua sam", "500", "16/04/2023");
//        db.insertItem(i);
//        List<Item> items = db.getAll();
//
//        adapter.setItems(items);
//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);
//        adapter.setItemListener(this);
//
//
//    }
//
//    @Override
//    public void onItemClick(View view, int position) {
//        Item item = adapter.getItem(position);
//        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
//        intent.putExtra("item", item);
//        startActivity(intent);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        List<Item> items = db.getAll();
//        adapter.setItems(items);
//    }
}
