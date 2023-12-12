package com.example.a14_firebaseaccess.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.firebaseaccess.R
import com.example.firebaseaccess.ui.users.SignupActivity

import com.google.firebase.auth.FirebaseAuth


import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class LoginActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()

    private lateinit var btnAutenticar: Button
    private lateinit var txtEmail: EditText
    private lateinit var txtContra: EditText
    private lateinit var txtRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnAutenticar = findViewById(R.id.btnAutenticar)
        txtEmail = findViewById(R.id.txtEmail)
        txtContra = findViewById(R.id.txtContra)
        txtRegister = findViewById(R.id.txtRegister)

        txtRegister.setOnClickListener {
            goToSignup()
        }

        btnAutenticar.setOnClickListener {
            if(txtEmail.text.isNotEmpty() && txtContra.text.isNotEmpty()){
                auth.signInWithEmailAndPassword(txtEmail.text.toString(), txtContra.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        //Register the data into the local storage
                        val prefe = this.getSharedPreferences("appData", Context.MODE_PRIVATE)

                        //Create editor object for write app data
                        val editor = prefe.edit()

                        //Set editor fields with the new values
                        editor.putString("email", txtEmail.text.toString())
                        editor.putString("contra", txtContra.text.toString())

                        //Write app data
                        editor.commit()

                        // call back to main activity
                        Intent().let {
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }else{
                        showAlert("Error","Al autenticar el usuario")
                    }
                }
            }else{
                showAlert("Error","El correo electrónico y contraseña no pueden estar vacíos")
            }
        }
    }
    private fun goToSignup() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    private fun showAlert(titu:String, mssg: String){
        val diagMessage = AlertDialog.Builder(this)
        diagMessage.setTitle(titu)
        diagMessage.setMessage(mssg)
        diagMessage.setPositiveButton("Aceptar", null)

        val diagVentana: AlertDialog = diagMessage.create()
        diagVentana.show()
    }

}