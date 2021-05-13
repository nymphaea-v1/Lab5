package lab5.ticket;

class Coordinates {
    private Long x;
    private Integer y;

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