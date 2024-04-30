package edu.jimei.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.jimei.ics.struct.CourseActivity;

import java.io.IOException;

public class CourseActivitySerializer  extends JsonSerializer<CourseActivity> {
    /**
     * @param courseActivity
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(CourseActivity courseActivity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
