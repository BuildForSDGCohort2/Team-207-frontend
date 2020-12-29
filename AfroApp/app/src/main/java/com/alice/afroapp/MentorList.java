package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MentorList extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private CircleImageView circleImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);

        RecyclerView mentor_list = (RecyclerView) findViewById(R.id.mentor_list);

        LinearLayoutManager messageLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,
                false);
        mentor_list.setLayoutManager(messageLayoutManager);
        final MentorAdapter adapter = new MentorAdapter();
        mentor_list.setAdapter(adapter);


        mFirebaseAuth = FirebaseAuth.getInstance();

//        circleImageView = (CircleImageView) findViewById(R.id.circleImage);
//        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
//        Picasso.with(this).load(imageUrl)
//               .placeholder(R.drawable.ic_account_circle_black_24dp).into(circleImageView);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backhome();
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
                Backhome();
                return true;
            case R.id.action_list:
                return  true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    public void backToProf(){
        Intent intent= new Intent(MentorList.this,AddProf.class);
        startActivity(intent);
    }

    public void Backhome()
    {
        Intent intent= new Intent(MentorList.this,MainActivity.class);
        startActivity(intent);
    }
   
}
