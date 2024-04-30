package edu.jimei.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import edu.jimei.ics.struct.CourseActivity;

import java.io.IOException;

public class CourseActivityDeserializer extends JsonDeserializer<CourseActivity> {
    /**
     * @param jsonParser
     * @param deserializationContext
     * @return
     * @throws IOException
     * @throws JacksonException
     */
    @Override
    public CourseActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
