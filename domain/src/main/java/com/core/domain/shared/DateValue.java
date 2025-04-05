package com.core.domain.shared;

import java.time.LocalDate;
import java.util.Objects;

public record DateValue(LocalDate date) {

    public DateValue {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }

    @Override
    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateValue that = (DateValue) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public String toString() {
        return "DateValue{" +
                "date=" + date +
                '}';
    }

    public static DateValue from(LocalDate date) {
        return new DateValue(date);
    }

    public LocalDate toLocalDate() {
        return this.date;
    }
}
