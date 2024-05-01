package edu.jimei.ics.generator;

import edu.jimei.ics.generator.impl.IcsGeneratorImpl;

import java.time.LocalDate;

public class IcsGeneratorFactory {
    public static IcsGenerator createIcsGenerator(LocalDate date) {
        return new IcsGeneratorImpl(date);
    }
}
