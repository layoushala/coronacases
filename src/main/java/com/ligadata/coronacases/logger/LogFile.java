/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.logger;

import com.ligadata.coronacases.io.FileManager;
import com.ligadata.coronacases.spring.configuration.CatalinaService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hala Layous
 */
@Component
public class LogFile {
  
    @Autowired
    FileManager fileManager;
    
    @Autowired
    CatalinaService context;
    
    public enum LOG_TYPE {
        ALARM,
        NORMAL
    }


    public synchronized void logData(String content, LOG_TYPE logType) {
      
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer
                    .append(new SimpleDateFormat("dd-MM-yyy HH:mm:ss").format(Calendar.getInstance().getTime()))
                    .append(" ==>  ")
                    .append(content);
            
            String logFileName = "Corona-Cases" +new SimpleDateFormat("dd-MM-yyy").format(Calendar.getInstance().getTime()) + ".log";
            if (logType == LOG_TYPE.ALARM) {
                logFileName = "Corona-Cases-AlARM-" + new SimpleDateFormat("dd-MM-yyy").format(Calendar.getInstance().getTime()) + ".log";
            }

            fileManager.appendToFile(context.getCatalinaLogInstanceDir() +"/"+ logFileName, stringBuffer);

        
    }

   
}
