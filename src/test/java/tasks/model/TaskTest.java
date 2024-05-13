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

    @Nested
    @DisplayName("ECP Tests")
    class ECPTasks {

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
        @DisplayName("ECP Invalid Test Case - Interval 0")
        void testInvalidECPTaskCreationWithInterval0() {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", validStartDate, validEndDate, 0));
        }
    }

    @Nested
    @DisplayName("BVA Tests")
    class BVATasks {

        @ParameterizedTest
        @ValueSource(ints = {1, 60})
        @DisplayName("BVA Valid Test Case")
        @Tag("BVA")
        void testValidBVATaskCreation(int interval) {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, interval));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1})
        @DisplayName("BVA Invalid Test Case for Time")
        void testInvalidBVATaskCreationForTime(int timeOffset) {
            Date invalidStartDate = createDate(1970, Calendar.JANUARY, 1, 0, timeOffset);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", invalidStartDate, validEndDate, 60));
        }
        @ParameterizedTest
        @ValueSource(ints = {1, 60})
        @DisplayName("BVA Valid Test Case")
        @Tag("BVA")
        void testValidBVATaskCreationTime(int timeOffset) {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, timeOffset);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertDoesNotThrow(() -> new Task("Test", validStartDate, validEndDate, 60));
        }


        @ParameterizedTest
        @ValueSource(ints = {0})
        @DisplayName("BVA Invalid Interval Test Case")
        void testInvalidBVATaskCreationForInterval(int interval) {
            Date validStartDate = createDate(2022, Calendar.JANUARY, 1, 10, 0);
            Date validEndDate = createDate(2022, Calendar.JANUARY, 1, 11, 0);
            assertThrows(IllegalArgumentException.class, () -> new Task("Test", validStartDate, validEndDate, interval));
        }
    }
}