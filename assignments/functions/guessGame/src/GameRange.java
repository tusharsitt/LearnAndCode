public record GameRange(int lower, int upper) {
    public GameRange{
        if (lower >= upper) {
            throw new IllegalArgumentException("Lower bound must be less than upper bound.");
        }
    }
}





