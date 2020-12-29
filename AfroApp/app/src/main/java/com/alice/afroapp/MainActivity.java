package com.alice.afroapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.alice.afroapp.ui.home.HomeFragment;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuth;
    private ImageButton postButton;
    private static final int RC_SIGN_IN=123;
    private CircleImageView circleImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        mFirebaseAuth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sidetitle);




//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController);
//        NavigationUI.setupWithNavController(navView, navController);
        circleImageView = (CircleImageView) findViewById(R.id.circleImage);

        mAuth = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()== null){
                        Signin();                 }
            }
        };

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
            case R.id.action_home:
                Toast.makeText(this,"Home.",
                        Toast.LENGTH_LONG).show();
                return true;
                case R.id.action_signout:
                FirebaseAuth.getInstance()
                        .signOut();
                return  true;
            case R.id.action_profile:
                showProfile();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }


        private void Signin(){
            // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


 //Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                Toast.makeText(this,"SignIn failed.",Toast.LENGTH_LONG).show();

            }
        }
    }


    //stateListener for Firebase
    public void attachListener(){
        mFirebaseAuth.addAuthStateListener(mAuth);
    }

    public void detachListener(){
        mFirebaseAuth.removeAuthStateListener(mAuth);
    }

    //function to go to listActivity
    public void ViewList(MenuItem item) {
        Intent intent= new Intent(MainActivity.this,QuestionsActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Questions list.",Toast.LENGTH_LONG).show();
    }

    //function to view comments
    public void ViewComments(MenuItem item) { Intent intent= new Intent(
            MainActivity.this,Post.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
         attachListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detachListener();

    }


    public void MentorSignUp(MenuItem item) {
        Intent intent= new Intent(MainActivity.this,AddProf.class);
        startActivity(intent);
    }

    public void Post(){
        Intent intent= new Intent(MainActivity.this,Post.class);
        startActivity(intent);
    }

    public void Posting(View view) {
        Intent intent= new Intent(MainActivity.this,Post.class);
        startActivity(intent);
    }

    public void showProfile(){
        Intent intent= new Intent(MainActivity.this,MyProfile.class);
        startActivity(intent);
    }


}
