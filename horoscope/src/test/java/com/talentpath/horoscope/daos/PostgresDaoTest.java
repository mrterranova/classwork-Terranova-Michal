package com.talentpath.horoscope.daos;

import com.talentpath.horoscope.exceptions.HoroscopeDaoException;
import com.talentpath.horoscope.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daotesting")
class PostgresDaoTest {

    @Autowired
    PostgresDao testing;

    @Autowired
    JdbcTemplate template;

    @BeforeEach
    void setUp() {
        String horoscopeDelete = "DELETE FROM \"Horoscopes\";";
        String requestLogDelete = "DELETE FROM \"RequestLog\";";
        String signDelete = "DELETE FROM \"Signs\";";

        template.update(horoscopeDelete);
        template.update(requestLogDelete);
        template.update(signDelete);

        template.update("ALTER SEQUENCE \"RequestLog_logId_seq\" RESTART WITH 1;");
        template.update("ALTER SEQUENCE \"Signs_signId_seq\" RESTART WITH 1;");
    }

    @Test
    void getMessage() throws NullArgumentException, HoroscopeDaoException {
        String sign = "Libra";
        Integer monthValue = 8;
        Integer signId = template.queryForObject("INSERT INTO \"Signs\" (\"name\") VALUES ('Libra') RETURNING \"signId\"", new SignIdMapper());
        template.update("INSERT INTO \"Horoscopes\" (\"signId\", \"month\", \"message\") VALUES ('"+ signId +"', '8', 'you will be eaten by a grue.')");
        String messageTest = testing.getMessage(sign, monthValue);
        assertEquals( "you will be eaten by a grue.", messageTest);
    }

    @Test
    void getMessageNullSign() throws HoroscopeDaoException {
        try {
            String sign = null;
            Integer monthValue = 8;
            Integer signId = template.queryForObject("INSERT INTO \"Signs\" (\"name\") VALUES ('Libra') RETURNING \"signId\"", new SignIdMapper());
            template.update("INSERT INTO \"Horoscopes\" (\"signId\", \"month\", \"message\") VALUES ('" + signId + "', '8', 'you will be eaten by a grue.')");
            String messageTest = testing.getMessage(sign, monthValue);

            fail("Expected NullArgumentExcept but recieved "+ messageTest);
        } catch (NullArgumentException ex){

        }
    }

    class SignIdMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("signId");
        }
    }
}