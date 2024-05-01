package util.ics.struct;

import java.util.List;

public record CourseSchedule(
        List<CourseActivity> activities
) {}