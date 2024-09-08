package com.example.telegame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //----Se uso chatGPT para realizar esta parte de codigo que da funcionalidad al boton jugara para enviar el nombre y comenzar el juego----
        EditText nombreEditText = findViewById(R.id.editTextText);
        Button jugarButton = findViewById(R.id.button);

        // Configura el listener del botón
        jugarButton.setOnClickListener(v -> {
            String nombreIngresado = nombreEditText.getText().toString();

            //Este toast se usa como comprobacion que se esta capturando el texto ingresado en el nombre
            Toast.makeText(MainActivity.this, "Nombre ingresado: " + nombreIngresado, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this,JuegoActivity.class);
            intent.setData(Uri.parse(nombreIngresado));
            startActivity(intent);

        });
        //------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /*public void abrirJuego(View view){
        Intent intent = new Intent(this,JuegoActivity.class);
        intent.setData(Uri.parse(nombre));
        startActivity(intent);
    }*/
}