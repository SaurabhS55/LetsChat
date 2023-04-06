package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.letschat.adapters.MessageAdapter;
import com.example.letschat.databinding.ActivityChatdetailBinding;
import com.example.letschat.models.MessageModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatdetail extends AppCompatActivity {
FirebaseAuth auth;
FirebaseDatabase database;
final ArrayList<MessageModel> m=new ArrayList<>();
ActivityChatdetailBinding chatdetailBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatdetailBinding=ActivityChatdetailBinding.inflate(getLayoutInflater());
        setContentView(chatdetailBinding.getRoot());
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        Intent intent=getIntent();
        String senderid=auth.getUid();
        String receiverid=intent.getStringExtra("uid");
        String uname=intent.getStringExtra("uname");
        String prof=intent.getStringExtra("uprofile");
        chatdetailBinding.uname.setText(uname);
        Picasso.get().load(prof).placeholder(R.drawable.defaultuser).into(chatdetailBinding.dp1);
        chatdetailBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chatdetail.this,MainActivity.class));
            }
        });
        String senderroom=senderid+receiverid;
        String receiverroom=receiverid+senderid;
        final MessageAdapter messageAdapter=new MessageAdapter(m,chatdetail.this);
        chatdetailBinding.chatview.setAdapter(messageAdapter);
        chatdetailBinding.chatview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        database.getReference().child("chat").child(senderroom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    MessageModel model=snapshot1.getValue(MessageModel.class);
                    m.add(model);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        chatdetailBinding.sendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=chatdetailBinding.typemsg.getText().toString();
                final MessageModel messageModel=new MessageModel(msg,senderid);
                messageModel.setTimestamp(new Date().getTime());
                chatdetailBinding.typemsg.setText("");
                database.getReference().child("chat").child(senderroom).push().setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        database.getReference().child("chat").child(receiverroom).push().setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
                    }
                });
            }
        });
    }
}