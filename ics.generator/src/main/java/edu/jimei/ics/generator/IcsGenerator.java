package edu.jimei.ics.generator;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import edu.jimei.ics.struct.CourseActivity;
import edu.jimei.ics.struct.CourseSchedule;

import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IcsGenerator {

    private static final Map<Integer, DayOfWeek> dayMapping = new HashMap<>();
    static {
        dayMapping.put(1, DayOfWeek.MONDAY);
        dayMapping.put(2, DayOfWeek.TUESDAY);
        dayMapping.put(3, DayOfWeek.WEDNESDAY);
        dayMapping.put(4, DayOfWeek.THURSDAY);
        dayMapping.put(5, DayOfWeek.FRIDAY);
        dayMapping.put(6, DayOfWeek.SATURDAY);
        dayMapping.put(7, DayOfWeek.SUNDAY);
    }

    public String generateIcs(CourseSchedule schedule) {
        ICalendar ical = new ICalendar();

        for (CourseActivity activity : schedule.activities()) {
            VEvent event = new VEvent();
            event.setSummary(new Summary(activity.courseName()));
            event.setDescription("Lecturer(s): " + String.join(", ", activity.teachers()) + "\n" +
                    "Course Code: " + activity.courseCode() + "\n" +
                    "Room: " + activity.room() + "\n" +
                    "Building: " + activity.building() + "\n" +
                    "Campus: " + activity.campus() + "\n" +
                    "Remark: " + activity.lessonRemark());

            // Assuming the semester starts on a specific date, e.g., 2024-01-01
            LocalDate semesterStartDate = LocalDate.of(2024, 1, 1);
            LocalDate eventDate = semesterStartDate.with(dayMapping.get(activity.weekday()));

            // Example start time and duration - adjust according to your schedule
            LocalTime startTime = LocalTime.of(activity.startUnit() + 7, 0); // Assuming each unit starts an hour after 7 AM
            LocalTime endTime = LocalTime.of(activity.endUnit() + 7, 0);

            LocalDateTime startDateTime = LocalDateTime.of(eventDate, startTime);
            LocalDateTime endDateTime = LocalDateTime.of(eventDate, endTime);

            event.setDateStart(Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant()));
            event.setDateEnd(Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant()));

            event.setLocation(activity.building() + ", " + activity.room() + ", " + activity.campus());

            ical.addEvent(event);
        }

        return Biweekly.write(ical).go();
    }
}