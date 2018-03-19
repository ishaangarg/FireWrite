package com.example.firewrite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * Created by ishaan on 21/1/18.
 */

public class ChatAdapter extends RecyclerView.Adapter {

    private static final String TAG = "ChatAdapter";
    private static final int TYPE_RECEIVED = 0;
    private static final int TYPE_SENT = 1;

    private Context context;
    private ArrayList<ChatData> chatDataList;
    private FirebaseDatabase firebaseDatabase;

    public ChatAdapter(Context context, ArrayList<ChatData> chatDataList) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        this.chatDataList = chatDataList;
    }

    @Override
    public int getItemViewType(int position) {
        final ChatData chatData = chatDataList.get(position);
        if (chatData.getAuthorUid().equalsIgnoreCase("UID_1")) {
            return TYPE_SENT;
        }
        return TYPE_RECEIVED;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_RECEIVED:
                return new RecvdChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recvd_chat, parent, false));
            case TYPE_SENT:
                return new SentChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sent_chat, parent, false));
            default:
                return new RecvdChatViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recvd_chat, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SentChatViewHolder) {
            bindSentChatViewHolder(holder, position);
        } else {
            bindRecvdChatViewHolder(holder, position);
        }
    }

    private void bindSentChatViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SentChatViewHolder sentChatViewHolder = (SentChatViewHolder) holder;
        ChatData chatData = chatDataList.get(position);

        sentChatViewHolder.messageTv.setText(chatData.getMessage());

    }

    private void bindRecvdChatViewHolder(RecyclerView.ViewHolder holder, int position) {
        final RecvdChatViewHolder recvdChatViewHolder = (RecvdChatViewHolder) holder;
        ChatData chatData = chatDataList.get(position);

        recvdChatViewHolder.messageTv.setText(chatData.getMessage());
    }

    public void addChatData(@NonNull ChatData chatData) {
        final int size = chatDataList.size();
        chatDataList.add(chatData);
        notifyItemInserted(size + 1);
    }

    public void updateChatData(@NonNull ChatData chatData) {
        final int pos = chatDataList.indexOf(chatData);
        if (pos != -1) {
            chatDataList.set(pos, chatData);
            notifyItemChanged(pos);
        }
    }

    @Override
    public int getItemCount() {
        return chatDataList.size();
    }

    public class RecvdChatViewHolder extends RecyclerView.ViewHolder {

        private TextView authorNameTv;
        private TextView messageTv;

        public RecvdChatViewHolder(View itemView) {
            super(itemView);
            authorNameTv = itemView.findViewById(R.id.tv_author);
            messageTv = itemView.findViewById(R.id.tv_message);
        }

    }

    public class SentChatViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTv;

        public SentChatViewHolder(View itemView) {
            super(itemView);
            messageTv = itemView.findViewById(R.id.tv_message);

        }

    }

}
