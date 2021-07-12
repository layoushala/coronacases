/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.spring.configuration;

import com.ligadata.coronacases.io.FolderManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hala Layous
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    FolderManagement folderManagement;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        folderManagement.createNeededFolder();
    }
    
}
