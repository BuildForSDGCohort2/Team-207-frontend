package com.alice.afroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
                    editProf.setText(postSnapshot.child("fullname").getValue
                            (String.class).toString());
                    editProfic.setText(postSnapshot.child("proficiency").
                            getValue(String.class).toString());
                    editLoc.setText(postSnapshot.child("location").
                            getValue(String.class).toString());
                    editEmail.setText(postSnapshot.child("email").
                            getValue(String.class).toString());
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
}
