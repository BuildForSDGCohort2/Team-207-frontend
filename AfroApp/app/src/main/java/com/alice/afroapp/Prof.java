package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

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

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Prof extends AppCompatActivity {
    private TextView fullname,proficiency,location,email;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private ChildEventListener mChildEventListerner;
    private ValueEventListener mValueEventListener;
    private ArrayList<Mentor> mentors;
    private  Mentor mentor;
    private  String imageUrl;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_code_black_18dp);

        fullname = (TextView) findViewById(R.id.fullname_text);
        proficiency = (TextView) findViewById(R.id.profciency_text);
        location = (TextView) findViewById(R.id.loc_text);
        email = (TextView) findViewById(R.id.email_addtext);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Mentors");


        Intent intent = getIntent();
        String Currentname = intent.getStringExtra("name");
        final Query itemFilter = mDatabaseReference.orderByChild("fullname")
                .equalTo(Currentname);


        itemFilter.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot:snapshot.getChildren()){
                fullname.setText(postSnapshot.child("fullname").getValue(String.class).toString());
                proficiency.setText(postSnapshot.child("proficiency").getValue(String.class).toString());
                location.setText(postSnapshot.child("location").getValue(String.class).toString());
                email.setText(postSnapshot.child("email").getValue(String.class).toString());
                String imageUrl = postSnapshot.child("imageUrl").getValue(String.class).toString();
                    Picasso.with(Prof.this).load(imageUrl)
                            .placeholder(R.drawable.ic_account_circle_black_24dp).into(circleImageView);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mentor_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_back_home:
                backHome();
                return true;
            case R.id.action_edit:
                return  true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void GoToList(){
        Intent intent= new Intent(Prof.this,MentorList.class);
        startActivity(intent);
    }

    public void backHome(){
        Intent intent= new Intent(Prof.this,MainActivity.class);
        startActivity(intent);
    }

    public void Editprofile(View view) {
//        if(mentor.getId()!= null){
//            mDatabaseReference.child(mentor.getId()
//            ).setValue(mentor);
//        }
//        else {
//            Toast.makeText(this,"cant be edited.",Toast.LENGTH_LONG).show();
//
//        }

    }

    public void goHome(View view) {
        Intent intent= new Intent(Prof.this,MentorList.class);
        startActivity(intent);
    }
}
