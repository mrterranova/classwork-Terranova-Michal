package com.talentpath.horoscope.daos;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Profile({"servicetesting", "inmemtesting"})
public class InMemHoroscopeDao implements HoroscopeDao {

    Map<String, String[]> allMessages = new HashMap<>();

    public InMemHoroscopeDao(){

        String[] libraMessages = new String[12];
        libraMessages[0] = "a";
        libraMessages[1] = "b";
        libraMessages[2] = "c";
        libraMessages[3] = "d";
        libraMessages[4] = "e";
        libraMessages[5] = "f";
        libraMessages[6] = "g";
        libraMessages[7] = "h";
        libraMessages[8] = "i";
        libraMessages[9] = "j";
        libraMessages[10] = "k";
        libraMessages[11] = "l";

        allMessages.put("Libra", libraMessages );
        allMessages.put("Leo", libraMessages );
        allMessages.put("Sagittarius", libraMessages );
        allMessages.put("Taurus", libraMessages );
        allMessages.put("Aries", libraMessages );
        allMessages.put("Pisces", libraMessages );
        allMessages.put("Capricorn", libraMessages );
        allMessages.put("Virgo", libraMessages );
        allMessages.put("Cancer", libraMessages );
        allMessages.put("Aquarius", libraMessages );
        allMessages.put("Gemini", libraMessages );
        allMessages.put("Scorpio", libraMessages );

    }


    @Override
    public String getMessage(String sign, int monthValue) {
       String[] monthlyMessages =  allMessages.get(sign);

        return monthlyMessages[monthValue-1];
    }
}
