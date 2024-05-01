package ics;

import ics.impl.IcsGeneratorImpl;

import java.time.LocalDate;

public class IcsGeneratorFactory {
    public static IcsGenerator createIcsGenerator(LocalDate date) {
        return new IcsGeneratorImpl(date);
    }
}
