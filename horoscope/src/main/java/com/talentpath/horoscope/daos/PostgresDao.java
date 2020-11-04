package com.talentpath.horoscope.daos;

import com.talentpath.horoscope.exceptions.HoroscopeDaoException;
import com.talentpath.horoscope.exceptions.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Profile({"daotesting", "production"})
public class PostgresDao implements HoroscopeDao{

    @Autowired
    private JdbcTemplate template;

    @Override
    public String getMessage(String sign, int monthValue) throws NullArgumentException, HoroscopeDaoException {
        if(sign == null){
            NullArgumentException toThrow = new NullArgumentException("sign is null");
            throw toThrow;
        }
        try {
            String sqlStatement = "SELECT * FROM \"Horoscopes\" " +
                    "INNER JOIN \"Signs\" " +
                    "ON \"Horoscopes\".\"signId\" = \"Signs\".\"signId\" " +
                    "WHERE \"month\" = " + monthValue + " AND \"name\" = '" + sign + "';";
            String messageResults = template.queryForObject(sqlStatement, new MessageMapper());
            return messageResults;
        } catch (IncorrectResultSizeDataAccessException ex){
            throw new HoroscopeDaoException("Ran select selection query returned no results", ex);
        }
    }

    class MessageMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {

            return resultSet.getString( "message");
        }
    }
}

