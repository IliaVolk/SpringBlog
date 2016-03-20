package com.springapp.dao;

import com.springapp.entities.Spitter;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcSpitterDao extends SimpleJdbcDaoSupport implements SpitterDao {
    private static final String SQL_SELECT_SPITTER_BY_ID =
            "select id, username from spitter where id = ?;";
    @Override
    public Spitter getSpitterById(long id) {
        return getSimpleJdbcTemplate().queryForObject(
                SQL_SELECT_SPITTER_BY_ID,
                new ParameterizedRowMapper<Spitter>() {
                    @Override
                    public Spitter mapRow(ResultSet resultSet,
                                          int rowNumber) throws SQLException {
                        Spitter spitter = new Spitter();
                        spitter.setId(resultSet.getLong(1));
                        spitter.setUsername(resultSet.getString(2));
                        return spitter;
                    }
                }, id
        );
    }
    private final static String SQL_INSERT_SPITTER =
            "insert into spitter" +
                    " (username)" +
                    " values (?)";

    @Override
    public void saveSpitter(Spitter spitter) {
        getSimpleJdbcTemplate().update(
                SQL_INSERT_SPITTER,
                spitter.getUsername()
        );
    }

    @Override
    public List<Spitter> getSpittersByName(String name) {
        return null;
    }

    @Override
    public void deleteSpitter(Spitter spitter) {

    }

}
