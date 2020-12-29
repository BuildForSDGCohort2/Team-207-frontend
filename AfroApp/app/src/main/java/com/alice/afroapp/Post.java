package com.alice.afroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alice.afroapp.utility.Database;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Post extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuth;
    private EditText editQuestiion;
    private ImageButton postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().getThemedContext();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);


        editQuestiion  = (EditText) findViewById(R.id.editQuestion);
        postButton = (ImageButton)findViewById(R.id.postButton);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Questions");
        mDatabaseReference.keepSynced(true);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postQuestion();
            }
        });
        editQuestiion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == editQuestiion){
                    clean();
                }

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_question_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_home:
                GoHome();
                return true;
            case R.id.action_questions_list:
                GoList();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
//function to go view list questions
    public void GoList(){
        Intent intent= new Intent(Post.this,QuestionsActivity.class);
        startActivity(intent);
    }

    public void GoHome(){
        Intent intent= new Intent(Post.this,MainActivity.class);
        startActivity(intent);
    }

    private void clean() {
        editQuestiion.setText("");
    }

    public void postQuestion(){
        String title = editQuestiion.getText().toString();
        String username = mFirebaseAuth.getCurrentUser().getDisplayName();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        Database datbase = new Database();
        if(editQuestiion != null ){
            datbase.setQuestion(username,title);
        }
        else {
            Toast.makeText(this,"enter question",Toast.LENGTH_LONG).show();

        }
//        Intent intent= new Intent(Post.this,QuestionsActivity.class);
//        startActivity(intent);


    }

}
