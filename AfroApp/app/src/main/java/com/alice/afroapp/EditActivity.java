package com.alice.afroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alice.afroapp.utility.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditActivity extends AppCompatActivity {
    private EditText editProf;
    private EditText editProfic;
    private EditText editLoc;
    private EditText editEmail;
    private static FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private CircleImageView circleImageView;
    private Mentor mentor;
    private String editId;
    private Database database;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().getThemedContext();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);

        editProf = (EditText) findViewById(R.id.editProf);
        editProfic =(EditText)findViewById(R.id.editProficiency);
        editLoc = (EditText) findViewById(R.id.editLoc);
        editEmail = (EditText)findViewById(R.id.editEmail);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);

        Intent intent = getIntent();
        String pushId = intent.getStringExtra("pushId");



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


                    String setProf = postSnapshot.child("fullname")
                            .getValue(String.class).toString();
                    editProf.setText(setProf);

                    String setProfic = postSnapshot.child("proficiency")
                            .getValue(String.class).toString();
                    editProfic.setText(setProfic);

                    String setLoc = postSnapshot.child("location")
                            .getValue(String.class).toString();
                    editLoc.setText(setLoc);

                    String setEmail = postSnapshot.child("email")
                            .getValue(String.class).toString();
                    editEmail.setText(setEmail);

                    String key = postSnapshot.getKey().toString();

                    editId = key;

                    String imageUrl = postSnapshot.child("imageUrl").
                            getValue(String.class).toString();
                    Picasso.with(EditActivity.this).load(imageUrl)
                            .placeholder(R.drawable.
                                    ic_account_circle_black_24dp)
                            .into(circleImageView);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(EditActivity.this,
                        "Connection error",Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public void editProfile(View view) {
        String fullname = editProf.getText().toString();
        String proficiency = editProf.getText().toString();
        String location = editLoc.getText().toString();
        String email = editEmail.getText().toString();
        String imageUrl = mFirebaseAuth.getCurrentUser().getPhotoUrl().toString();
        String username = mFirebaseAuth.getCurrentUser().getDisplayName();

        Mentor mentor = new Mentor(editId,fullname,proficiency,location
                ,email,imageUrl,"");

        DatabaseReference aliceRef = mDatabaseReference.child(editId);
        Map<String, Object> aliceUpdates = new HashMap<>();
        aliceUpdates.put("proficiency", proficiency);

        aliceRef.updateChildren(aliceUpdates);

//        mDatabaseReference.child("Mentors").child(editId).setValue(mentor);



        Toast.makeText(EditActivity.this,"name updated"
                ,Toast.LENGTH_LONG).show();


    }
}
