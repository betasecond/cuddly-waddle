package util.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import util.ics.struct.CourseActivity;
import util.ics.struct.CourseSchedule;

public final class JacksonConfig {
    private JacksonConfig(){}

    public static ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();



        module.addSerializer(CourseActivity.class, new CourseActivitySerializer());
        module.addDeserializer(CourseActivity.class, new CourseActivityDeserializer());

        module.addSerializer(CourseSchedule.class, new CourseScheduleSerializer());
        module.addDeserializer(CourseSchedule.class, new CourseScheduleDeserializer());

        mapper.registerModule(module);

        return mapper;

    }

    @Deprecated public static String serialize(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = getObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper mapper = getObjectMapper();
        return mapper.readValue(json, clazz);
    }


}
