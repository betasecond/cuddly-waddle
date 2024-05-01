package util.MainProcess;

import ics.IcsGenerator;
import ics.IcsGeneratorFactory;
import util.ics.struct.CourseSchedule;
import util.json.parser.ParserService;
import util.json.parser.ParserServiceFactory;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class MainProcess {
    public static void main(String[] args) {
        // 检查命令行参数是否提供了文件路径
//        if (args.length < 1) {
//            System.out.println("Usage: java Main <path_to_json_file>");
//            return;
//        }
//
//        String filePath = args[0];
//        Path path = Paths.get(filePath);
        String filepath = "C:\\Users\\nbdhc\\WebstormProjects\\kebiao\\kebiao.json";
        Path path = Paths.get(filepath);

        try {
            // 读取文件内容
            String jsonText = new String(Files.readAllBytes(path));

            // 解析 JSON 文本为 CourseSchedule 对象
            ParserService parser = ParserServiceFactory.getParserService();
            CourseSchedule schedule = parser.parse(jsonText);

            // 生成 ICS 文件内容
            IcsGenerator generator = IcsGeneratorFactory.createIcsGenerator(LocalDate.of(2024, 2, 26));  // 开学日期假设为 2024 年 1 月 1 日
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


    public static ProcessResult  mainUiArgs(String Inputfilepath,String Outputfilepath) {

        Path path = Paths.get(Inputfilepath);
        Path outputPath = Paths.get(Outputfilepath);
        try {
            // 读取文件内容
            String jsonText = new String(Files.readAllBytes(path));

            // 解析 JSON 文本为 CourseSchedule 对象
            ParserService parser = ParserServiceFactory.getParserService();
            CourseSchedule schedule = parser.parse(jsonText);

            // 生成 ICS 文件内容
            IcsGenerator generator = IcsGeneratorFactory.createIcsGenerator(LocalDate.of(2024, 2, 26));  // 开学日期假设为 2024 年 1 月 1 日
            String icsContent = generator.generateIcs(schedule);

            // 将 ICS 文件内容写入文件

            Files.write(outputPath, icsContent.getBytes());
            System.out.println("ICS file generated at: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
            return new ProcessResult(false, "Error: " + e.getMessage());

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return new ProcessResult(false, "Error: " + e.getMessage());

        }
        return new ProcessResult(Boolean.TRUE,"成功");
    }
}
