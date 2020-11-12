package senin.service;

import senin.dto.*;
import senin.exception.IncorrectVertexRuntimeException;
import senin.utility.DataBaseUtility;
import senin.utility.FileParserUtility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphService {

    public static final String THE_RESULT_IS_WRITTEN_TO_RESULT_CSV = "The result is written to result.csv";
    public static final String CAN_T_FIND_VERTEX_WITH_THIS_NAME = "Can't find vertex with this name";

    public GraphService() {
    }

    public void start() {
        DataBaseUtility.createTableForPipeline();

        List<PipeLine> pipeLines = FileParserUtility.readPipelineCSV();
        savePipeLineToDB(pipeLines);

        List<Route> routes = FileParserUtility.readPathCSV();
        List<Vertex> vertices = new ArrayList<>(fillPipeLineVertex(pipeLines));

        Graph graph = new Graph(vertices, pipeLines);
        WaterPipelineService waterPipelineService = new WaterPipelineService(graph);

        List<Result> output = getResultOfGraph(routes, vertices, waterPipelineService);

        FileParserUtility.writeToCSV(output);
        System.out.println(THE_RESULT_IS_WRITTEN_TO_RESULT_CSV);
    }

    private Set<Vertex> fillPipeLineVertex(List<PipeLine> pipeLines) {
        Set<Vertex> vertexesSet = new HashSet<>();
        pipeLines.forEach(pipeLine -> {
            vertexesSet.add(pipeLine.getStart());
            vertexesSet.add(pipeLine.getEnd());
        });
        return vertexesSet;
    }

    private void savePipeLineToDB(List<PipeLine> pipeLines) {
        pipeLines.forEach(pipeLine ->
                DataBaseUtility.insertPipeLineToTable(pipeLine.getStart().getNumber(), pipeLine.getEnd().getNumber(),
                        pipeLine.getLength()));
    }

    private List<Result> getResultOfGraph(List<Route> routes, List<Vertex> vertices, WaterPipelineService waterPipelineService) {
        List<Result> results = new ArrayList<>();
        routes.forEach(route -> {
            Vertex startVertex = findVertexByName(vertices, route.getIdA());
            Vertex endVertex = findVertexByName(vertices, route.getIdB());

            waterPipelineService.execute(startVertex);
            boolean isExist = waterPipelineService.pathExist(endVertex);
            int minLength = isExist ? waterPipelineService.getMinLength(endVertex) : 0;
            results.add(new Result(isExist, minLength));
        });
        return results;
    }

    private Vertex findVertexByName(List<Vertex> vertices, int name) {
        return vertices.stream()
                .filter(vertex -> vertex.getNumber() == name)
                .findFirst().orElseThrow(() -> new IncorrectVertexRuntimeException(CAN_T_FIND_VERTEX_WITH_THIS_NAME));
    }
}
