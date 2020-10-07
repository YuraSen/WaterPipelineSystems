package senin.dto;

import java.util.Objects;

public class Vertex implements Comparable<Vertex>{
    private final int number;

    public Vertex(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return number == vertex.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(number, o.number);
    }
}
