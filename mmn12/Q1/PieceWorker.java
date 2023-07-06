public class PieceWorker extends Employee {
    private double piecePrice; // price per piece
    private int piecesCount; // number of pieces created

    // constructor
    public PieceWorker(String firstName, String lastName,
                          String socialSecurityNumber, BirthDate birthDate, double piecePrice, int piecesCount) {
        super(firstName, lastName, socialSecurityNumber, birthDate);

        if (piecePrice < 0.0) { // validate piece price
            throw new IllegalArgumentException("Piece price must be >= 0.0");
        }

        if (piecesCount < 0) { // validate pieces count
            throw new IllegalArgumentException(
                    "Pieces count must be >= 0");
        }

        this.piecePrice = piecePrice;
        this.piecesCount = piecesCount;
    }

    // set piece price
    public void setPiecePrice(double piecePrice) {
        if (piecePrice < 0.0) { // validate wage
            throw new IllegalArgumentException("Piece price must be >= 0.0");
        }

        this.piecePrice = piecePrice;
    }

    // return piece price
    public double getPiecePrice() {
        return piecePrice;
    }

    // set pieces count
    public void setPiecesCount(int piecesCount) {
        if (piecesCount < 0) { // validate pieces count
            throw new IllegalArgumentException(
                    "Pieces count must be >= 0");
        }

        this.piecesCount = piecesCount;
    }

    // return pieces count
    public int getPiecesCount() {
        return piecesCount;
    }

    // calculate earnings; override abstract method earnings in Employee
    @Override
    protected double baseEarnings() {
        return piecesCount * piecePrice;
    }

    // return String representation of HourlyEmployee object
    @Override
    public String toString() {
        return String.format("piece worker: %s%n%s: $%,.2f; %s: %d",
                super.toString(), "piece price", getPiecePrice(),
                "pieces count", getPiecesCount());
    }
}
