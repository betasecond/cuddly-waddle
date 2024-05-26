package util.json.parser.impl

import com.fasterxml.jackson.core.JsonProcessingException
import util.ics.struct.CourseSchedule


import util.json.parser.ParserService
import util.utils.JacksonConfig

class ParserKt : ParserService {

    /**
     * Parses the given JSON string into a CourseSchedule object.
     * @param json JSON string representing a CourseSchedule.
     * @return Parsed CourseSchedule object or null if parsing fails.
     */
    override fun parse(json: String?): CourseSchedule? {
        try {
            return JacksonConfig.deserialize(json, CourseSchedule::class.java)
        } catch (e: JsonProcessingException) {
            System.err.println("Failed to parse JSON: ${e.message}")
            return null
        }
    }
}