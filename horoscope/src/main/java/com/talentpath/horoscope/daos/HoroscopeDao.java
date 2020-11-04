package com.talentpath.horoscope.daos;

import com.talentpath.horoscope.exceptions.HoroscopeDaoException;
import com.talentpath.horoscope.exceptions.NullArgumentException;

public interface HoroscopeDao {

    String getMessage(String sign, int monthValue) throws NullArgumentException, HoroscopeDaoException;

}
