package ics.impl

import biweekly.Biweekly
import biweekly.ICalendar
import biweekly.component.VEvent
import biweekly.property.Summary
import ics.IcsGenerator
import ics.TimeSlot
import util.ics.struct.CourseSchedule
import java.time.*
import java.util.*

open class IcsGeneratorImpl(private val semesterStartDate: LocalDate?) : IcsGenerator {
    private val shanghaiZone: ZoneId = ZoneId.of("Asia/Shanghai")


    override fun generateIcs(schedule: CourseSchedule): String {
        val ical = ICalendar()

        for (activity in schedule.activities) {
            for (weekIndex in activity.weekIndexes) {
                val event = VEvent()
                event.summary = Summary(activity.courseName)
                event.setDescription(
                    """
    Lecturer(s): ${java.lang.String.join(", ", activity.teachers)}
    Course Code: ${activity.courseCode}
    Room: ${activity.room}
    Building: ${activity.building}
    Campus: ${activity.campus}
    Remark: ${activity.lessonRemark}
    """.trimIndent()
                )

                // Calculate event date based on week index and weekday
                val eventDate = semesterStartDate?.plusWeeks((weekIndex - 1).toLong())
                    ?.with(dayMapping[activity.weekday])
                val startTime = TimeSlot.entries[activity.startUnit - 1].startTime
                //                debug
                println(activity.endUnit - 1)
                println(TimeSlot.entries[10].endTime)
                val endTime = TimeSlot.entries[activity.endUnit - 1].endTime

                val startDateTime = LocalDateTime.of(eventDate, startTime)
                val endDateTime = LocalDateTime.of(eventDate, endTime)

                val startZonedDateTime = ZonedDateTime.of(startDateTime, shanghaiZone)
                val endZonedDateTime = ZonedDateTime.of(endDateTime, shanghaiZone)


                event.setDateStart(Date.from(startZonedDateTime.toInstant()))
                event.setDateEnd(Date.from(endZonedDateTime.toInstant()))

                event.setLocation(activity.building + ", " + activity.room + ", " + activity.campus)

                ical.addEvent(event)
            }
        }

        return Biweekly.write(ical).go()
    }

    companion object {
        private val dayMapping: MutableMap<Int, DayOfWeek> = HashMap()

        init {
            dayMapping[1] = DayOfWeek.MONDAY
            dayMapping[2] = DayOfWeek.TUESDAY
            dayMapping[3] = DayOfWeek.WEDNESDAY
            dayMapping[4] = DayOfWeek.THURSDAY
            dayMapping[5] = DayOfWeek.FRIDAY
            dayMapping[6] = DayOfWeek.SATURDAY
            dayMapping[7] = DayOfWeek.SUNDAY
        }
    }
}