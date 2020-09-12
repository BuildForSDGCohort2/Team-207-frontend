package com.alice.afroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
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
            case R.id.action_edit:
                GoList();
                return true;
            case R.id.action_home:
                Gohome();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
//function to go view list questions
    public void GoList(){
        Intent intent= new Intent(Post.this,Listquestions.class);
        startActivity(intent);
    }

    public void Gohome(){
        Intent intent= new Intent(Post.this,MainActivity.class);
        startActivity(intent);
    }

}
