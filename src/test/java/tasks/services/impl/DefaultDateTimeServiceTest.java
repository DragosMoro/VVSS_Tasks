package tasks.services.impl;

import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDateTimeServiceTest {
    private static DefaultDateTimeService service;
    private static Date noTimeDate;
    private static String time;
    private static String exceptionMsg;
    private static Date result;

    static Date getDateWithoutTimeUsingCalendar(int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @BeforeAll
    static void beforeAll() {
        service = new DefaultDateTimeService();
        exceptionMsg = "time unit exceeds bounds";
    }

    @BeforeEach
    void setUp() {
        noTimeDate = getDateWithoutTimeUsingCalendar(0, 0);
        result = null;
    }

    @Disabled
    @Test
    void givenValidDateAndTime_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "12:43";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(12, 43);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void given23Hour59Minutes_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "23:59";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(23, 59);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void given00Hour00Minutes_whenParsingDate_thenShouldReturnDate() {
        // arrange
        time = "00:00";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            fail(e.getMessage());
        }

        Date expected = getDateWithoutTimeUsingCalendar(00, 00);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void givenNegativeHours_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "-01:12";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Test
    void givenGreaterThan24Hours_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "24:12";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Test
    void givenNegativeMinutes_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "13:-01";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @Test
    void givenGreaterThan59Minutes_whenParsingDate_thenShouldTrowException() {
        // arrange
        time = "13:60";

        //act
        try {
            result = service.getDateMergedWithTime(time, noTimeDate);
        }
        //assert
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), exceptionMsg);
        }
        assertNull(result);
    }

    @AfterEach
    void tearDown() {
        noTimeDate = null;
        result = null;
    }

    @AfterAll
    static void afterAll() {
        noTimeDate = null;
        result = null;
        service = null;
        exceptionMsg = null;
    }
}