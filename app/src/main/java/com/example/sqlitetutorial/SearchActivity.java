package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlitetutorial.DataBaseClass.DataBaseHelper;
import com.example.sqlitetutorial.Modal.User;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private EditText search;
    private TextView id,name,password;
    private List<User> mList = new ArrayList<>();
    private User user;
    private DataBaseHelper mHelper = new DataBaseHelper(SearchActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search=findViewById(R.id.search_item);
        id=findViewById(R.id.search_id);
        name=findViewById(R.id.search_name);
        password=findViewById(R.id.search_password);
    }

    public void setSearchData(View view) {
        mList=mHelper.findData(search.getText().toString());
        for (int i=0;i<mList.size();i++)
        {
             user = mList.get(i);
        }
        id.setText(String.valueOf(user.getId()));
        name.setText(user.getName());
        password.setText(user.getPassword());





    }
}