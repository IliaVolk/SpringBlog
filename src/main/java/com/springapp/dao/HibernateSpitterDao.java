package com.springapp.dao;

import com.springapp.entities.Spitter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Repository
public class HibernateSpitterDao extends HibernateDaoSupport implements SpitterDao{

    @Override
    public Spitter getSpitterById(long id) {
        return getHibernateTemplate().get(Spitter.class, id);
    }

    @Override
    public void saveSpitter(Spitter spitter) {
        getHibernateTemplate().saveOrUpdate(spitter);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Spitter> getSpittersByName(String name) {
        return (List<Spitter>) getSession().createCriteria(Spitter.class).
                add(Restrictions.eq("username", name)).list();

    }

    @Override
    public void deleteSpitter(Spitter spitter) {
        Spitter loadedSpitter = getHibernateTemplate().load(Spitter.class, spitter.getId());
        if (loadedSpitter != null){
            getHibernateTemplate().delete(loadedSpitter);
        }
    }

}
