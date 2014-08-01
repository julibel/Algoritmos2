package jaframework.tests.functional;

import jaframework.def.JAFactory;
import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.JASession;
import jaframework.tests.utils.Alumno;

import org.junit.Test;

public class MainTestIndex
{
    @Test
    public void indexTest (){

        JAFactory.registerMapping(Alumno.class);
        JASession session = JAFactory.getSession();

        // obtengo el archivo
        JAFile<Alumno> f = session.getFileByAlias("ALUMNOS");
        
        JAIndex<Alumno> idx1 = session.getIndexByAlias(f,"idx");

        idx1.reset();
        
        Alumno a = new Alumno();
         
        while( idx1.read(a) )
        {
           System.out.println(a); 
           
           idx1.read(a);
        }
        
        idx1.close();
        f.close();
    
    }    
        
        
}
