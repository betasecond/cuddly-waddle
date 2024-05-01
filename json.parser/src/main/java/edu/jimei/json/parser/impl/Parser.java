package edu.jimei.json.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.jimei.ics.struct.CourseSchedule;
import edu.jimei.json.parser.ParserService;
import edu.jimei.utils.JacksonConfig;

public class Parser implements ParserService {

    /**
     * Parses the given JSON string into a CourseSchedule object.
     * @param json JSON string representing a CourseSchedule.
     * @return Parsed CourseSchedule object or null if parsing fails.
     */
    @Override
    public CourseSchedule parse(String json) {
        try {
            return JacksonConfig.deserialize(json, CourseSchedule.class);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
            return null;
        }
    }
}
