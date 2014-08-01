package jaframework.tests.functional;

import jaframework.def.JAFactory;
import jaframework.def.JAFile;
import jaframework.def.JAIndex;
import jaframework.def.JASession;
import jaframework.tests.utils.Alumno;

import junit.framework.Assert;
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
        JAIndex<Alumno> ids = session.getIndexByAlias(f,"ids");

        idx1.reset();
        
        Alumno a = new Alumno();

        System.out.println("************ IDX1 ***********");
        while( idx1.read(a) )
        {
           System.out.println(a); 
        }

        System.out.println("************ IDS ***********");
        while( ids.read(a) )
        {
            System.out.println(a);
        }

        {
            Assert.assertEquals(0, idx1.search(new Alumno("Padro")));
            Assert.assertEquals(1, idx1.search(new Alumno("Pedro")));
            Assert.assertEquals(2, idx1.search(new Alumno("Pidro")));
            Assert.assertEquals(3, idx1.search(new Alumno("Sandra")));
            Assert.assertEquals(4, idx1.search(new Alumno("Sandro")));
        }

        {
            Assert.assertEquals(4, ids.search(new Alumno("Padro")));
            Assert.assertEquals(3, ids.search(new Alumno("Pedro")));
            Assert.assertEquals(2, ids.search(new Alumno("Pidro")));
            Assert.assertEquals(1, ids.search(new Alumno("Sandra")));
            Assert.assertEquals(0, ids.search(new Alumno("Sandro")));
        }



        idx1.close();
        f.close();
    
    }    
        
        
}
