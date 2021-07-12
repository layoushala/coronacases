/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.spring.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Hala Layous
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CatalinaService {

   private String applicationName = "Corona cases";

    public String getCatalinaHome() {
        return System.getProperty("catalina.home");
    }

    public String getCatalinaWorkDir() {
        return getCatalinaHome() + "/work/";
    }

    public String getCatalinaLogDir() {
        return getCatalinaHome() + "/logs/";
    }

    public String getCatalinaWorkInstanceDir() {
        return getCatalinaWorkDir() + applicationName;
    }

    public String getCatalinaLogInstanceDir() {
        return getCatalinaLogDir() + applicationName;
    }

    

}
