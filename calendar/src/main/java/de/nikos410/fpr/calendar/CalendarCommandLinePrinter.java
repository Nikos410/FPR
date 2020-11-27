package de.nikos410.fpr.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarCommandLinePrinter {
    private static final Locale LOCALE = Locale.getDefault();
    private static final DayOfWeek FIRST_DAY_OF_WEEK = WeekFields.of(LOCALE).getFirstDayOfWeek();
    private static final LocalDate TODAY = LocalDate.now();

    public static void main(String[] args) {
        System.out.println("Using locale " + LOCALE);
        System.out.println(formatCalendar());
    }

    private static String formatCalendar() {
        final var resultBuilder = new StringBuilder();

        final int firstDayOffset = TODAY.withDayOfMonth(1).getDayOfWeek().getValue() - FIRST_DAY_OF_WEEK.getValue() % 7;
        resultBuilder.append(" ".repeat(firstDayOffset * 4));

        for (int i = 1; i <= TODAY.lengthOfMonth(); i++) {
            final var currentDay = TODAY.withDayOfMonth(i);
            resultBuilder.append(formatDay(currentDay));
        }

        return resultBuilder.toString();
    }

    private static String formatDay(LocalDate day) {
        final var resultBuilder = new StringBuilder();

        if (day.getDayOfWeek().equals(FIRST_DAY_OF_WEEK)) {
            resultBuilder.append('\n');
        }

        resultBuilder.append(String.format("%3s", day.getDayOfMonth()));

        if (day.equals(TODAY)) {
            resultBuilder.append('*');
        } else {
            resultBuilder.append(' ');
        }

        return resultBuilder.toString();
    }
}
