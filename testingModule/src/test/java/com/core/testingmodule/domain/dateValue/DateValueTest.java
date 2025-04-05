package com.core.testingmodule.domain.dateValue;

import com.core.domain.shared.DateValue;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateValueTest {

    @Test
    void testConstructor_ThrowsException_WhenDateIsNull() {
        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new DateValue(null));
        assertEquals("Date cannot be null", thrown.getMessage());
    }

    @Test
    void testConstructor_Successful_WhenDateIsValid() {
        // Arrange
        LocalDate expectedDate = LocalDate.of(2025, 2, 19);

        // Act
        DateValue dateValue = new DateValue(expectedDate);

        // Assert
        assertEquals(expectedDate, dateValue.date(), "Date should be correctly set.");
    }

    @Test
    void testEquals_ReturnsTrue_WhenDatesAreEqual() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 2, 19);
        DateValue dateValue1 = new DateValue(date);
        DateValue dateValue2 = new DateValue(date);

        // Act & Assert
        assertTrue(dateValue1.equals(dateValue2), "DateValue instances with the same date should be equal.");
    }

    @Test
    void testEquals_ReturnsFalse_WhenDatesAreDifferent() {
        // Arrange
        DateValue dateValue1 = new DateValue(LocalDate.of(2025, 2, 19));
        DateValue dateValue2 = new DateValue(LocalDate.of(2025, 2, 20));

        // Act & Assert
        assertNotEquals(dateValue1, dateValue2, "DateValue instances with different dates should not be equal.");
    }

    @Test
    void testToString_ReturnsCorrectFormat() {
        // Arrange
        LocalDate date = LocalDate.of(2025, 2, 19);
        DateValue dateValue = new DateValue(date);

        // Act
        String result = dateValue.toString();

        // Assert
        assertEquals("DateValue{date=" + date + "}", result, "The toString method should return the correct format.");
    }

    @Test
    void testFrom_ReturnsCorrectDateValueInstance() {
        // Arrange
        LocalDate expectedDate = LocalDate.of(2025, 2, 19);

        // Act
        DateValue dateValue = DateValue.from(expectedDate);

        // Assert
        assertEquals(expectedDate, dateValue.date(), "The from() method should return the correct DateValue instance.");
    }

    @Test
    void testToLocalDate_ReturnsCorrectLocalDate() {
        // Arrange
        LocalDate expectedDate = LocalDate.of(2025, 2, 19);
        DateValue dateValue = new DateValue(expectedDate);

        // Act
        LocalDate result = dateValue.toLocalDate();

        // Assert
        assertEquals(expectedDate, result, "The toLocalDate() method should return the correct LocalDate.");
    }
}
