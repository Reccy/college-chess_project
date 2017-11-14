import java.util.Stack;

public class ChessState {

    Square[][] chessSquare = new Square[8][8];

    // TODO: Implement constructor for ChessState inputState
    public ChessState(ChessState inputState) {
        initDataAsNull();
    }

    /**
     * Creates a ChessState data representation based off of the ChessProject GUI
     * @param chessProject
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
                System.out.println("Init Column : " + columns + ", Init Row: " + rows);
                chessSquare[columns][rows] = new Square(columns, rows, "NULL");
            }
        }
    }

    /**
     * Prints the representation of the chess project to the terminal
     */
    public void printDataRepresentation() {
        System.out.println("=========================");
        System.out.println("EVAL: " + evaluateBoard());
        System.out.println("=========================");
        System.out.println("|" + getSquarePieceChar(0,0) + "|" + getSquarePieceChar(1,0) + "|" + getSquarePieceChar(2,0) + "|" + getSquarePieceChar(3,0) + "|" + getSquarePieceChar(4,0) + "|" + getSquarePieceChar(5,0) + "|" + getSquarePieceChar(6,0) + "|" + getSquarePieceChar(7,0) + "|");
        System.out.println("|" + getSquarePieceChar(0,1) + "|" + getSquarePieceChar(1,1) + "|" + getSquarePieceChar(2,1) + "|" + getSquarePieceChar(3,1) + "|" + getSquarePieceChar(4,1) + "|" + getSquarePieceChar(5,1) + "|" + getSquarePieceChar(6,1) + "|" + getSquarePieceChar(7,1) + "|");
        System.out.println("|" + getSquarePieceChar(0,2) + "|" + getSquarePieceChar(1,2) + "|" + getSquarePieceChar(2,2) + "|" + getSquarePieceChar(3,2) + "|" + getSquarePieceChar(4,2) + "|" + getSquarePieceChar(5,2) + "|" + getSquarePieceChar(6,2) + "|" + getSquarePieceChar(7,2) + "|");
        System.out.println("|" + getSquarePieceChar(0,3) + "|" + getSquarePieceChar(1,3) + "|" + getSquarePieceChar(2,3) + "|" + getSquarePieceChar(3,3) + "|" + getSquarePieceChar(4,3) + "|" + getSquarePieceChar(5,3) + "|" + getSquarePieceChar(6,3) + "|" + getSquarePieceChar(7,3) + "|");
        System.out.println("|" + getSquarePieceChar(0,4) + "|" + getSquarePieceChar(1,4) + "|" + getSquarePieceChar(2,4) + "|" + getSquarePieceChar(3,4) + "|" + getSquarePieceChar(4,4) + "|" + getSquarePieceChar(5,4) + "|" + getSquarePieceChar(6,4) + "|" + getSquarePieceChar(7,4) + "|");
        System.out.println("|" + getSquarePieceChar(0,5) + "|" + getSquarePieceChar(1,5) + "|" + getSquarePieceChar(2,5) + "|" + getSquarePieceChar(3,5) + "|" + getSquarePieceChar(4,5) + "|" + getSquarePieceChar(5,5) + "|" + getSquarePieceChar(6,5) + "|" + getSquarePieceChar(7,5) + "|");
        System.out.println("|" + getSquarePieceChar(0,6) + "|" + getSquarePieceChar(1,6) + "|" + getSquarePieceChar(2,6) + "|" + getSquarePieceChar(3,6) + "|" + getSquarePieceChar(4,6) + "|" + getSquarePieceChar(5,6) + "|" + getSquarePieceChar(6,6) + "|" + getSquarePieceChar(7,6) + "|");
        System.out.println("|" + getSquarePieceChar(0,7) + "|" + getSquarePieceChar(1,7) + "|" + getSquarePieceChar(2,7) + "|" + getSquarePieceChar(3,7) + "|" + getSquarePieceChar(4,7) + "|" + getSquarePieceChar(5,7) + "|" + getSquarePieceChar(6,7) + "|" + getSquarePieceChar(7,7) + "|");
        System.out.println("=========================");
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
        return 1;
    }
}
