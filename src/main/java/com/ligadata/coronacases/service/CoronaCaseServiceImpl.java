/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.service;

import com.ligadata.coronacases.dao.ICoronaCaseDao;
import com.ligadata.coronacases.helpermodel.CountryPagination;
import com.ligadata.coronacases.logger.LogFile;
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
public class CoronaCaseServiceImpl implements ICoronaCaseService {

    @Autowired
    ICoronaCaseDao coronaCaseDao;

    @Autowired
    LogFile log;

    @Override
    public CountryPagination filterCoronaCases(int pageNumber, int maxRes, Map<String, String[]> filters) {
        try {
            log.logData("Receive  Get Corona Cases Request[pageNumber = "+pageNumber+", Size = "+maxRes+"]", LogFile.LOG_TYPE.NORMAL);
            CountryPagination countryPagination = coronaCaseDao.filterCoronaCases(pageNumber, maxRes, filters);
            log.logData("Success  Get Corona Cases Request [pageNumber = "+pageNumber+", Size = "+maxRes+"]", LogFile.LOG_TYPE.NORMAL);
            return countryPagination;
        } catch (Exception ex) {
            log.logData("Error in CoronaCaseDaoImp->filterCoronaCases :" + ex.getMessage(), LogFile.LOG_TYPE.ALARM);
            return null;
        }

    }

    @Override
    public List<String> getRegions() {
        try {
            log.logData("Receive  Get Regions Request", LogFile.LOG_TYPE.NORMAL);
            List<String> regions = coronaCaseDao.getRegions();
            log.logData("Success  Get Regions Request", LogFile.LOG_TYPE.NORMAL);
            return regions;
        } catch (Exception ex) {
            log.logData("Error in CoronaCaseServiceImpl->getRegions :" + ex.getMessage(), LogFile.LOG_TYPE.ALARM);
            return null;
        }
    }

    @Override
    public List<CoronaCase> countryDetials(String country) {
        try {
            log.logData("Receive  Country Details Request [country = "+country+"]", LogFile.LOG_TYPE.NORMAL);
            List<CoronaCase> couList =  coronaCaseDao.countryDetials(country);
            log.logData("Success  Country Details Request [country = "+country+"]", LogFile.LOG_TYPE.NORMAL);
            return couList;
        } catch (Exception ex) {
            log.logData("Error in CoronaCaseServiceImpl->countryDetials :" + ex.getMessage(), LogFile.LOG_TYPE.ALARM);
            return null;
        }
    }

}
