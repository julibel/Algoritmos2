package jaframework.tests.juli;

import jaframework.def.JAFactory;
import jaframework.def.JAFile;
import jaframework.def.JASession;

/**
 * Created by usuario on 27/07/14.
 */
public class ImplementoFramework {


    public static void main(String[] args) {
        //

        JAFactory.registerMapping(Producto.class);
        JAFactory.registerMapping(Pedido.class);

        JASession session = JAFactory.getSession();

        JAFile<Producto> f = session.getFileByAlias("ListaMasCorto");

        f.reset();

        Producto a = new Producto();

        boolean ok = f.read(a);

        while (ok) {
            a.setPrecio(a.getPrecio() * 1.3);
            f.seek( f.filePos() -1 );
            f.write(a);
            System.out.println(a);
            ok = f.read(a);
        }

        f.close();

        JAFile<Pedido> archivoDePedidos = session.getFileByAlias("pedido");
        archivoDePedidos.reset();

        Pedido p = new Pedido();

        while (!archivoDePedidos.eof()) {
            archivoDePedidos.read(p);

            System.out.println(p);

        }

        archivoDePedidos.close();
    }


}
