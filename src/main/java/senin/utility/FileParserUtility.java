package senin.utility;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import senin.dto.PipeLine;
import senin.dto.Result;
import senin.dto.Route;
import senin.dto.Vertex;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.opencsv.ICSVParser.DEFAULT_QUOTE_CHARACTER;
import static com.opencsv.ICSVParser.DEFAULT_SEPARATOR;


public class FileParserUtility {
    public static final String PIPELINE_SYS_CSV = "src/main/resources/pipelineSys.csv";
    public static final String VERTEX_CSV = "src/main/resources/vertex.csv";
    public static final int TITLE_LINE = 1;
    private static final String pathResult = "src/main/resources/result.csv";

    public static List<PipeLine> readPipelineCSV() {
        File pipeLineFile = new File(PIPELINE_SYS_CSV);
        List<PipeLine> pipelineList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pipeLineFile),
                DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, TITLE_LINE)) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                pipelineList.add(new PipeLine(new Vertex(Integer.parseInt(line[0])), new Vertex(Integer.parseInt(line[1])), Integer.parseInt(line[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pipelineList;
    }

    public static List<Route> readPathCSV() {
        File parseFile = new File(VERTEX_CSV);
        List<Route> routePipeLines = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(parseFile),
                DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, TITLE_LINE)) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                routePipeLines.add(new Route(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return routePipeLines;
    }

    public static void writeToCSV(List<Result> results) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(pathResult));
             CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {
            csvWriter.writeNext(new String[]{"ROUTE EXISTS", "MIN LENGTH"});
            results.forEach(result -> {
                if (result.getMinLength() == 0) csvWriter.writeNext(new String[]{String.valueOf(result.isExist())});
                else csvWriter.writeNext(new String[]{String.valueOf(result.isExist()), String.valueOf(result.getMinLength())});
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
