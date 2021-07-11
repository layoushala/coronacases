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
    
    private Number deathCases;
    
    private Number confirmedCases;
    
    private Number recoveredCases;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Number getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(Number deathCases) {
        this.deathCases = deathCases;
    }

    public Number getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(Number confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public Number getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(Number recoveredCases) {
        this.recoveredCases = recoveredCases;
    }
    
    
    
}
