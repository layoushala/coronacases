/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ligadata.coronacases.dao;

import com.ligadata.coronacases.helpermodel.CountryCase;
import com.ligadata.coronacases.helpermodel.CountryPagination;
import com.ligadata.coronacases.logger.LogFile;
import com.ligadata.coronacases.model.CoronaCase;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
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
    public CountryPagination filterCoronaCases(int pageNumber, int maxRes, Map<String, String[]> filters) {
        
            String filterValue = "";
            String orderBy = "";
            for (Map.Entry<String, String[]> entry : filters.entrySet()) {
                String filterId = entry.getKey();

                if (filterId.equals("sortField")) {
                    String sortValue = entry.getValue()[0];
                   
                    if(sortValue != ""){
                       sortValue = sortValue.trim();
                       orderBy = "order by " + sortValue + " " + filters.get("sortOrder")[0];
                    }
                    

                }
                if (filterId.equals("region")) {
                    filterValue = entry.getValue()[0];
                    filterValue = (filterValue == null) ? "" : filterValue.trim();

                }
            }

            Session session = sessionFactory.getCurrentSession();

            Query countQuery;
            countQuery = session.createSQLQuery("SELECT count(*) from (SELECT  country,\n"
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
                    + "FROM    tbl_corona_cases c where region LIKE '" + "%" + filterValue + "%" + "'\n"
                    + "GROUP BY country) b;");
            Integer totalResults = ((BigInteger) countQuery.uniqueResult()).intValue();
            Query query = session.createSQLQuery("SELECT * From ( SELECT  country,\n"
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
                    + "    ) AS 'recoveredCases',\n"
                    + "    sum(\n"
                    + "        CASE \n"
                    + "            WHEN c.name='Death Cases' \n"
                    + "            THEN c.value \n"
                    + "            ELSE NULL \n"
                    + "        END\n"
                    + "    ) AS 'deathCases'\n"
                    + "FROM  tbl_corona_cases c where region LIKE '" + "%" + filterValue + "%" + "'\n"
                    + "GROUP BY country) b " + orderBy + " LIMIT " + ((pageNumber - 1) * maxRes) + ", " + maxRes);
            query.setResultTransformer(Transformers.aliasToBean(CountryCase.class));
            List<CountryCase> countryList = (List<CountryCase>) query.list();
            int id = pageNumber * maxRes;
            for (CountryCase countryCase : countryList) {
                countryCase.setId(id);
                id++;
            }
            CountryPagination countryPagination = new CountryPagination(totalResults, countryList);

            return countryPagination;
       
    }

    @Override
    public List<String> getRegions() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CoronaCase.class);
        criteria.setProjection(Projections.distinct(Projections.property("region")));

        return criteria.list();
    }

    @Override
    public List<CoronaCase> countryDetials(String country) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CoronaCase.class);
        criteria.add(Restrictions.eq("country", country));

        return criteria.list();
    }

}
