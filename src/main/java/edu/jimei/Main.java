package edu.jimei;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 检查命令行参数是否提供了文件路径
        if (args.length < 1) {
            System.out.println("Usage: java Main <path_to_json_file>");
            return;
        }

        String filePath = args[0];
        Path path = Paths.get(filePath);

        try {
            // 读取文件内容
            String jsonText = new String(Files.readAllBytes(path));

            // 解析 JSON 文本为 CourseSchedule 对象
            ParserService parser = new JsonParserService();
            CourseSchedule schedule = parser.parse(jsonText);

            // 生成 ICS 文件内容
            IcsGenerator generator = new BasicIcsGenerator(LocalDate.of(2024, 1, 1));  // 开学日期假设为 2024 年 1 月 1 日
            String icsContent = generator.generateIcs(schedule);

            // 将 ICS 文件内容写入文件
            Path outputPath = Paths.get("output.ics");
            Files.write(outputPath, icsContent.getBytes());
            System.out.println("ICS file generated at: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
