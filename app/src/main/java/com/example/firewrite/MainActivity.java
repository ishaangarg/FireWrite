package com.example.firewrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView chatRecyclerView;
    private EditText newMessageEt;
    private Button sendBtn;
    private ChatAdapter chatAdapter;
    private ChildEventListener ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatRecyclerView = findViewById(R.id.rv_chat);
        newMessageEt = findViewById(R.id.et_new_message);
        sendBtn = findViewById(R.id.btn_send_message);
        final FirebaseDatabase instance = FirebaseDatabase.getInstance();
        chatAdapter = new ChatAdapter(this, new ArrayList<ChatData>());
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChatData chatData = new ChatData();
                chatData.setAuthorUid(String.valueOf("UID_1"));
                chatData.setMessage(newMessageEt.getText().toString());
                chatData.setCreatedTimestamp(System.currentTimeMillis());
                chatData.setServerTimestamp(ServerValue.TIMESTAMP);

                FirebaseDatabase.getInstance().getReference("messages/groups")
                        .child("0")
                        .push().setValue(chatData);

                newMessageEt.setText("");
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("default");

    }

    @Override
    protected void onResume() {
        super.onResume();
        syncMsgs();
    }

    private void syncMsgs() {
        ref = FirebaseDatabase.getInstance().getReference("messages/groups")
                .child("0").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.d(TAG, "onChildAdded: ");
                        final ChatData chatData = dataSnapshot.getValue(ChatData.class);
                        if (chatData != null) {
                            Log.d(TAG, "onChildAdded: " + dataSnapshot.getKey());
                            chatData.setId(dataSnapshot.getKey());
                            chatAdapter.addChatData(chatData);
                            //sendReadReceipt(chatData);
                        }
                        chatRecyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        Log.d(TAG, "onChildChanged: ");
                        final ChatData chatData = dataSnapshot.getValue(ChatData.class);
                        if (chatData != null) {
                            chatData.setId(dataSnapshot.getKey());
                            chatAdapter.updateChatData(chatData);
                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Log.d(TAG, "onChildRemoved: ");
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseDatabase.getInstance().getReference("messages/groups")
                .child("0").removeEventListener(ref);
    }
}
