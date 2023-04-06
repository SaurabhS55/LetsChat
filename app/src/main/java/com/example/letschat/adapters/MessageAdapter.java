package com.example.letschat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.letschat.R;
import com.example.letschat.models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter{
    ArrayList<MessageModel> messageModels;
    Context ctx;

    public MessageAdapter(ArrayList<MessageModel> messageModels, Context ctx) {
        this.messageModels = messageModels;
        this.ctx = ctx;
    }
    public int SENDER_VIEW=1;
    public int RECEIVER_VIEW=2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW){
            return new SenderViewHolder(LayoutInflater.from(ctx).inflate(R.layout.sample_sender,parent,false));
        }
        else{
            return new ReceiverViewHolder(LayoutInflater.from(ctx).inflate(R.layout.sample_receiver,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageModels.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW;
        }
        else{
            return RECEIVER_VIEW;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel model=messageModels.get(position);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).sendermsg.setText(model.getMessage());
        }
        else{
            ((ReceiverViewHolder)holder).receivermsg.setText(model.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView receivermsg;
        TextView receivertime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receivermsg=itemView.findViewById(R.id.receivertext);
            receivertime=itemView.findViewById(R.id.receivedtime);
        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView sendermsg;
        TextView sendertime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sendermsg=itemView.findViewById(R.id.sendertext);
            sendertime=itemView.findViewById(R.id.sendertime);
        }
    }
}
