package edu.jimei.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import edu.jimei.ics.struct.CourseActivity;
import edu.jimei.ics.struct.CourseSchedule;

import java.io.IOException;

public class CourseScheduleDeserializer extends JsonDeserializer<CourseSchedule> {
    /**
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JacksonException
     */
    @Override
    public CourseSchedule deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
