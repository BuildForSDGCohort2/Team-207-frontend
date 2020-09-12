package com.alice.afroapp;

import android.app.Activity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseUtil {

    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mReference;
    private static Activity caller;
    private static final int RC_SIGN_IN=123;
    public  static FirebaseUtil firebaseUtil;

    private FirebaseUtil(){}

    public static void openFbReference(String ref, final Activity callerActivity) {
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            caller = callerActivity;
            mReference = mFirebaseDatabase.getReference().child(ref);

        }

    }

//    private static void Signin(){
//        // Choose authentication providers
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build());
//

// Create and launch sign-in intent
//        caller.startActivityForResult(
//                AuthUI.getInstance()
//                        .createSignInIntentBuilder()
//                        .setAvailableProviders(providers)
//                        .build(),
//                RC_SIGN_IN);
//    }

//    public static  void attachListener(){
//        mAuth.addAuthStateListener(mAuthListener);
//    }

//    public static void detachListener(){
//        mAuth.removeAuthStateListener(mAuthListener);
//    }


}
