package com.devon.firstapplication;

//class SignInActivity extends MainActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

//    // Google Firebase instance variables
//    private FirebaseAuth mFirebaseAuth;
//    private GoogleApiClient mGoogleApiClient;
//    private static final int RC_SIGN_IN = 9001;
//  private SignInButton mSignInButton;
//    private static final String TAG = SignInActivity.class.getSimpleName();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Assign fields
//        mSignInButton = findViewById(R.id.sign_in_button);
//
//        // Set click listeners
//        mSignInButton.setOnClickListener(this);
//
//        // Initialize Google FirebaseAuth
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.sign_in_button:
//                signIn();
//                break;
//            default:
//        }
//    }
//
//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // Google Sign-In was successful, authenticate with Firebase
//                GoogleSignInAccount account = result.getSignInAccount();
//                assert account != null;
//                firebaseAuthWithGoogle(account);
//            } else {
//                // Google Sign-In failed
//                Log.e(TAG, "Google Sign-In failed.");
//            }
//        }
//    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(
//                this, task -> {
//            Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
//
//            // If sign in fails, display a message to the user. If sign in succeeds
//            // the auth state listener will be notified and logic to handle the
//            // signed in user can be handled in the listener.
//            if (!task.isSuccessful()) {
//                Log.w(TAG, "signInWithCredential", task.getException());
//                Toast.makeText(SignInActivity.this, "Authentication failed.",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                                                                        //Maybe change this to profileFrag
//                startActivity(new Intent(SignInActivity.this, SecondActivity.class));
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
//        // be available.
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
//    }
//}
