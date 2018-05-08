package `in`.co.massacre.firebasetest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.support.annotation.NonNull
import android.widget.Toast
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential




class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN = 123
    private var mAuth:FirebaseAuth ?= null
    var mVerificationId:String?=null
    var mResendToken:PhoneAuthProvider.ForceResendingToken?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mAuth=FirebaseAuth.getInstance()

        fab.setOnClickListener { view ->
Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        if(mAuth!!.currentUser!=null){
            Toast.makeText(this,"Login is already there!!"+mAuth!!.currentUser!!.phoneNumber,Toast.LENGTH_LONG)
                    .show()
        }
        else {
            val providers = Arrays.asList(
                    AuthUI.IdpConfig.FacebookBuilder().setPermissions(Arrays.asList("user_friends"))
                            .setPermissions(Arrays.asList("email"))
                            .build(),
                    AuthUI.IdpConfig.GoogleBuilder().build(),
                    AuthUI.IdpConfig.PhoneBuilder().build())
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setIsSmartLockEnabled(true)
                            .setAvailableProviders(providers)
                            .setLogo(R.mipmap.ic_launcher)
                            .build(),
                    RC_SIGN_IN)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("resultCode",""+resultCode)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            Log.e("resultCode",""+resultCode)
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                // ...
            } else {
                // ...
                Log.e("Login error",""+response?.error)
            }
        }
    }
    fun signOut(view: View){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(task: Task<Void>) {
                        // ...
                        finish()
                    }
                })
    }
    fun deleteAccount(view:View){
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Deletion succeeded
                        finish()
                    } else {
                        // Deletion failed
                    }
                }

    }


}
