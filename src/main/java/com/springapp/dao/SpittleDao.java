package com.springapp.dao;

import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;

import java.util.List;

public interface SpittleDao {
    public void saveSpittle(Spittle message);
    public List<Spittle> getRecentSpittles(int count);
}
