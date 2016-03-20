package com.springapp.services;

import com.springapp.dao.SpitterDao;
import com.springapp.dao.SpittleDao;
import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DefaultSpitterService implements SpitterService {
    @Autowired
    private SpitterDao spitterDao;
    @Autowired
    private SpittleDao spittleDao;
    public void setSpitterDao(SpitterDao spitterDao) {
        this.spitterDao = spitterDao;
    }
    public void setSpittleDao(SpittleDao spittleDao) {
        this.spittleDao = spittleDao;
    }

    @Override
    public Spitter getSpitterById(long id) {
        return spitterDao.getSpitterById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSpitter(Spitter spitter) {
        spitterDao.saveSpitter(spitter);
    }

    @Override
    public List<Spitter> getSpittersByName(String name) {
        return spitterDao.getSpittersByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSpitter(Spitter spitter) {
        spitterDao.deleteSpitter(spitter);
    }

    @Override
    public List<Spittle> getRecentSpittles(int count) {
        return spittleDao.getRecentSpittles(count);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveSpittle(Spittle spittle) {
        spittleDao.saveSpittle(spittle);
    }
}
