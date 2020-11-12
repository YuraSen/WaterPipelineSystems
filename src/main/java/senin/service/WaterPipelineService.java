package senin.service;

import senin.dto.Graph;
import senin.dto.PipeLine;
import senin.dto.Vertex;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

public class WaterPipelineService {

    private List<PipeLine> pipeLines;
    private Set<Vertex> visitedVertices;
    private Set<Vertex> unvisitedVertices;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> length;

    public WaterPipelineService(Graph graph) {
        pipeLines = new ArrayList<>(graph.getPipeLines());
    }

    public int getMinLength(Vertex vertex) {
        return length.get(vertex);
    }

    private boolean isVisited(Vertex vertex) {
        return visitedVertices.contains(vertex);
    }

    public boolean pathExist(Vertex destination) {
        return predecessors.get(destination) != null;
    }

    public void execute(Vertex source) {
        clearTempData();
        length.put(source, 0);
        unvisitedVertices.add(source);
        findDistancesOfGraph();
    }

    private void findDistancesOfGraph() {
        while (unvisitedVertices.size() > 0) {
            Vertex vertex = getMinimum(unvisitedVertices);
            visitedVertices.add(vertex);
            unvisitedVertices.remove(vertex);
            findMinimalDistances(vertex);
        }
    }

    private void clearTempData() {
        visitedVertices = new HashSet<>();
        unvisitedVertices = new HashSet<>();
        predecessors = new HashMap<>();
        length = new HashMap<>();
    }

    private int getDistance(Vertex vertex, Vertex target) {
        return pipeLines.stream()
                .filter(pipeLine -> (pipeLine.getStart().equals(vertex) && pipeLine.getEnd().equals(target)))
                .mapToInt(PipeLine::getLength)
                .findFirst()
                .orElse(0);
    }

    private List<Vertex> getNeighbors(Vertex vertex) {
        return pipeLines.stream()
                .filter(pipeLine -> (pipeLine.getStart().equals(vertex) && !isVisited(pipeLine.getEnd())))
                .map(PipeLine::getEnd)
                .collect(Collectors.toList());
    }

    private Vertex getMinimum(Set<Vertex> vertices) {
        return Collections.min(vertices);
    }

    private void findMinimalDistances(Vertex vertex) {
        getNeighbors(vertex).stream()
                .filter(target -> getMinimumLength(target) > getMinimumLength(vertex) + getDistance(vertex, target))
                .forEach(target -> {
                    length.put(target, getMinimumLength(vertex)
                            + getDistance(vertex, target));
                    predecessors.put(target, vertex);
                    unvisitedVertices.add(target);
                });
    }

    private int getMinimumLength(Vertex destination) {
        Integer d = length.get(destination);
        return requireNonNullElse(d, Integer.MAX_VALUE);
    }
}
