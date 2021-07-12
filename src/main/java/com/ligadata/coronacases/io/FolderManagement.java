/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.io;

import com.ligadata.coronacases.spring.configuration.CatalinaService;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hala Layous
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FolderManagement {
    
    @Autowired
    CatalinaService context;
    @Autowired
    FileManager fileManager;
    
    public void createNeededFolder(){
         if (!fileManager.checkIfDirExists(context.getCatalinaLogInstanceDir())) {
            fileManager.createDirectory(context.getCatalinaLogInstanceDir());
        }
       
    }
    
}
