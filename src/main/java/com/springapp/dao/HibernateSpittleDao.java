package com.springapp.dao;

import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class HibernateSpittleDao extends HibernateDaoSupport implements SpittleDao {
    @Override
    public void saveSpittle(Spittle spittle) {
        getHibernateTemplate().saveOrUpdate(spittle);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Spittle> getRecentSpittles(int count) {
        return getSession().createCriteria(Spittle.class).
                addOrder(Order.desc("creationDate")).setMaxResults(count).list();
    }

}
