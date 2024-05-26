package ics.impl

import biweekly.Biweekly
import biweekly.ICalendar
import biweekly.component.VEvent
import biweekly.property.Description
import biweekly.property.Summary
import ics.TimeSlot
import ics.IcsGenerator
import util.ics.struct.CourseSchedule
import java.time.*
import java.util.Date

class IcsGeneratorImplKt(private val semesterStartDate: LocalDate) : IcsGenerator {
    private val shanghaiZone: ZoneId = ZoneId.of("Asia/Shanghai")

    private val dayMapping = mapOf(
        1 to DayOfWeek.MONDAY,
        2 to DayOfWeek.TUESDAY,
        3 to DayOfWeek.WEDNESDAY,
        4 to DayOfWeek.THURSDAY,
        5 to DayOfWeek.FRIDAY,
        6 to DayOfWeek.SATURDAY,
        7 to DayOfWeek.SUNDAY
    )

    override fun generateIcs(schedule: CourseSchedule): String {
        val ical = ICalendar()

        for (activity in schedule.activities) {
            for (weekIndex in activity.weekIndexes) {
                val event = VEvent().apply {
                    summary = Summary(activity.courseName)
                    description = Description("""
                        Lecturer(s): ${activity.teachers.joinToString(", ")}
                        Course Code: ${activity.courseCode}
                        Room: ${activity.room}
                        Building: ${activity.building}
                        Campus: ${activity.campus}
                        Remark: ${activity.lessonRemark}
                    """.trimIndent())
                }

                val eventDate = semesterStartDate.plusWeeks((weekIndex - 1).toLong())
                    .with(dayMapping[activity.weekday])
                val startTime = TimeSlot.entries[activity.startUnit - 1].startTime
                val endTime = TimeSlot.entries[activity.endUnit - 1].endTime

                val startDateTime = LocalDateTime.of(eventDate, startTime)
                val endDateTime = LocalDateTime.of(eventDate, endTime)

                val startZonedDateTime = ZonedDateTime.of(startDateTime, shanghaiZone)
                val endZonedDateTime = ZonedDateTime.of(endDateTime, shanghaiZone)

                event.setDateStart(Date.from(startZonedDateTime.toInstant()))
                event.setDateEnd(Date.from(endZonedDateTime.toInstant()))

                event.setLocation("${activity.building}, ${activity.room}, ${activity.campus}")

                ical.addEvent(event)
            }
        }

        return Biweekly.write(ical).go()
    }
}
