package ics;

import util.ics.struct.CourseSchedule;

public interface IcsGenerator {
    String generateIcs(CourseSchedule schedule);
}
