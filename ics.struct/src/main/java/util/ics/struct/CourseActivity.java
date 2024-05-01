package util.ics.struct;

import java.util.List;

public record CourseActivity(
        int lessonId,
        String courseCode,
        String courseName,
        String weeksStr,
        List<Integer> weekIndexes,
        String room,
        String building,
        String campus,
        int weekday,
        int startUnit,
        int endUnit,
        String lessonRemark,
        List<String> teachers,
        Double credits
) {}

