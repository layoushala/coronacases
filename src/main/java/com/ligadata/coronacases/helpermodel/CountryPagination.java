/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.helpermodel;

import java.util.List;

/**
 *
 * @author Hala Layous
 */
public class CountryPagination {
    
    private int total;
    
    List<CountryCase> countryCases;

    public CountryPagination(int total, List<CountryCase> countryCases) {
        this.total = total;
        this.countryCases = countryCases;
    }
    
    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CountryCase> getCountryCases() {
        return countryCases;
    }

    public void setCountryCases(List<CountryCase> countryCases) {
        this.countryCases = countryCases;
    }
    
    
}
