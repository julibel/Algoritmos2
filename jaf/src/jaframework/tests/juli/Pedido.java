package jaframework.tests.juli;

import jaframework.def.annotations.Field;
import jaframework.def.annotations.File;

/**
 * Created by usuario on 27/07/14.
 */
@File(name="ListaDeCompra", alias="pedido")
public class Pedido {

    @Field(size = 3)
    Integer codigo;

    @Field(size = 1)
    Integer cantidad;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", cantidad=" + cantidad +
                '}';
    }
}
