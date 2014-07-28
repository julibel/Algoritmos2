package jaframework.tests.juli;

import jaframework.def.annotations.Field;
import jaframework.def.annotations.File;

@File(name="ListaDePrecios.txt", alias="ListaMasCorto")
public class Producto {

    @Field(size = 3)
    Double codigo;

    @Field(size = 10)
    String descripcion;

    @Field(size = 4)
    Double precio;

    public Double getCodigo() {
        return codigo;
    }

    public void setCodigo(Double codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String toString(){
        return getCodigo() + " - " + getDescripcion() + " - $" + getPrecio();
    }



}
