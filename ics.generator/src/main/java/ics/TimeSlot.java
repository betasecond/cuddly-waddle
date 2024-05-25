package ics;
import java.time.LocalTime;

public enum TimeSlot {
    SLOT_1(LocalTime.of(8, 0), LocalTime.of(8, 45)),
    SLOT_2(LocalTime.of(8, 50), LocalTime.of(9, 35)),
    SLOT_3(LocalTime.of(10, 5), LocalTime.of(10, 50)),
    SLOT_4(LocalTime.of(11, 5), LocalTime.of(11, 50)),
    SLOT_5(LocalTime.of(14, 0), LocalTime.of(14, 45)),
    SLOT_6(LocalTime.of(14, 50), LocalTime.of(15, 35)),
    SLOT_7(LocalTime.of(15, 55), LocalTime.of(16, 40)),
    SLOT_8(LocalTime.of(16, 45), LocalTime.of(17, 30)),
    SLOT_9(LocalTime.of(19, 0), LocalTime.of(19, 45)),
    SLOT_10(LocalTime.of(19, 50), LocalTime.of(20, 35)),
    SLOT_11(LocalTime.of(20, 40), LocalTime.of(21, 25));

    private final LocalTime startTime;
    private final LocalTime endTime;

    TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
