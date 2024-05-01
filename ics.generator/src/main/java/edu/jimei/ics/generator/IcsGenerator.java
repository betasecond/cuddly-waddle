package edu.jimei.ics.generator;

import edu.jimei.ics.struct.CourseSchedule;

public interface IcsGenerator {
    String generateIcs(CourseSchedule schedule);
}
