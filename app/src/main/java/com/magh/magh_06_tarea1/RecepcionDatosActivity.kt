package com.magh.magh_06_tarea1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RecepcionDatosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recepcion_datos)

        val nombre = intent.getStringExtra("EXTRA_NOMBRE")
        val apellido = intent.getStringExtra("EXTRA_APELLIDO")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val genero = intent.getStringExtra("EXTRA_GENERO")
        val contraseña = intent.getStringExtra("EXTRA_CONTRASEÑA")

        val lblInfo = findViewById<TextView>(R.id.lblInfo)
        lblInfo.text = "Nombre: $nombre $apellido\n\n" +
                "Correo: $email\n\n" +
                "Género: $genero\n\n" +
                "Contraseña: $contraseña"

        //Botón Regresar
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("EXTRA_ES_VALIDO",true)
            }
            setResult(RESULT_OK,resultIntent)
            finish()
        }
    }
}