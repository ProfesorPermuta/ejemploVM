package com.example.ejemplosvm;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//Este VM solo será usado en la main activity, normalmente lleva su nombre
public class MainVM extends ViewModel {

    //Esta información es la necesaria para que la activity muestre los datos correctamente.
    //Usamos MutableLiveData para poder detectar cambios en esta información y actualizar la parte visual de la activity
    private MutableLiveData<Estudiante> estudiante = new MutableLiveData<>();

    public void cargaEstudiante(){
        //Aqui se realizaría la llamada al repositorio o se crearía el estudiante dependiendo de la implementacion
        //Imagina que la siguiente línea fuese algo como estudentRepo.getStudentById() y devolvies un Estudiante
        Estudiante estudianteDevuelto = new Estudiante();
        estudianteDevuelto.nombre = "Nombre";
        estudianteDevuelto.dni = "111111A";
        estudianteDevuelto.nota = 3;
        estudianteDevuelto.aprobado = false;
        this.estudiante.setValue(estudianteDevuelto);
    }

    //Modificará el aprobado del estudiante en los datos
    public void cambiarAprobadoEstudiante(){

        //Obtengo el valor del estudiante
        Estudiante e = estudiante.getValue();

        assert e != null;
        e.aprobado = !e.aprobado;


        //Una vez finalizada la actualización vuelvo a cargarlo en el mutable para que se notifiquen los cambios
        estudiante.setValue(e);
    }

    public MutableLiveData<Estudiante> getEstudiante() {
        return estudiante;
    }
}
