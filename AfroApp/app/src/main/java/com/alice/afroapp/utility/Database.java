package com.alice.afroapp.utility;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



// class to get and set data to firebase database.
public class Database {
    private DatabaseReference mFirebaseDatabase;

    public Database(){

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void setUser (String userId, String userName, String e_mail){

        mFirebaseDatabase.child("Users").child(userId).child("username").setValue(userName);
        mFirebaseDatabase.child("Users").child(userId).child("email").setValue(e_mail);

    }

    public void setQuestion (String questionId, String title){

        mFirebaseDatabase.child("Conversations").child("Questions").child(questionId).child("title").setValue(title);
    }

    public void setSolution (String questionId, String solutionId, String solution){

        mFirebaseDatabase.child("Conversations").child("Questions").child(questionId).child("Solutions").child(solutionId).setValue(solution);
    }
}
