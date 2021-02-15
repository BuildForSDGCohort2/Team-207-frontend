package com.alice.afroapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alice.afroapp.utility.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProf extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 22;
    private FirebaseDatabase mFirebaseDatabase;
    private  DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private EditText editName,editProf,editLoc,editEmail;
    private Uri filePath;
    private ImageView imageView;
    private ImageButton selectButton;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);


        //initializing views
        editName = (EditText) findViewById(R.id.editName);
        editProf = (EditText) findViewById(R.id.editProf);
        editLoc = (EditText) findViewById(R.id.editLoc);
        editEmail= (EditText) findViewById(R.id.editEmail);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);
        imageView = (ImageView) findViewById(R.id.myprof_pic);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("mentors");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveMentor();

            }
        });

        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        Picasso.with(this).load(imageUrl)
                .placeholder(R.drawable.ic_account_circle_black_24dp).
                into(circleImageView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_home:
                GoHome();
                return true;
            case R.id.action_myprofile:
                GoProf();
                return true;
            case R.id.action_list:
                GoList();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    public void GoHome(){
        Intent intent= new Intent(AddProf.this,MainActivity.class);
        startActivity(intent);
    }



    public void GoList(){
        Intent intent= new Intent(AddProf.this,MentorList.class);
        startActivity(intent);
    }


    public void SaveMentor(){
        String fullname = editName.getText().toString();
        String proficiency = editProf.getText().toString();
        String location = editLoc.getText().toString();
        String email = editEmail.getText().toString();
        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        String username = mFirebaseAuth.getCurrentUser().getDisplayName();
        Database database = new Database();

        if(mDatabaseReference.push().getKey()== null){

            database.setMentor(fullname,proficiency,location,email,imageUrl,
                    "");

        }

        else{
            Toast.makeText(AddProf.this,
                    "profile not created",Toast.LENGTH_LONG).show();

        }

    }

    public void GoProf(){
        String pushId=mDatabaseReference.push().getKey();
        Intent intent = new Intent(AddProf.this, MyProfile.class);
        intent.putExtra("pushId",pushId);
        startActivity(intent);
    }

}
