package senin.dto;

public class Result {

    private final boolean isExist;
    private final int minLength;

    public Result(boolean isExist, int minLength) {
        this.isExist = isExist;
        this.minLength = minLength;
    }

    public boolean isExist() {
        return isExist;
    }

    public int getMinLength() {
        return minLength;
    }
}
