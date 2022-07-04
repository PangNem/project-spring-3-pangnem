package io.test;

import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateTest {

    @Test
    void plusSecond() {
        int year = 2022;
        int monthOfYear = 5;
        int dayOfMonth = 11;
        int hourOfDay = 10;
        int minuteOfHour = 31;
        DateTime dateTime = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 30);

        DateTime newDateTime = dateTime.plusSeconds(15);

        assertThat(newDateTime)
                .isEqualTo(new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, 45));
        System.out.println("newDateTime.toDate() = " + newDateTime.toDate());
    }
}
