package edu.jimei.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.jimei.ics.struct.CourseActivity;
import edu.jimei.ics.struct.CourseSchedule;

import java.io.IOException;

public class CourseScheduleSerializer extends JsonSerializer<CourseSchedule> {
    /**
     * @param courseSchedule
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(CourseSchedule courseSchedule, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
