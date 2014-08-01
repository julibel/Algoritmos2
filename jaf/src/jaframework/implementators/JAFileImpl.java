package jaframework.implementators;

import jaframework.def.JAFile;
import jaframework.def.annotations.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 15/06/14.
 */
public class JAFileImpl<T> implements JAFile<T> {

    Class<T> fileWrapper;
    List<String> fileDataset;
    java.io.File file;
    boolean isDirty = false;

    int pos;

    JAFileImpl(Class<T> unaClase) throws IOException {
        File annotation = unaClase.getAnnotation(File.class);
        this.file = new java.io.File(annotation.name());

        FileReader fr = new FileReader(this.file);
        BufferedReader reader = new BufferedReader(fr);
        this.fileDataset = new ArrayList<String>();
        String actualLine = "";

        while((actualLine = reader.readLine()) != null){
            this.fileDataset.add(actualLine);
        }
        this.fileWrapper = unaClase;
        reader.close();
        fr.close();
    }

    @Override
    public void reset() {
        pos = 0;
    }

    @Override
    public boolean eof() {
        return pos == fileDataset.size();
    }

    /**
     * 0043,Carla     ,2001/03/21,F,10
     0023,Amalia    ,1992/10/12,F,06
     0060,Marisa    ,1990/03/15,F,04
     0033,Rolando   ,1993/11/01,M,09
     0053,Osvaldo   ,2005/12/20,M,02
     * @param record
     * @return
     */
    @Override
    public boolean read(T record) {
        if(pos >= fileDataset.size())
            return false;
        return Utils.fill(fileDataset.get(pos++), record);
    }

    @Override
    public int filePos() {
        return pos;
    }

    @Override
    public void write(T record) {
        try {
            isDirty = true;
            fileDataset.set(pos, Utils.encode(record));
            pos++;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void seek(int n) {
        pos = n;
    }

    @Override
    public int fileSize() {
        return fileDataset.size();
    }

    @Override
    public void close() {
        if(isDirty){
            try {
                FileWriter fw = new FileWriter(this.file);
                for(String line : fileDataset) {
                    fw.append(String.format(line + "%n"));
                }
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        fileDataset.clear();
        pos = 0;
    }

    @Override
    public String getAlias() {
        return Utils.getAlias(fileWrapper);
    }

    @Override
    public void rewrite() {
        fileDataset.clear();
    }

    @Override
    public void append(T record) {
        try {
            isDirty = true;
            fileDataset.add(pos, Utils.encode(record));
            pos++;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
