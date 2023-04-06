package com.example.letschat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letschat.R;
import com.example.letschat.adapters.FragmentAdapter;
import com.example.letschat.adapters.UserAdapter;
import com.example.letschat.databinding.FragmentChatBinding;
import com.example.letschat.models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends Fragment {
    ArrayList<Users> arrayList=new ArrayList<>();
    FragmentChatBinding chatBinding;
    FirebaseDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        chatBinding=FragmentChatBinding.inflate(inflater, container, false);
        UserAdapter adapter=new UserAdapter(arrayList,getContext());
        chatBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        chatBinding.rv.setAdapter(adapter);

        db=FirebaseDatabase.getInstance();
        db.getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users users=dataSnapshot.getValue(Users.class);
                    users.setUser_id(dataSnapshot.getKey());
                    arrayList.add(users);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return chatBinding.getRoot();
    }
}