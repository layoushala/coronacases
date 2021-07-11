/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.dao;

import com.ligadata.coronacases.helpermodel.CountryPagination;
import java.util.Map;

/**
 *
 * @author Hala Layous
 */
public interface ICoronaCaseDao {
    
    public CountryPagination filterAllRewards(int pageNumber, int maxRes, Map<String, String[]> filters);
}