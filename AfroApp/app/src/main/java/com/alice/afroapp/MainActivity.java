package com.alice.afroapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alice.afroapp.utility.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {
    private Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.action_profile:
                Viewprofile();
                return true;
            case R.id.action_post:
                Post();
                Toast.makeText(this,"Post a question",Toast.LENGTH_LONG).show();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    public void Viewlist(MenuItem item) {
        Intent intent= new Intent(MainActivity.this,Listquestions.class);
        startActivity(intent);
        Toast.makeText(this,"Questions list.",Toast.LENGTH_LONG).show();

    }

    public void Viecomments(MenuItem item) {
        Intent intent= new Intent(MainActivity.this,Listcomments.class);
        startActivity(intent);
        Toast.makeText(this,"Comments list.",Toast.LENGTH_LONG).show();
    }

    public void Post(){
        Intent intent= new Intent(MainActivity.this,Post.class);
        startActivity(intent);
    }

    public void Viewprofile(){
//        Intent intent= new Intent(MainActivity.this,Profile.class);
//        startActivity(intent);
        Toast.makeText(this,"View profile",Toast.LENGTH_LONG).show();
    }


    //  to test the database class
    private void testDatabase(){
        db = new Database();
        db.setUser("1","oo","jkdlfjdl@df.com");
        db.setQuestion("1","what?");
        db.setSolution("1","1","yes");
    }
}
