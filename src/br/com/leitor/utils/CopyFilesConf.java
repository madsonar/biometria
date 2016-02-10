package br.com.leitor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyFilesConf {

    public void load() {
        File dir_parent = new File(Path.getRealPath() + "\\src\\resources\\conf\\");
        if (dir_parent.exists()) {
            File dir_lib = new File(Path.getRealPath() + "\\lib\\conf\\");
            Boolean existDist = false;
            if (!dir_lib.exists()) {
                dir_lib.mkdirs();
            }
            if (new File(Path.getRealPath() + "\\dist\\").exists()) {
                if (!new File(Path.getRealPath() + "\\dist\\lib\\conf\\").exists()) {
                    new File(Path.getRealPath() + "\\dist\\lib\\conf\\").mkdirs();
                }
                existDist = true;
            }
            File file_cp[] = dir_parent.listFiles();
            for (int i = 0; i < file_cp.length; i++) {
                File cp = new File(Path.getRealPath() + "\\lib\\conf\\" + file_cp[i].getName());
                if (!cp.exists()) {
                    try {
                        copyFile(file_cp[i], cp);
                    } catch (IOException ex) {

                    }
                }
                if (existDist) {
                    cp = new File(Path.getRealPath() + "\\dist\\lib\\conf\\" + file_cp[i].getName());
                    if (!cp.exists()) {
                        try {
                            copyFile(file_cp[i], cp);
                        } catch (IOException ex) {

                        }
                    }
                }
            }
        }
    }

    public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

}
