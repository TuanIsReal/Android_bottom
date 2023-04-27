package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Item;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> items;
    private ItemListener itemListener;

    public RecycleViewAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name.setText(item.getName());
        holder.ct.setText(item.getCt());
        holder.date.setText(item.getDate());
        holder.status.setText(item.getStatus());
        holder.countries.setText(Integer.toString(item.getCountries()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public Item getItem(int position){
        return items.get(position);
    }

    public ItemListener getItemListener() {
        return itemListener;
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, ct, date, status, countries;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvName);
            ct = view.findViewById(R.id.tvCauTruc);
            date = view.findViewById(R.id.tvDate);
            status = view.findViewById(R.id.tvStatus);
            countries = view.findViewById(R.id.tvCountries);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }


}
