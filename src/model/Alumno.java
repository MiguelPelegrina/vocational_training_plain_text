package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo alumno cuyos datos se van a guardar en una lista
 * @author migup
 */
public class Alumno implements Serializable, Comparable<Alumno>{
    // Atributos de la instancia
    private int matricula;
    private String nombre;
    private String fechaNacimiento;
    private double notaMedia;
    private int edad;         
    
    /**
     * Constructor por parametros de la clase alumno
     * @param matricula 
     * @param nombre
     * @param fechaNacimiento
     * @param notaMedia
     * @param edad 
     */
    public Alumno(int matricula, String nombre, String fechaNacimiento, 
            double notaMedia, int edad){
        this.matricula = matricula;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.notaMedia = notaMedia;
        this.edad = edad;
    }
    
    // Getter
    public int getMatricula(){
        return this.matricula;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    
    public double getNotaMedia(){
        return this.notaMedia;
    }
    
    public int getEdad(){
        return this.edad;
    }
    
    // Setter
    public void setMatricula(int matricula){
        this.matricula = matricula;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setNotaMedia(double notaMedia){
        this.notaMedia = notaMedia;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    @Override
    public String toString(){
        return this.getMatricula() + "*" + this.getNombre() + "*" +
                this.getFechaNacimiento() + "*" + this.getNotaMedia() + "*" +
                this.getEdad() + "\n";
    }

    @Override
    public int compareTo(Alumno t) {
        return this.matricula - t.matricula;
    }
    
    @Override
    public boolean equals(Object obj){
        boolean esIgual = false;
            if(obj instanceof Alumno){
                Alumno alumno = (Alumno) obj;
                if(this.matricula == alumno.matricula){
                    esIgual = true;
                }
            }
        return esIgual;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.matricula;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.fechaNacimiento);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.notaMedia) ^ (Double.doubleToLongBits(this.notaMedia) >>> 32));
        hash = 97 * hash + this.edad;
        return hash;
    }
}
