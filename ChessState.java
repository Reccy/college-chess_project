import java.util.Stack;

public class ChessState {

    private Square[][] chessSquare = new Square[8][8];

    /**
     * Creates a ChessState data representation based off of the input value
     */
    public ChessState(ChessState inputState) {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                chessSquare[columns][rows] = inputState.chessSquare[columns][rows];
            }
        }
    }

    /**
     * Creates a ChessState data representation based off of the ChessProject GUI
     */
    public ChessState(ChessProject chessProject) {
        initDataAsNull();

        Stack<Square> chessSquares = chessProject.findAllSquares();

        for (Square square : chessSquares) {
            square.print();
            chessSquare[square.getPosX()][square.getPosY()] = square;
        }

    }

    /**
     * Initialises the data representation so that each piece is null
     */
    private void initDataAsNull() {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                chessSquare[columns][rows] = new Square(columns, rows, "NULL");
            }
        }
    }

    /**
     * Prints the representation of the chess project to the terminal
     */
    public void printDataRepresentation(String title) {
        System.out.println("=========================");

        if (title.length() > 0)
            System.out.println(title);

        System.out.println("EVAL: " + evaluateBoard());
        System.out.println("=========================");
        System.out.println("   0  1  2  3  4  5  6  7");
        System.out.println("0 |" + getSquarePieceChar(0,0) + "|" + getSquarePieceChar(1,0) + "|" + getSquarePieceChar(2,0) + "|" + getSquarePieceChar(3,0) + "|" + getSquarePieceChar(4,0) + "|" + getSquarePieceChar(5,0) + "|" + getSquarePieceChar(6,0) + "|" + getSquarePieceChar(7,0) + "|");
        System.out.println("1 |" + getSquarePieceChar(0,1) + "|" + getSquarePieceChar(1,1) + "|" + getSquarePieceChar(2,1) + "|" + getSquarePieceChar(3,1) + "|" + getSquarePieceChar(4,1) + "|" + getSquarePieceChar(5,1) + "|" + getSquarePieceChar(6,1) + "|" + getSquarePieceChar(7,1) + "|");
        System.out.println("2 |" + getSquarePieceChar(0,2) + "|" + getSquarePieceChar(1,2) + "|" + getSquarePieceChar(2,2) + "|" + getSquarePieceChar(3,2) + "|" + getSquarePieceChar(4,2) + "|" + getSquarePieceChar(5,2) + "|" + getSquarePieceChar(6,2) + "|" + getSquarePieceChar(7,2) + "|");
        System.out.println("3 |" + getSquarePieceChar(0,3) + "|" + getSquarePieceChar(1,3) + "|" + getSquarePieceChar(2,3) + "|" + getSquarePieceChar(3,3) + "|" + getSquarePieceChar(4,3) + "|" + getSquarePieceChar(5,3) + "|" + getSquarePieceChar(6,3) + "|" + getSquarePieceChar(7,3) + "|");
        System.out.println("4 |" + getSquarePieceChar(0,4) + "|" + getSquarePieceChar(1,4) + "|" + getSquarePieceChar(2,4) + "|" + getSquarePieceChar(3,4) + "|" + getSquarePieceChar(4,4) + "|" + getSquarePieceChar(5,4) + "|" + getSquarePieceChar(6,4) + "|" + getSquarePieceChar(7,4) + "|");
        System.out.println("5 |" + getSquarePieceChar(0,5) + "|" + getSquarePieceChar(1,5) + "|" + getSquarePieceChar(2,5) + "|" + getSquarePieceChar(3,5) + "|" + getSquarePieceChar(4,5) + "|" + getSquarePieceChar(5,5) + "|" + getSquarePieceChar(6,5) + "|" + getSquarePieceChar(7,5) + "|");
        System.out.println("6 |" + getSquarePieceChar(0,6) + "|" + getSquarePieceChar(1,6) + "|" + getSquarePieceChar(2,6) + "|" + getSquarePieceChar(3,6) + "|" + getSquarePieceChar(4,6) + "|" + getSquarePieceChar(5,6) + "|" + getSquarePieceChar(6,6) + "|" + getSquarePieceChar(7,6) + "|");
        System.out.println("7 |" + getSquarePieceChar(0,7) + "|" + getSquarePieceChar(1,7) + "|" + getSquarePieceChar(2,7) + "|" + getSquarePieceChar(3,7) + "|" + getSquarePieceChar(4,7) + "|" + getSquarePieceChar(5,7) + "|" + getSquarePieceChar(6,7) + "|" + getSquarePieceChar(7,7) + "|");
        System.out.println("=========================");
    }

    /**
     * Performs a move
     */
    public void performMove(Move move) {
        Square startingSquare = move.getStart();
        Square landingSquare = move.getLanding();

        System.out.println("Moving " + startingSquare.getName() + " from " + startingSquare.getPosX() + ", " + startingSquare.getPosY() + " to " + landingSquare.getPosX() + ", " + landingSquare.getPosY());

        chessSquare[landingSquare.getPosX()][landingSquare.getPosY()] = new Square(landingSquare.getPosX(), landingSquare.getPosY(), startingSquare.getName());
        chessSquare[startingSquare.getPosX()][startingSquare.getPosY()] = new Square(startingSquare.getPosX(), startingSquare.getPosY(), "NULL");
    }

    /**
     * Returns the string of the piece
     */
    public String getSquarePieceChar(int column, int row) {
        Square squarePiece = chessSquare[column][row];

        switch (squarePiece.getName()) {
            case "WhitePawn":
                return "wp";
            case "WhiteRook":
                return "wr";
            case "WhiteKnight":
                return "wk";
            case "WhiteBishop":
                return "wb";
            case "WhiteKing":
                return "wK";
            case "WhiteQueen":
                return "wq";
            case "BlackPawn":
                return "bp";
            case "BlackRook":
                return "br";
            case "BlackKnight":
                return "bk";
            case "BlackBishop":
                return "bb";
            case "BlackKing":
                return "bK";
            case "BlackQueen":
                return "bq";
            default:
                return "  ";
        }
    }

    /**
     * TODO: Create evaluation function
     */
    public int evaluateBoard() {
        int evaluation = 0;

        // Evaluate agent pawn positions
        for (Square pawn : getWhitePawns()) {
            evaluation += pawn.getPosY();
        }

        // Evaluate player pawn positions
        for (Square pawn : getBlackPawns()) {
            evaluation -= pawn.getPosY();
        }

        return evaluation;
    }

    //region Piece Finders

    /**
     * Returns all of the white pawn squares
     */
    private Stack<Square> getWhitePawns() {
        Stack<Square> pawns = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhitePawn")) {
                    pawns.push(chessSquare[columns][rows]);
                }
            }
        }

        return pawns;
    }

    /**
     * Returns all of the black pawn squares
     */
    private Stack<Square> getBlackPawns() {
        Stack<Square> pawns = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackPawn")) {
                    pawns.push(chessSquare[columns][rows]);
                }
            }
        }

        return pawns;
    }

    //endregion
}
