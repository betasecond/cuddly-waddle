package edu.jimei.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.jimei.ics.struct.CourseActivity;
import edu.jimei.ics.struct.CourseSchedule;

import java.io.IOException;

public class CourseScheduleSerializer extends JsonSerializer<CourseSchedule> {
    /**
     * @param provider
     * @param jgen
     * @param value
     * @throws IOException
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
