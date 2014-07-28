package jaframework.tests.functional;

import jaframework.def.JAFactory;
import jaframework.def.JAFile;
import jaframework.def.JASession;
import jaframework.tests.utils.Alumno;
import org.junit.Test;

/**
 * Created by usuario on 15/06/14.
 */
public class MainTests {

    @Test
    public void readTest (){

        JAFactory.registerMapping(Alumno.class);
        JASession session = JAFactory.getSession();

        // obtengo el archivo
        JAFile<Alumno> f = session.getFileByAlias("ALUMNOS");

        // muevo el indicador de posicion del archivo hacia el inicio
        f.reset();

        // creo una instancia de Alumno
        Alumno a = new Alumno();

        // el metodo read lee el proximo registro y retorna true si la lectura resulto correcta
        boolean ok = f.read(a);

        while( ok ) {
            System.out.println(a);
            ok = f.read(a);
        }

        f.reset();
    }

    @Test
    public void readWrite(){
        JAFactory.registerMapping(Alumno.class);
        JASession session = JAFactory.getSession();

        // obtengo el archivo
        JAFile<Alumno> f = session.getFileByAlias("ALUMNOS");

        // muevo el indicador de posicion del archivo hacia el inicio
        f.reset();

        // creo una instancia de Alumno
        Alumno a = new Alumno();

        // el metodo read lee el proximo registro y retorna true si la lectura resulto correcta
        boolean ok = f.read(a);

        while( ok ) {
            System.out.println(a);

            try {
                Alumno alumnoNuevo = a.clone();
                alumnoNuevo.setLegajo( alumnoNuevo.getLegajo() +1 );
                f.append(alumnoNuevo);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


            ok = f.read(a);
        }
        f.reset();
        f.close();
    }
/*
    @Test
    public void todosReprueban(){
        JAFactory.registerMapping(Alumno.class);
        JASession session = JAFactory.getSession();

        // obtengo el archivo
        JAFile<Alumno> f = session.getFileByAlias("ALUMNOS");

        // muevo el indicador de posicion del archivo hacia el inicio
        f.reset();

        // creo una instancia de Alumno
        Alumno a = new Alumno();

        // el metodo read lee el proximo registro y retorna true si la lectura resulto correcta
        boolean ok = f.read(a);

        while( ok ) {
            System.out.println(a);
            a .setNota(2);
            f.seek(f.filePos() -1);
            f.write(a);
            ok = f.read(a);
        }
        f.reset();
        f.close();
    }


    @Test
    public void writeTest(){

            System.out.println("INICIO WRITETEST");
            JAFactory.registerMapping(Alumno.class);
            JASession session=JAFactory.getSession();

            // obtengo la referencia al archivo de alumnos
            JAFile<Alumno> f=session.getFileByAlias("ALUMNOS");

            // borro el contenido actual del archivo; lo dejo vacio
            f.seek(f.fileSize() -1);
            Alumno a = new Alumno();
            a.setFechaIngreso("24/10/1988");
            a.setLegajo(1298392);
            a.setNombre("Pablo Hernan");
            a.setNota(8);
            a.setSexo('M');

            f.write(a);

            System.out.println("FIN WRITETEST");
    }
*/
    @Test
    public void indexed(){
/*       System.out.println("INICIO WRITETEST");
       JAFactory.registerMapping(Alumno.class);
       JASession session=JAFactory.getSession();
       // obtengo la referencia al archivo de alumnos
       JAFile<Alumno> f=session.getFileByAlias("ALUMNOS");
        session.getIndexByAlias(f,)*/
    }


}
