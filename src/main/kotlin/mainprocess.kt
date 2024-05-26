import ics.IcsGenerator
import ics.IcsGeneratorFactory
import struct.CourseSchedule
import util.json.parser.ParserService
import util.json.parser.ParserServiceFactory
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDate

object MainProcess {
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            println("Usage: java Main <path_to_json_file>")
            return
        }
        val filePath = args[0]
        val path = Paths.get(filePath)
        try {
            val jsonText = Files.readString(path)
            val parser: ParserService = ParserServiceFactory.getParserService()
            val schedule: CourseSchedule = parser.parse(jsonText)
            val generator: IcsGenerator = IcsGeneratorFactory.createIcsGenerator(LocalDate.of(2024, 2, 26))
            val icsContent: String = generator.generateIcs(schedule)
            val outputPath = Paths.get("output.ics")
            Files.write(outputPath, icsContent.toByteArray())
            println("ICS file generated at: " + outputPath.toAbsolutePath())
        } catch (e: IOException) {
            System.err.println("Error reading or writing file: " + e.message)
        } catch (e: Exception) {
            System.err.println("An error occurred: " + e.message)
        }
    }

    @JvmStatic
    fun mainUiArgs(inputFilePath: String, outputFilePath: String): ProcessResult {
        val path = Paths.get(inputFilePath)
        val outputPath = Paths.get(outputFilePath)
        return try {
            val jsonText = Files.readString(path)
            val parser: ParserService = ParserServiceFactory.getParserService()
            val schedule: CourseSchedule = parser.parse(jsonText)
            val generator: IcsGenerator = IcsGeneratorFactory.createIcsGenerator(LocalDate.of(2024, 2, 26))
            val icsContent: String = generator.generateIcs(schedule)
            Files.write(outputPath, icsContent.toByteArray())
            ProcessResult(true, "成功")
        } catch (e: IOException) {
            System.err.println("Error reading or writing file: " + e.message)
            ProcessResult(false, "Error: " + e.message)
        } catch (e: Exception) {
            System.err.println("An error occurred: " + e.message)
            ProcessResult(false, "Error: " + e.message)
        }
    }
    @JvmStatic
    fun mainUiArgs(inputFilePath: String, outputFilePath: String, startDay: LocalDate): ProcessResult {
        val path = Paths.get(inputFilePath)
        val outputPath = Paths.get(outputFilePath)
        return try {
            val jsonText = Files.readString(path)
            val parser: ParserService = ParserServiceFactory.getParserService()
            val schedule: CourseSchedule = parser.parse(jsonText)
            val generator: IcsGenerator = IcsGeneratorFactory.createIcsGenerator(startDay)
            val icsContent: String = generator.generateIcs(schedule)
            Files.write(outputPath, icsContent.toByteArray())
            ProcessResult(true, "成功")
        } catch (e: IOException) {
            System.err.println("Error reading or writing file: " + e.message)
            ProcessResult(false, "Error: " + e.message)
        } catch (e: Exception) {
            System.err.println("An error occurred: " + e.message)
            ProcessResult(false, "Error: " + e.message)
        }
    }
}

data class ProcessResult(val success: Boolean, val message: String)
