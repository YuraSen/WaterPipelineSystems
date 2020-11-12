package senin.dto;

public class PipeLine {

    private final Vertex start;
    private final Vertex end;
    private final int length;

    public PipeLine(Vertex start, Vertex end, int length) {
        this.start = start;
        this.end = end;
        this.length = length;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getLength() {
        return length;
    }
}
