package lab5.ticket;

class Coordinates {
    private final Long x;
    private final Integer y;

    protected Coordinates(Long x, Integer y) {
        this.y = y;
        this.x = x;
    }

    protected Long getX() {
        return x;
    }

    protected Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}