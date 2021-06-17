package com.example.viagens.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.viagens.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log


const val RC_SIGN_IN = 888

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var btnLogin: SignInButton
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.sign_in_button)
        btnLogin.setOnClickListener(this)

        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if(account != null){
            updateUI()
        }
    }

    private fun updateUI() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.sign_in_button){
            sigIn()
        }
    }

    private fun sigIn() {
        val intent = mGoogleSignInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN)  {
            val task:Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            val user = task.getResult(ApiException::class.java)
            if (user != null) {
                val dadosUsuario = getSharedPreferences("dados_usuario", Context.MODE_PRIVATE)
                val editor = dadosUsuario.edit()
                editor.putString("display_name", user.displayName)
                editor.putString("family_name", user.familyName)
                editor.putString("email", user.email)
                editor.putString("photo_url", user.photoUrl.toString())
                editor.putString("id", user.id)
                editor.apply()
                updateUI()
            }
        }
    }
}