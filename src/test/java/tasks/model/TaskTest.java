package tasks.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {

    private Date createDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

        @Test
        @DisplayName("ECP Valid Test Case - Positive Time ")
        @Tag("ECP")
        void testValidECPTaskCreation() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, 60));
        }

        @Test
        @DisplayName("ECP Invalid Test Case - Negative Time")
        @Tag("ECP")
        void testInvalidECPTaskCreationNegativeTime() {
            Date invalidStartDate = createDate(1969, Calendar.DECEMBER, 31, 23, 59);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", invalidStartDate, validEndDate, 60));
        }

        @Test
        @DisplayName("ECP Valid Test Case - Interval 10")
        void testValidECPTaskCreationWithInterval10() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, 10));
        }

        @Test
        void testInvalidECPTaskCreationWithInterval0() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", validStartDate, validEndDate, 0));
        }

        @Test
        void testValidBVATaskCreation() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, 1));
        }

        @Test
        void testInvalidBVATaskCreationForTime() {
            Date invalidStartDate = createDate(1970, Calendar.JANUARY, 1, 0, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", invalidStartDate, validEndDate, 60));
        }
        @Test
        void testValidBVATaskCreationTime() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 1);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, 60));
        }

        @Test
        void testInvalidBVATaskCreationForInterval() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", validStartDate, validEndDate, -1));
        }
}