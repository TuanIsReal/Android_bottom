package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.dal.SQLiteHelper;
import com.example.myapplication.databinding.ActivityUpdateDeleteBinding;
import com.example.myapplication.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityUpdateDeleteBinding binding;
    private Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btCancel.setOnClickListener(this);
        binding.btUpdate.setOnClickListener(this);
        binding.btDelete.setOnClickListener(this);
        binding.eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item = (Item)intent.getSerializableExtra("item");
        binding.eName.setText(item.getName());
        binding.eCountries.setText(String.valueOf(item.getCountries()));
        binding.eDate.setText(item.getDate());
        String[] values = item.getCt().split(";");
        for (String value : values) {
            System.out.println(value);
            if (value.trim().equalsIgnoreCase("ARN")) {
                binding.checkbox1.setChecked(true);
            }if (value.trim().equalsIgnoreCase("protein-s")) {
                binding.checkbox2.setChecked(true);
            }if (value.trim().equalsIgnoreCase("protein-n")) {
                binding.checkbox3.setChecked(true);
            }
        }
        if (item.getStatus().equalsIgnoreCase("Có vắc xin")){
            binding.option1.setChecked(true);
        } else if (item.getStatus().equalsIgnoreCase("Chưa có vắc xin")) {
            binding.option2.setChecked(true);
        }
    }


    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if(view==binding.eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    String date = "";
                    if(month > 8 ){
                        date = dayOfMonth + "/" + (month+1) + "/" + year;
                    }else{
                        date = dayOfMonth + "/0" + (month+1) + "/" + year;

                    }
                    binding.eDate.setText(date);
                }
            }, year, month, day);
            dialog.show();
        }
        if(view == binding.btCancel){
            finish();
        }
        if(view == binding.btDelete){
            int id = item.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thông báo");
            builder.setMessage("Bạn có chắc muốn xóa " + item.getName()+" không?");
            builder.setIcon(R.drawable.remove);
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.deleteItem(id);
                    finish();
                }

            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(view == binding.btUpdate){
            int id =item.getId();
            String name = binding.eName.getText().toString();
            int c = Integer.parseInt(binding.eCountries.getText().toString());
            String d = binding.eDate.getText().toString();
            String s = "";
            if (binding.option1.isChecked()){
                s = binding.option1.getText().toString();
            }else if (binding.option2.isChecked()){
                s = binding.option2.getText().toString();
            }
            StringBuilder sb = new StringBuilder();
            if (binding.checkbox1.isChecked()){
                sb.append(binding.checkbox1.getText().toString() + "; ");
            }
            if (binding.checkbox2.isChecked()){
                sb.append(binding.checkbox2.getText().toString() + "; ");
            }
            if (binding.checkbox3.isChecked()){
                sb.append(binding.checkbox3.getText().toString() + "; ");
            }
            String ct = sb.toString();
            if(!name.isEmpty()){
                Item i = new Item(id,name,ct,d,s,c);
                db.updateItem(i);
                finish();
            }
        }
    }
}