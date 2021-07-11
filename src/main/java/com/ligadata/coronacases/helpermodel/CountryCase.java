/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.helpermodel;

/**
 *
 * @author Hala Layous
 */
public class CountryCase {
    
    private String country;
    
    private Integer deathCases;
    
    private Integer confirmedCases;
    
    private Integer recoveredCases;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(Integer deathCases) {
        this.deathCases = deathCases;
    }

    public Integer getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(Integer confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public Integer getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(Integer recoveredCases) {
        this.recoveredCases = recoveredCases;
    }
    
    
    
}
