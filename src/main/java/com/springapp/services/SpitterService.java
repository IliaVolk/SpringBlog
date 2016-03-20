package com.springapp.services;

import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SpitterService {
    public Spitter getSpitterById(long id);
    public void saveSpitter(Spitter spitter);
    public List<Spitter> getSpittersByName(String name);
    public void deleteSpitter(Spitter spitter);
    public List<Spittle> getRecentSpittles(int count);
    public void saveSpittle(Spittle spittle);
}
