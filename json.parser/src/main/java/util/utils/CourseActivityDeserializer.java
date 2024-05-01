package util.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import util.ics.struct.CourseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CourseActivityDeserializer extends JsonDeserializer<CourseActivity> {

    @Override
    public CourseActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        int lessonId = node.get("lessonId").asInt();
        String courseCode = node.hasNonNull("courseCode") ? node.get("courseCode").asText() : null;
        String courseName = node.hasNonNull("courseName") ? node.get("courseName").asText() : null;
        String weeksStr = node.get("weeksStr").asText();
//        对weekIndex的特殊处理
        List<Integer> weekIndexes;
        JsonNode weekIndexesNode = node.get("weekIndexes");
        if (weekIndexesNode != null && weekIndexesNode.isArray()) {
            weekIndexes = new ArrayList<>();
            try (JsonParser weekIndexesParser = weekIndexesNode.traverse()) {
                weekIndexesParser.nextToken(); // 跳过开始数组的标记
                while (weekIndexesParser.nextToken() != JsonToken.END_ARRAY) {
                    weekIndexes.add(weekIndexesParser.getIntValue());
                }
            }
        } else {
            weekIndexes = Collections.emptyList();
        }

        String room = node.hasNonNull("room") ? node.get("room").asText() : null;
        String building = node.hasNonNull("building") ?node.get("building").asText() : null;
        String campus = node.hasNonNull("campus") ? node.get("campus").asText() : null;
        int weekday =  node.hasNonNull("weekday")? node.get("weekday").asInt() : 0;
        int startUnit = node.hasNonNull("startUnit") ? node.get("startUnit").asInt() : 0;
        int endUnit = node.hasNonNull("endUnit") ? node.get("endUnit").asInt() : 0;
        String lessonRemark = node.hasNonNull("lessonRemark")?node.get("lessonRemark").asText() : null;
        List<String> teachers = new ArrayList<>();
        JsonNode teachersNode = node.get("teachers");
        if (teachersNode != null && teachersNode.isArray()) {
            // 使用增强的 for 循环来遍历数组节点
            for (JsonNode teacherNode : teachersNode) {
                if (teacherNode.isTextual()) {  // 检查节点是否为文本类型
                    teachers.add(teacherNode.asText());
                }
            }
        } else {
            teachers = Collections.emptyList(); // 如果不存在或者不是数组，则使用空列表
        }

        double credits = node.hasNonNull("credits")?node.get("credits").asDouble() : 0.0;

        return new CourseActivity(lessonId, courseCode, courseName, weeksStr, weekIndexes,
                room, building, campus, weekday, startUnit, endUnit,
                lessonRemark, teachers, credits);
    }
}

