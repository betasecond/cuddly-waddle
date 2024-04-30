package edu.jimei.ics.struct;

import java.util.List;

public record CourseSchedule(
        List<CourseActivity> activities
) {}