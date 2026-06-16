package com.larisa.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.larisa.dto.Qualification;
@Repository
public class QualificationDaoImpl implements QualificationDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Qualification> getQualifications() {
        String queryQual="select * from qualification";

        // TODO Auto-generated method stub
        return (List<Qualification>)jdbcTemplate.query(queryQual, new QualificationMapper());
    }
    private static final class QualificationMapper implements RowMapper{

        @Override
        public @Nullable Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            // TODO Auto-generated method stub
            return new Qualification(rs.getString("name"), (UUID)rs.getObject("id"));
        }
        
    }

}
