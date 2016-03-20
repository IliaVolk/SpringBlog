package com.springapp.dao;

import com.springapp.entities.Spitter;

import java.util.List;

public interface SpitterDao {

    public Spitter getSpitterById(long id);
    public void saveSpitter(Spitter spitter);
    public List<Spitter>  getSpittersByName(String name);
    public void deleteSpitter(Spitter spitter);
}
