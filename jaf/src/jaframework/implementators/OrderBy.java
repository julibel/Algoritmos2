package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.JAIndex;

import java.util.List;

/**
 * Created by usuario on 15/06/14.
 */
public class OrderBy<T> implements JAIndex<T>, AutoCloseable {

    JAFile<T> jaFile;
    List<String> indexes;
    String indexAlias = "";
    List<FileLine<T>> fileDataset;
    Integer pos = 0;

    public OrderBy(JAFile<T> file, String indexAlias){
        this.jaFile = file;
        this.indexAlias = indexAlias;
    }


    @Override
    public JAFile<T> getFile() {
        return jaFile;
    }

    @Override
    public void add(T key) {

    }

    @Override
    public String getKey() {
        return indexAlias;
    }

    @Override
    public int search(T toSearch) {
        return 0;
    }

    /**/
    @Override
    public void reset() {
        pos = 0;
    }

    @Override
    public boolean eof() {
        return pos == fileDataset.size();
    }

    @Override
    public boolean read(T record) {
        return false;
    }

    @Override
    public int filePos() {
        return pos;
    }

    @Override
    public void write(T record) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void seek(int n) {
        pos = n;
    }

    @Override
    public int fileSize() {
        return jaFile.fileSize();
    }

    @Override
    public void close() {
        jaFile.close();
    }

    @Override
    public String getAlias() {
        return jaFile.getAlias();
    }

    @Override
    public void rewrite() {
        jaFile.rewrite();
    }

    @Override
    public void append(T alumnoNuevo) {
        jaFile.append(alumnoNuevo);
    }

    public class FileLine<T>{
        public int pos;
        public T elem;
        public FileLine(T elem, int pos){
            this.pos = pos;
            this.elem = elem;
        }
    }


}
