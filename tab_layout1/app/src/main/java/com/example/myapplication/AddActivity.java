package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.dal.SQLiteHelper;
import com.example.myapplication.databinding.ActivityAddBinding;
import com.example.myapplication.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btCancel.setOnClickListener(this);
        binding.btUpdate.setOnClickListener(this);
        binding.eDate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view==binding.eDate ){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view == binding.btUpdate){
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
                Item i = new Item(name, ct,d,s,c);
                SQLiteHelper db = new SQLiteHelper(this);
                db.insertItem(i);
                finish();
            }
        }
    }
}