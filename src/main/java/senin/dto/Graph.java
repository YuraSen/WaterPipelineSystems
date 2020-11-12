package senin.dto;

import java.util.List;

public class Graph {

    private final List<Vertex> vertices;
    private final List<PipeLine> pipeLines;

    public Graph(List<Vertex> vertices, List<PipeLine> pipeLines) {
        this.vertices = vertices;
        this.pipeLines = pipeLines;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<PipeLine> getPipeLines() {
        return pipeLines;
    }
}
