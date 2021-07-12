/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.service;

import com.ligadata.coronacases.dao.ICoronaCaseDao;
import com.ligadata.coronacases.helpermodel.CountryPagination;
import com.ligadata.coronacases.model.CoronaCase;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hala Layous
 */
@Service("coronaCaseService")
@Transactional
public class CoronaCaseServiceImpl implements ICoronaCaseService{
    
    @Autowired
    ICoronaCaseDao coronaCaseDao;

    @Override
    public CountryPagination filterAllRewards(int pageNumber, int maxRes, Map<String, String[]> filters) {
        return coronaCaseDao.filterAllRewards(pageNumber, maxRes, filters);
    }

    @Override
    public List<String> getRegions() {
        return coronaCaseDao.getRegions();
    }

    @Override
    public List<CoronaCase> countryDetials(String country) {
        return coronaCaseDao.countryDetials(country);
    }
    
}
