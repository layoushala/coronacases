/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hala Layous
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileManager {

    public void createDirectory(String dirPath) {
        File dir = new File(dirPath);
        if (!checkIfDirExists(dirPath)) {
            dir.mkdir();
        }
    }

    public boolean checkIfDirExists(String dirPath) {
        File dir = new File(dirPath);
        return dir.exists();
    }
    
      public boolean copyFile(String srcFile, String desPath) throws FileNotFoundException {
       File file = new File(desPath);
        if (!file.exists()) {
            try(InputStream inStream = new FileInputStream(srcFile)) {
                FileUtils.copyInputStreamToFile(inStream, file);
                return true;
            } catch (IOException ex) {
                return false;
            }
        } else {
            return true;
        }
    }

       public synchronized void appendToFile(String filePath, StringBuffer stringBuffer) {

        try {
            FileWriter fstream = new FileWriter(filePath, true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(stringBuffer.toString());
            out.newLine();
            out.close();
            fstream.close();
        } catch (IOException ex) {
            int i = 0;
            //Log.append("Error occured FileSystemService:appendToFile ==> " + filePath + " ==> " + ex.getMessage(), "", "", Log.LOG_TYPE_ALARM);
        }
       }
}
