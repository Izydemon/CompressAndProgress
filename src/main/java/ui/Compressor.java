package ui;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import java.util.zip.*;

/**
 *
 * @author legol
 */
public class Compressor extends SwingWorker<Void, Integer>{

    List<File> files;
    File dir;
    String name;
    
    @Override
    protected Void doInBackground() throws Exception {
        // Objeto para referenciar el archivo zip de salida
        FileOutputStream dest = new FileOutputStream(dir.getAbsolutePath() + "\\" + name + ".zip");
        ZipOutputStream out = new ZipOutputStream(dest);
        for (File file : files) {
            out.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            out.write(bytes, 0, bytes.length);
            out.closeEntry();
        }
        // Cerramos el archivo zip
        out.close();
        
        return null;
    }
    
    @Override
    public void done(){
        System.out.println("Done");
    }
    
    public void SetFiles(List<File> files){
        this.files = new ArrayList<>(files);
    }
    
    public void SetDir(File dir){
        this.dir = dir;
    }
    
    public void SetName(String name){
        this.name = name;
    }
}
