package ics.impl;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import ics.TimeSlot;
import ics.IcsGenerator;
import util.ics.struct.CourseActivity;
import util.ics.struct.CourseSchedule;

import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IcsGeneratorImpl implements IcsGenerator {
    private final LocalDate semesterStartDate;
    private ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");

    public IcsGeneratorImpl(LocalDate semesterStartDate) {
        this.semesterStartDate = semesterStartDate;

    }


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

    public String generateIcs(CourseSchedule schedule){
        ICalendar ical = new ICalendar();

        for (CourseActivity activity : schedule.activities()) {
            for (int weekIndex : activity.weekIndexes()) {
                VEvent event = new VEvent();
                event.setSummary(new Summary(activity.courseName()));
                event.setDescription("Lecturer(s): " + String.join(", ", activity.teachers()) + "\n" +
                        "Course Code: " + activity.courseCode() + "\n" +
                        "Room: " + activity.room() + "\n" +
                        "Building: " + activity.building() + "\n" +
                        "Campus: " + activity.campus() + "\n" +
                        "Remark: " + activity.lessonRemark());

                // Calculate event date based on week index and weekday
                LocalDate eventDate = semesterStartDate.plusWeeks(weekIndex - 1)
                        .with(dayMapping.get(activity.weekday()));
                LocalTime startTime = TimeSlot.values()[activity.startUnit() - 1].getStartTime();
                LocalTime endTime = TimeSlot.values()[activity.startUnit() - 1].getEndTime();

                LocalDateTime startDateTime = LocalDateTime.of(eventDate, startTime);
                LocalDateTime endDateTime = LocalDateTime.of(eventDate, endTime);

                ZonedDateTime startZonedDateTime = ZonedDateTime.of(startDateTime, shanghaiZone);
                ZonedDateTime endZonedDateTime = ZonedDateTime.of(endDateTime, shanghaiZone);


                event.setDateStart(Date.from(startZonedDateTime.toInstant()));
                event.setDateEnd(Date.from(endZonedDateTime.toInstant()));

                event.setLocation(activity.building() + ", " + activity.room() + ", " + activity.campus());

                ical.addEvent(event);
            }
        }

        return Biweekly.write(ical).go();
    }
}