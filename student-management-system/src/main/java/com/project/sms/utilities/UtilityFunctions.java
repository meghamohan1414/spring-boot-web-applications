package com.project.sms.utilities;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UtilityFunctions {


    public static Integer calculateAge(Date dob) {
        LocalDate currentLocalDate = LocalDate.now();
        LocalDate dobLocalDate = getDobAsLocalDate(dob);
        return Period.between(dobLocalDate,currentLocalDate).getYears();
    }

    private static LocalDate getDobAsLocalDate(Date dob) {
        return dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
