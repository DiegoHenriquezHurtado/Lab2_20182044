package com.example.telegame;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JuegoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button botonIniciarNuevoJuego;
    private ImageView cabeza, torzo, brazoDerecho, brazoIzquierdo, piernaDerecha, piernaIzquierda;
    private String palabraSecreta = obtenerPalabraAleatoria();
    private StringBuilder palabraMostrada;
    private int errores;
    private final int MAX_ERRORES = 6;
    private long tiempoInicio;
    Uri nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_juego);

        //Se obtiene el nombre del jugador
        Intent intent = getIntent();
        nombre = intent.getData();

        ImageView botonEstadisticas = findViewById(R.id.estadistica);

        botonEstadisticas.setOnClickListener(v -> {
            Intent intent1 = new Intent(this,EstadisticasActivity.class);
            intent1.setData(Uri.parse(nombre.toString()));
            startActivity(intent1);

        });

        // Imágenes del ahorcado
        cabeza = findViewById(R.id.imageViewCabeza);
        torzo = findViewById(R.id.imageViewTorzo);
        brazoDerecho = findViewById(R.id.imageViewBrazoDerecho);
        brazoIzquierdo = findViewById(R.id.imageViewBrazoIzquierdo);
        piernaDerecha = findViewById(R.id.imageViewPiernaDerecha);
        piernaIzquierda = findViewById(R.id.imageViewPiernaIzquierda);

        //Boton para iniciar nuevo juevo
        botonIniciarNuevoJuego = findViewById(R.id.buttonNuevoJuego);
        botonIniciarNuevoJuego.setOnClickListener(v -> iniciarNuevoJuego());

        // Inicializa el juego
        iniciarJuego();

        Button buttonA = findViewById(R.id.buttonA);
        Button buttonB = findViewById(R.id.buttonB);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonD = findViewById(R.id.buttonD);
        Button buttonE = findViewById(R.id.buttonE);
        Button buttonF = findViewById(R.id.buttonF);
        Button buttonG = findViewById(R.id.buttonG);
        Button buttonH = findViewById(R.id.buttonH);
        Button buttonI = findViewById(R.id.buttonI);
        Button buttonJ = findViewById(R.id.buttonJ);
        Button buttonK = findViewById(R.id.buttonK);
        Button buttonL = findViewById(R.id.buttonL);
        Button buttonM = findViewById(R.id.buttonM);
        Button buttonN = findViewById(R.id.buttonN);
        Button buttonO = findViewById(R.id.buttonO);
        Button buttonP = findViewById(R.id.buttonP);
        Button buttonQ = findViewById(R.id.buttonQ);
        Button buttonR = findViewById(R.id.buttonR);
        Button buttonS = findViewById(R.id.buttonS);
        Button buttonT = findViewById(R.id.buttonT);
        Button buttonU = findViewById(R.id.buttonU);
        Button buttonV = findViewById(R.id.buttonV);
        Button buttonW = findViewById(R.id.buttonW);
        Button buttonX = findViewById(R.id.buttonX);
        Button buttonY = findViewById(R.id.buttonY);
        Button buttonZ = findViewById(R.id.buttonZ);

        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);
        buttonE.setOnClickListener(this);
        buttonF.setOnClickListener(this);
        buttonG.setOnClickListener(this);
        buttonH.setOnClickListener(this);
        buttonI.setOnClickListener(this);
        buttonJ.setOnClickListener(this);
        buttonK.setOnClickListener(this);
        buttonL.setOnClickListener(this);
        buttonM.setOnClickListener(this);
        buttonN.setOnClickListener(this);
        buttonO.setOnClickListener(this);
        buttonP.setOnClickListener(this);
        buttonQ.setOnClickListener(this);
        buttonR.setOnClickListener(this);
        buttonS.setOnClickListener(this);
        buttonT.setOnClickListener(this);
        buttonU.setOnClickListener(this);
        buttonV.setOnClickListener(this);
        buttonW.setOnClickListener(this);
        buttonX.setOnClickListener(this);
        buttonY.setOnClickListener(this);
        buttonZ.setOnClickListener(this);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Se usa ChatGPT para obtener este metodo que realiza lo siguiente:Captura el "text" del boton para reconocer la letra del boton seleccionado y apartir de eso
    //realizamos el procesamiento para el juego.
    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        String letraSeleccionada = clickedButton.getText().toString();

        // Aquí puedes manejar cualquier lógica que quieras hacer con la letra seleccionada
        manejarSeleccionLetra(letraSeleccionada.charAt(0));
    }

    private void iniciarJuego() {
        palabraSecreta = obtenerPalabraAleatoria();
        palabraMostrada = new StringBuilder("_".repeat(palabraSecreta.length()));
        errores = 0;

        // Oculta las imágenes del ahorcado
        cabeza.setVisibility(View.INVISIBLE);
        torzo.setVisibility(View.INVISIBLE);
        brazoDerecho.setVisibility(View.INVISIBLE);
        brazoIzquierdo.setVisibility(View.INVISIBLE);
        piernaDerecha.setVisibility(View.INVISIBLE);
        piernaIzquierda.setVisibility(View.INVISIBLE);

        tiempoInicio = System.currentTimeMillis();

        mostrarPalabra();
    }

    private void iniciarNuevoJuego() {
        iniciarJuego();
    }

    // Método para obtener una palabra aleatoria
    private String obtenerPalabraAleatoria() {
        List<String> palabras = Arrays.asList("FIBRA", "REDES", "ANTENA", "PROPA", "CLOUD", "TELECO");
        Random random = new Random();
        int indiceAleatorio = random.nextInt(palabras.size());

        return palabras.get(indiceAleatorio);
    }

    private void manejarSeleccionLetra(char letra) {

        if (palabraSecreta.contains(String.valueOf(letra))) {
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (palabraSecreta.charAt(i) == letra) {
                    palabraMostrada.setCharAt(i, letra);
                }
            }
        } else {
            errores++;
            mostrarAhorcado(errores);
        }

        mostrarPalabra();
        verificarEstadoJuego();
    }

    private void mostrarPalabra() {
        TextView textViewPalabra = findViewById(R.id.textViewPalabra);
        textViewPalabra.setText(palabraMostrada.toString());
    }

    private void verificarEstadoJuego() {
        if (palabraMostrada.indexOf("_") == -1) {
            // El jugador ha ganado
            long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
            Toast.makeText(this, "¡Ganaste! Tiempo: " + tiempoTranscurrido + " s", Toast.LENGTH_SHORT).show();
            iniciarNuevoJuego(); //Reiniciamos el juego
        } else if (errores >= MAX_ERRORES) {
            // El jugador ha perdido
            Toast.makeText(this, "Perdiste. La palabra era: " + palabraSecreta, Toast.LENGTH_SHORT).show();
            iniciarNuevoJuego(); //Reiniciamos el jeugo
        }
    }

    private void mostrarAhorcado(int errores) {
        if (errores==1) {
            cabeza.setVisibility(View.VISIBLE);
        } else if (errores==2) {
            torzo.setVisibility(View.VISIBLE);
        } else if (errores==3) {
            brazoDerecho.setVisibility(View.VISIBLE);
        } else if (errores==4) {
            brazoIzquierdo.setVisibility(View.VISIBLE);
        } else if (errores==5) {
            piernaDerecha.setVisibility(View.VISIBLE);
        } else if (errores==6) {
            piernaIzquierda.setVisibility(View.VISIBLE);
        }
    }
    public void regresarPantallaPrincipal(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void verEstadisticas(View view){
        Intent intent = new Intent(this,EstadisticasActivity.class);
        startActivity(intent);
    }





}