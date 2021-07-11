/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.dao;

import com.ligadata.coronacases.helpermodel.CountryCase;
import com.ligadata.coronacases.helpermodel.CountryPagination;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hala Layous
 */
@Repository("coronaCaseDao")
public class CoronaCaseDaoImp implements ICoronaCaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CountryPagination filterAllRewards(int pageNumber, int maxRes, Map<String, String[]> filters) {
        String filterValue = "";
        for (Map.Entry<String, String[]> entry : filters.entrySet()) {
            String filterId = entry.getKey();
            filterValue = entry.getValue()[0];
            if (filterId.equals("region")) {
                filterValue = (filterValue == null) ? "" : filterValue.trim();
                break;

            }
        }

        Session session = sessionFactory.getCurrentSession();

        Query countQuery;
        countQuery = session.createSQLQuery("SELECT  country,\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Death Cases' \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'Death Cases',\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Confirmed Cases'  \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'Confirmed Cases',\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Recovered Cases' \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'Recovered Cases'\n"
                + "FROM    ligadata_assignment.tbl_corona_cases c where region LIKE '" + "%" + filterValue + "%" + "'\n"
                + "GROUP BY country;");
        Integer totalResults = ((BigInteger) countQuery.uniqueResult()).intValue();
        Query query = session.createSQLQuery("SELECT  country,\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Death Cases' \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'deathCases',\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Confirmed Cases'  \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'confirmedCases',\n"
                + "    sum(\n"
                + "        CASE \n"
                + "            WHEN c.name='Recovered Cases' \n"
                + "            THEN c.value \n"
                + "            ELSE NULL \n"
                + "        END\n"
                + "    ) AS 'recoveredCases'\n"
                + "FROM  tbl_corona_cases c where region LIKE '" + "%" + filterValue + "%" + "'\n"
                + "GROUP BY country LIMIT " + (pageNumber) + ", " + maxRes);
        query.setResultTransformer(Transformers.aliasToBean(CountryCase.class));
        List<CountryCase> countryList = (List<CountryCase>) query.list();
       
        CountryPagination countryPagination = new CountryPagination( totalResults, countryList);

        return countryPagination;
    }

}