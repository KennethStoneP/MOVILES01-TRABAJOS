package com.example.pdfs.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pdfs.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), DownloadListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pdfAdapter: PdfAdapter
    private val pdfList = mutableListOf<PdfModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Passing 'this' as the context to the PdfAdapter constructor
        pdfAdapter = PdfAdapter(pdfList, this)
        recyclerView.adapter = pdfAdapter

        cargarPDFs()
    }

    private fun cargarPDFs() {
        val db = Firebase.firestore
        db.collection("PDF's_modules").get().addOnSuccessListener { result ->
            for (document in result) {
                val nombre = document.getString("names") ?: ""
                val url = document.getString("url") ?: ""
                pdfList.add(PdfModel(nombre, url))
            }
            pdfAdapter.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            // Handle error
        }
    }

    // Método para mostrar un mensaje
    private fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onDownloadStarted(pdfName: String) {
        mostrarMensaje("Descarga iniciada para $pdfName")
    }
}
