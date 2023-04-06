package com.example.letschat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letschat.R;
import com.example.letschat.chatdetail;
import com.example.letschat.models.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    ArrayList<Users> arrayList;
    Context ctx;

    public UserAdapter(ArrayList<Users> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(ctx).inflate(R.layout.activity_showusers,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users user=arrayList.get(position);
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.defaultuser).into(holder.imageView);
        holder.uname.setText(user.getUser_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx, chatdetail.class);
                intent.putExtra("uid",user.getUser_id());
                intent.putExtra("uname",user.getUser_name());
                intent.putExtra("uprofile",user.getProfile());
                ctx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView uname,lastmsg;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.dp);
            uname=itemView.findViewById(R.id.username);
            lastmsg=itemView.findViewById(R.id.lastmsg);
            v=itemView;
        }
    }
}
