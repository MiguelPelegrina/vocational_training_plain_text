package controller;

import model.Alumno;
import static view.framePrincipal.alumno;
import static view.framePrincipal.listaAlumnos;

/**
 * Controlador que controla los datos introducidos del modelo alumno
 */
public class CtrlAlumno {
    /**
     * Metodo que crea un objeto de la clase alumno y lo introduce en una lista 
     * @param matricula
     * @param nombre
     * @param fecha
     * @param notaMedia
     * @param edad 
     */    
    public void altaAlumno(int matricula, String nombre, String fecha, 
            double notaMedia, int edad){        
        alumno = new Alumno(matricula, nombre, fecha, notaMedia, edad);        
        listaAlumnos.add(alumno);                      
    }    
    
    //ESCALABLE MODIFICANDO LA CANTIDAD DE PARÁMETROS
    /**
     * Metodo que comprueba la existencia de un alumno en la lista 
     * @param xMatricula Atributo identificador que no se puede repetir dentro
     * de la lista
     * @return Devuelve true si el alumno ya se encuentra dentro de la lista o
     * false si aun no se encuentra dentro
     */ 
    public boolean existeAlumno(int xMatricula){
        boolean existe = false;
        if(listaAlumnos.size() > 0){
            for(int i = 0; i < listaAlumnos.size(); i++){
                Alumno a = listaAlumnos.get(i);
                if(a.getMatricula() == xMatricula){                    
                    existe = true;                    
                }                    
            }
        }        
        return existe;
    }
    
    /**
     * Metodo que elimina un alumno de la lista
     * @param xMatricula Atributo identificador que no se puede repetir dentro
     * de la lista
     */
    public void bajaAlumno(int xMatricula){
        for(int i = 0; i < listaAlumnos.size(); i++){
            Alumno a = listaAlumnos.get(i);
            if(a.getMatricula() == xMatricula){
                listaAlumnos.remove(i);
            }                    
        }
    }
    
    /**
     * Metodo que modifica los atributos de un alumno
     * @param matricula
     * @param nombre
     * @param fecha
     * @param notaMedia
     * @param edad 
     */ 
    public void modificacionAlumno(int matricula, String nombre, String fecha, 
            double notaMedia, int edad){
        for (Alumno alumnoLista : listaAlumnos) {
            if(alumnoLista.getMatricula() == matricula){
                alumnoLista.setNombre(nombre);
                alumnoLista.setFechaNacimiento(fecha);
                alumnoLista.setNotaMedia(notaMedia);
                alumnoLista.setEdad(edad);
            }
        }
    }     
}
