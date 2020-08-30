package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sqlitetutorial.AdapterClass.UserAdapter;
import com.example.sqlitetutorial.DataBaseClass.DataBaseHelper;
import com.example.sqlitetutorial.Modal.User;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private static final String TAG ="ShowActivity".getClass().getName() ;
    private TextView users,pass,id;
    private DataBaseHelper mHelper = new DataBaseHelper(ShowActivity.this);
    private List<User> mList;
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        connction();
        mList=mHelper.read(ShowActivity.this);
        mAdapter= new UserAdapter(ShowActivity.this,mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(ShowActivity.this,1));
        mRecyclerView.setAdapter(mAdapter);

    }

    private void connction() {
        mList = new ArrayList<>();
        mRecyclerView=findViewById(R.id.recyclarview);
    }
}