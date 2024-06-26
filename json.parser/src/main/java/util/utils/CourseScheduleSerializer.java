package util.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import util.ics.struct.CourseSchedule;
import util.ics.struct.CourseActivity;


import java.io.IOException;

public class CourseScheduleSerializer extends JsonSerializer<CourseSchedule> {
    /**
     */
    @Override
    public void serialize(CourseSchedule value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeFieldName("activities");
        jgen.writeStartArray();
        for (CourseActivity activity : value.activities()) {
            provider.defaultSerializeValue(activity, jgen);
        }
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
