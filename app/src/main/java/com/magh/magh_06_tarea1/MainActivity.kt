package com.magh.magh_06_tarea1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNombre = findViewById<EditText>(R.id.txtNombre)
        val txtApellido = findViewById<EditText>(R.id.txtApellido)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtContraseña = findViewById<EditText>(R.id.txtContraseña)
        val txtContraseña2 = findViewById<EditText>(R.id.txtContraseña2)

        //Spinner
        val comGenero = findViewById<Spinner>(R.id.comGenero)
        val datos = arrayListOf("Género","Masculino","Femenino","Otro","Prefiero no decir")

        ArrayAdapter(this, android.R.layout.simple_spinner_item, datos).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            comGenero.adapter=it
        }

        val btnEnviar = findViewById<Button>(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            //Validación
            val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()
            if(txtNombre.text.toString().trim()==""){
                Snackbar.make(btnEnviar,"Es necesario capturar el nombre.", 1000).show()
            }else if(txtApellido.text.toString().trim()=="") {
                Snackbar.make(btnEnviar, "Es necesario capturar el apellido.", 1000).show()
            }else if(txtEmail.text.toString().trim()=="") {
                Snackbar.make(btnEnviar, "Es necesario capturar el correo.", 1000).show()
            }else if(!emailRegex.matches(txtEmail.text.toString().trim())){
                Snackbar.make(btnEnviar, "Correo con formato incorrecto.", 1000).show()
            }else if(comGenero.selectedItemPosition==0){
                Snackbar.make(btnEnviar, "Es necesario seleccionar una opción de género.", 1000).show()
            }else if(txtContraseña.text.toString().trim()==""){
                Snackbar.make(btnEnviar, "Es necesario capturar una contraseña.", 1000).show()
            }else if(txtContraseña2.text.toString().trim()==""){
                Snackbar.make(btnEnviar, "Es necesario confirmar la contraseña.", 1000).show()
            }else if(txtContraseña.text.toString().trim()!=txtContraseña2.text.toString().trim()){
                Snackbar.make(btnEnviar, "Las contraseñas no coinciden.", 1000).show()
            }else {
                //Envio de datos
                val sendIntent = Intent(this, RecepcionDatosActivity::class.java).apply {
                    putExtra("EXTRA_NOMBRE", txtNombre.text.toString())
                    putExtra("EXTRA_APELLIDO", txtApellido.text.toString())
                    putExtra("EXTRA_EMAIL", txtEmail.text.toString())
                    putExtra("EXTRA_GENERO", comGenero.selectedItem.toString())
                    putExtra("EXTRA_CONTRASEÑA", txtContraseña.text.toString())
                }
                //startActivity(sendIntent)
                resultRegister.launch(sendIntent)
            }
        }

    }

    private val resultRegister = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK){
            Toast.makeText(this,"ES_VALIDO = ${result.data?.getBooleanExtra("EXTRA_ES_VALIDO",false)}",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"CANCELADO",Toast.LENGTH_SHORT).show()
        }
    }

}