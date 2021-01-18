package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

import com.alice.afroapp.utility.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfile extends AppCompatActivity {
    private TextView fullname,proficiency,location,email;
    private static FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListerner;
    private ValueEventListener mValueEventListener;
    private FirebaseAuth mFirebaseAuth;
    private ImageView imageView;
    private CircleImageView circleImageView;
    private String editId;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);

        fullname = (TextView) findViewById(R.id.fullname_text);
        proficiency = (TextView) findViewById(R.id.profciency_text);
        location = (TextView) findViewById(R.id.loc_text);
        email = (TextView) findViewById(R.id.email_addtext);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);

        Intent intent = getIntent();
        String pushId = intent.getStringExtra("pushId");
        editId = pushId;


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Mentors");

        String displayName = mFirebaseAuth.getCurrentUser().getDisplayName();
        final Query nameFilter = mDatabaseReference.orderByChild("fullname")
                .equalTo(displayName);


        nameFilter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                    fullname.setText(postSnapshot.child("fullname").getValue
                            (String.class).toString());
                    proficiency.setText(postSnapshot.child("proficiency").
                            getValue(String.class).toString());
                    location.setText(postSnapshot.child("location").
                            getValue(String.class).toString());
                    email.setText(postSnapshot.child("email").
                            getValue(String.class).toString());

                    String imageUrl = postSnapshot.child("imageUrl").
                            getValue(String.class).toString();
                    Picasso.with(MyProfile.this).load(imageUrl)
                            .placeholder(R.drawable.
                                    ic_account_circle_black_24dp)
                            .into(circleImageView);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MyProfile.this,
                        "Connection error",Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_goback:
                backHome();
                return true;
            case R.id.action_delete:
                deleteProfile();
            case R.id.action_edit:
                editMentor();
                return  true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    public void backHome(){
        Intent intent= new Intent(MyProfile.this,
                MainActivity.class);
        startActivity(intent);
    }


    public void editMentor(){
        Intent intent = new Intent(MyProfile.this,
                EditActivity.class);
        intent.putExtra("pushId",editId);
        startActivity(intent);

    }

    public void deleteProfile(){
        if(mFirebaseAuth.getCurrentUser().getDisplayName() != null){
            Intent intent = getIntent();
            String pushId = intent.getStringExtra("pushId");

            mDatabaseReference.child(pushId).setValue(null);

        }
        else
        {

            Toast.makeText(MyProfile.this,
                    "Delete denied",Toast.LENGTH_LONG)
                    .show();
        }

    }

 
}
