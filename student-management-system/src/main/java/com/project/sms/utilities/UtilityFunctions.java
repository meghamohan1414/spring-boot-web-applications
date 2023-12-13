package com.project.sms.utilities;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UtilityFunctions {


    public static Integer calculateAge(LocalDate dob) {
        LocalDate currentLocalDate = LocalDate.now();
        return Period.between(dob,currentLocalDate).getYears();
    }
}
