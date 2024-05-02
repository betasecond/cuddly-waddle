import ics.TimeSlot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.time.LocalTime;

public class TimeSlotTest {



    @Test
    public void testInRangeIndex() {
        // 尝试访问在范围内的索引，预期不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i <= 10; i++) {
                LocalTime startTime = TimeSlot.values()[i].getStartTime();
            }
        });
    }

    @Test
    public void testInRangeIndex2() {
        // 尝试访问在范围内的索引，预期不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i <= 10; i++) {
                LocalTime endTime = TimeSlot.values()[i].getEndTime();
            }
        });

}
}
