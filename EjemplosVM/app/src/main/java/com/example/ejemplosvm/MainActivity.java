package com.example.ejemplosvm;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    TextView nombreTV, dniTV, notaTV;
    Button changeBtn;

    MainVM vm;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Cargo componentes visuales

        nombreTV = findViewById(R.id.nombreTV);
        dniTV = findViewById(R.id.dniTV);
        notaTV = findViewById(R.id.notaTV);
        changeBtn = findViewById(R.id.changeBtn);

        //Inicio el VM
        vm = new ViewModelProvider(this).get(MainVM.class);

        //Me suscribo a los mutableLiveData para poder detectar cuando cambian y repintar la app
        //fijate que una ventaja de usar mutable live data y observar los cambios es que este es el unico sitio donde tengo que preocuparme,
        //en toda la activity de vincular los elementos visuales a los datos.
        vm.getEstudiante().observe(this, e -> {
            //Aquí voy a actualizar los datos del usuario cuando cambien en el mutableLiveData
            nombreTV.setText(e.nombre);
            dniTV.setText(e.dni);
            notaTV.setText("" + e.nota);

            //cambiamos el color ne funcion de si esta aprobado o no
            notaTV.setTextColor(Color.parseColor("#ff0000"));
            if(e.aprobado)
                notaTV.setTextColor(Color.parseColor("#00FF00"));
        });

        //vínculo los botones
        changeBtn.setOnClickListener(v -> {
            vm.cambiarAprobadoEstudiante();
        });

        //cargo los datos iniciales

        vm.cargaEstudiante();
    }


}