package edu.jimei.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import edu.jimei.ics.struct.CourseActivity;

import java.io.IOException;
import java.util.List;

public class CourseActivitySerializer  extends JsonSerializer<CourseActivity> {




    @Override
    public void serialize(
            CourseActivity value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("lessonId", value.lessonId());
        jgen.writeStringField("courseCode", value.courseCode());
        serializeIfNotNull(jgen, "courseName", value.courseName());
        jgen.writeStringField("weeksStr", value.weeksStr());
        serializeWeekIndexes(jgen, value.weekIndexes());
        serializeIfNotNull(jgen, "room", value.room());
        serializeIfNotNull(jgen, "building", value.building());
        serializeIfNotNull(jgen, "campus", value.campus());
        jgen.writeNumberField("weekday", value.weekday());
        jgen.writeNumberField("startUnit", value.startUnit());
        jgen.writeNumberField("endUnit", value.endUnit());
        serializeIfNotNull(jgen, "lessonRemark", value.lessonRemark());
        serializeTeachers(jgen, value.teachers());
        jgen.writeNumberField("credits", value.credits());
        jgen.writeEndObject();
    }

    private void serializeIfNotNull(JsonGenerator jgen, String field, String value) throws IOException {
        if (value != null) {
            jgen.writeStringField(field, value);
        }
    }

    private void serializeWeekIndexes(JsonGenerator jgen, List<Integer> weekIndexes) throws IOException {
        jgen.writeArrayFieldStart("weekIndexes");
        for (Integer weekIndex : weekIndexes) {
            jgen.writeNumber(weekIndex);
        }
        jgen.writeEndArray();
    }

    private void serializeTeachers(JsonGenerator jgen, List<String> teachers) throws IOException {
        jgen.writeArrayFieldStart("teachers");
        for (String teacher : teachers) {
            jgen.writeString(teacher);
        }
        jgen.writeEndArray();
    }
}
