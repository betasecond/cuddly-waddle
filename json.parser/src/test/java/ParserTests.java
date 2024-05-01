import edu.jimei.ics.struct.CourseSchedule;
import edu.jimei.json.parser.ParserService;
import edu.jimei.json.parser.ParserServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParserTests {

    @Test
    public void testParseValidJson() throws IOException {
        ParserService parser = ParserServiceFactory.getParserService();
        String filepath = "C:\\Users\\nbdhc\\WebstormProjects\\kebiao\\kebiao.json";
        Path path = Paths.get(filepath);


            // 读取文件内容
        String jsonText = new String(Files.readAllBytes(path));
        System.out.println(jsonText);  // 输出 JSON 文本，检查格式是否正确

        CourseSchedule schedule = parser.parse(jsonText);
        Assertions.assertNotNull(schedule);
        Assertions.assertFalse(schedule.activities().isEmpty());
    }

    @Test
    public void testParseInvalidJson() {
        ParserService parser = ParserServiceFactory.getParserService();
        String invalidJson = "{ this is : not json }";
        CourseSchedule schedule = parser.parse(invalidJson);
        Assertions.assertNull(schedule);
    }

    @Test
    public void testParseIncompleteJson() {
        ParserService parser = ParserServiceFactory.getParserService();
        String incompleteJson = """
            {
              "activities": [
                {
                  "lessonId": 1
                  // Missing other required fields
                }
              ]
            }
            """;

        CourseSchedule schedule = parser.parse(incompleteJson);
        Assertions.assertNull(schedule); // Assuming the parser returns null if critical data is missing
    }
}
