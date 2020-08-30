package com.example.sqlitetutorial.AdapterClass;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sqlitetutorial.DataBaseClass.DataBaseHelper;
import com.example.sqlitetutorial.Modal.User;
import com.example.sqlitetutorial.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private static final String TAG ="UserAdapter".getClass().getName() ;
    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.showallrecord,null);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = mUsers.get(position);
        holder.userholderID.setText(String.valueOf(user.getId()));
        holder.userholdername.setText(user.getName());
        holder.userholderpassword.setText(user.getPassword());
        holder.deleteRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                LayoutInflater inflater = LayoutInflater.from(mContext.getApplicationContext());
                v= inflater.inflate(R.layout.customalertdialog,null);
                final EditText getID=v.findViewById(R.id.customid);
                Button delete=v.findViewById(R.id.deleteid);
                builder.setView(v);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = getID.getText().toString();
                        DataBaseHelper helper = new DataBaseHelper(mContext.getApplicationContext());
                        boolean res=helper.deleteRecord(Integer.parseInt(id));
                        if (res==true)
                        {
                            Toast.makeText(mContext.getApplicationContext(),"Record delete Successfully",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(mContext.getApplicationContext()," No Record delete ",Toast.LENGTH_SHORT).show();
                            alertDialog.show();

                        }
                    }
                });
            }
        });
        holder.updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Update Record");
                LayoutInflater inflater = LayoutInflater.from(mContext);
                v=inflater.inflate(R.layout.updatealert_dialog,null);
                final EditText username , password,id;
                Button update;
                id=v.findViewById(R.id.update_id);
                username=v.findViewById(R.id.update_username);
                password=v.findViewById(R.id.update_password);
                ImageView imageView = v.findViewById(R.id.update_imageview);
                update=v.findViewById(R.id.update_login);

                builder.setView(v);
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper helper = new DataBaseHelper(mContext.getApplicationContext());
                        User user = new User();

                        boolean res = helper.updateRecord(id.getText().toString(),username.getText().toString(),password.getText().toString(),"abc");
                        if (res==true)
                        {

                            Toast.makeText(mContext.getApplicationContext(),"Successfully Update Value"+user.getId(),Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(mContext.getApplicationContext(),"Problem Update Value",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }
                });
            }
        });
        holder.mImageView.setImageURI(Uri.parse(user.getImage()));



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        private TextView userholderID,userholdername,userholderpassword;
        private ImageView mImageView;
        private Button deleteRecord,updateRecord;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            userholderID=itemView.findViewById(R.id.recyclarveiwid);
            userholdername=itemView.findViewById(R.id.recyclarveiwusername);
            userholderpassword=itemView.findViewById(R.id.recyclarveiwpassword);
            mImageView=itemView.findViewById(R.id.recyclarveiwimage);
            deleteRecord=itemView.findViewById(R.id.recyclarveiwdelete);
            updateRecord=itemView.findViewById(R.id.recyclarveiwupdate);

        }
    }
}

