package edu.jimei.json.parser;

import edu.jimei.ics.struct.CourseSchedule;

/**
 * 接受json Text, 解析成CourseSchedule
 */
public interface ParserService {
    CourseSchedule parse(String json);
}
