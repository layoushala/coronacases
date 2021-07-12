/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.controller;

import com.ligadata.coronacases.helpermodel.CountryPagination;
import com.ligadata.coronacases.logger.LogFile;
import com.ligadata.coronacases.service.ICoronaCaseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hala Layous
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/corona-cases")
public class CoronaCaseController {

    @Autowired
    ICoronaCaseService coronaCaseService;
    
    @Autowired
    LogFile log;

    @GetMapping
    public ResponseEntity filterCoronaCases(@RequestParam(name = "start") int pageNumber, @RequestParam(name = "size") int maxRes, HttpServletRequest request) {
        
        CountryPagination countryPagination = coronaCaseService.filterCoronaCases(pageNumber, maxRes, request.getParameterMap());
        return new ResponseEntity<>(countryPagination, HttpStatus.OK);
    }
    
    @GetMapping("/country-details/{countryName}")
    public ResponseEntity countryDetials(@PathVariable String countryName) {
        
        return new ResponseEntity<>(coronaCaseService.countryDetials(countryName), HttpStatus.OK);
    }
    
    @GetMapping("/regions")
    public ResponseEntity getRegions() {
       
        return new ResponseEntity<>(coronaCaseService.getRegions(), HttpStatus.OK);
    }
   

}
