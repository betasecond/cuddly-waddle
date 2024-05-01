package util.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import util.ics.struct.CourseActivity;
import util.ics.struct.CourseSchedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseScheduleDeserializer extends JsonDeserializer<CourseSchedule> {

    @Override
    public CourseSchedule deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode activitiesNode = node.get("studentTableVm").get("activities");
        List<CourseActivity> activities = new ArrayList<>();

        if (activitiesNode != null && activitiesNode.isArray()) {
            for (JsonNode activityNode : activitiesNode) {
                CourseActivity activity = jp.getCodec().treeToValue(activityNode, CourseActivity.class);
                activities.add(activity);
            }
        }

        return new CourseSchedule(activities);
    }
}
