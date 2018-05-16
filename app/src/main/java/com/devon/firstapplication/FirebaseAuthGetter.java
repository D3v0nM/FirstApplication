package com.devon.firstapplication;

import com.google.firebase.auth.FirebaseAuth;
import Serializable;

public class FirebaseAuthGetter implements Serializable {
    private static FirebaseAuth firebaseAuth;

    public static FirebaseAuth getFirebaseAuth() {
        if(firebaseAuth != null) {
            return firebaseAuth;
        }

        return FirebaseAuth.getInstance();
    }

    public static void setFirebaseAuth(FirebaseAuth firebaseAuthLoc) {
        firebaseAuth = firebaseAuthLoc;
    }
}
