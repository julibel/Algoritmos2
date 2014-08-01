package jaframework.tests.utils;

import jaframework.def.annotations.Field;
import jaframework.def.annotations.File;
import jaframework.def.annotations.Index;
import jaframework.def.annotations.Indexes;

/**
 * Created by usuario on 15/06/14.
 */

@File(name="ALUMNOS.txt", alias="ALUMNOS")
@Indexes(
    value = {
        @Index(key="nombre", alias="idx"),
        @Index(key="-nombre", alias="ids")
    }
)
public class Alumno implements Cloneable{
    @Field(size=4)
    private int legajo;

    @Field(size=10)
    private String nombre;

    @Field(size=10)
    private String fechaIngreso;

    @Field(size=1)
    private char sexo;

    @Field(size=2)
    private int nota;


    public Alumno(){
    }

    public Alumno(String nombre){
        this.nombre = nombre;
    }

    @Override
    public String toString()
    {
        String linea = "";
        linea+="legajo="+legajo+ ", nombre="+nombre+", ";
        linea+="fechaIngreso="+fechaIngreso+", sexo="+sexo+", nota="+nota;
        return linea;
    }

    public Alumno clone() throws CloneNotSupportedException {
        return (Alumno) super.clone();
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
