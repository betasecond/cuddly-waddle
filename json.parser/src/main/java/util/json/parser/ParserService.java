package util.json.parser;

import util.ics.struct.CourseSchedule;

/**
 * 接受json Text, 解析成CourseSchedule
 */
public interface ParserService {
    CourseSchedule parse(String json);
}
