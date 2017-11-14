import java.util.Stack;

public class ChessState {

    private Square[][] chessSquare = new Square[8][8];
    private ChessProject chessProject;

    /**
     * Creates a ChessState data representation based off of the input value
     */
    public ChessState(ChessState inputState) {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                chessSquare[columns][rows] = inputState.chessSquare[columns][rows];
            }
        }

        chessProject = inputState.chessProject;
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

        this.chessProject = chessProject;
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

        System.out.println("AGENT ADVANTAGE: " + evaluateBoard());
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
     * Evaluates the board to see what the agent's advantage is.
     * The higher the number, the higher the agent's advantage.
     */
    int evaluateBoard() {
        int evaluation = 0;

        //region agent eval

        // Evaluate agent pawn positions
        for (Square pawn : getWhitePawns()) {
            evaluation += (pawn.getEvaluationValue() + pawn.getPosY() - 1 + chessProject.getPawnMoves(pawn.getPosX(), pawn.getPosY(), pawn.getName()).size());

            for (Move move : chessProject.getPawnMoves(pawn.getPosX(), pawn.getPosY(), pawn.getName())) {
                if (move.getLanding().getName().contains())
            }
        }

        // Evaluate agent rook positions
        for (Square rook : getWhiteRooks()) {
            evaluation += (rook.getEvaluationValue() + chessProject.getRookMoves(rook.getPosX(), rook.getPosY(), rook.getName()).size());
        }

        // Evaluate agent knight positions
        for (Square knight : getWhiteKnights()) {
            evaluation += (knight.getEvaluationValue() + chessProject.getKnightMoves(knight.getPosX(), knight.getPosY(), knight.getName()).size());
        }

        // Evaluate agent bishop positions
        for (Square bishop : getWhiteBishops()) {
            evaluation += (bishop.getEvaluationValue() + chessProject.getBishopMoves(bishop.getPosX(), bishop.getPosY(), bishop.getName()).size());
        }

        // Evaluate agent queen position
        Square whiteQueen = getWhiteQueen();
        if (whiteQueen != null)
            evaluation += (whiteQueen.getEvaluationValue() + chessProject.getQueenMoves(whiteQueen.getPosX(), whiteQueen.getPosY(), whiteQueen.getName()).size());

        // Evaluate agent king position
        Square whiteKing = getWhiteKing();
        if (whiteKing != null)
            evaluation += (whiteKing.getEvaluationValue() - chessProject.getKingMoves(whiteKing.getPosX(), whiteKing.getPosY(), whiteKing.getName()).size());

        //endregion

        //region player eval

        // Evaluate player pawn positions
        for (Square pawn : getBlackPawns()) {
            evaluation -= (pawn.getEvaluationValue() + Math.abs(pawn.getPosY() - 6) + chessProject.getPawnMoves(pawn.getPosX(), pawn.getPosY(), pawn.getName()).size());
        }

        // Evaluate player rook positions
        for (Square rook : getBlackRooks()) {
            evaluation -= (rook.getEvaluationValue() + chessProject.getRookMoves(rook.getPosX(), rook.getPosY(), rook.getName()).size());
        }

        // Evaluate player knight positions
        for (Square knight : getBlackKnights()) {
            evaluation -= (knight.getEvaluationValue() + chessProject.getKnightMoves(knight.getPosX(), knight.getPosY(), knight.getName()).size());
        }

        // Evaluate player bishop positions
        for (Square bishop : getBlackBishops()) {
            evaluation -= (bishop.getEvaluationValue() + chessProject.getBishopMoves(bishop.getPosX(), bishop.getPosY(), bishop.getName()).size());
        }

        // Evaluate player queen position
        Square blackQueen = getBlackQueen();
        if (blackQueen != null)
            evaluation -= (blackQueen.getEvaluationValue() + chessProject.getQueenMoves(blackQueen.getPosX(), blackQueen.getPosY(), blackQueen.getName()).size());

        // Evaluate player king position
        Square blackKing = getBlackKing();
        if (blackKing != null)
            evaluation -= (blackKing.getEvaluationValue() - chessProject.getKingMoves(blackKing.getPosX(), blackKing.getPosY(), blackKing.getName()).size());

        //endregion

        return evaluation;
    }

    //region Piece Finders

    //region Pawns
    /**
     * Returns all of the white pawn squares
     */
    private Stack<Square> getWhitePawns() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhitePawn")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    /**
     * Returns all of the black pawn squares
     */
    private Stack<Square> getBlackPawns() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackPawn")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    //endregion

    //region Rooks
    /**
     * Returns all of the white rook squares
     */
    private Stack<Square> getWhiteRooks() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhiteRook")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    /**
     * Returns all of the black rook squares
     */
    private Stack<Square> getBlackRooks() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackRook")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }
    //endregion

    //region Knights
    /**
     * Returns all of the white knight squares
     */
    private Stack<Square> getWhiteKnights() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhiteKnight")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    /**
     * Returns all of the black knight squares
     */
    private Stack<Square> getBlackKnights() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackKnight")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }
    //endregion

    //region Bishops
    /**
     * Returns all of the black knight squares
     */
    private Stack<Square> getWhiteBishops() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhiteBishop")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    /**
     * Returns all of the white bishop squares
     */
    private Stack<Square> getBlackBishops() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackBishop")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }
    //endregion

    //region Queens
    /**
     * Returns the white queen
     */
    private Square getWhiteQueen() {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhiteQueen")) {
                    return chessSquare[columns][rows];
                }
            }
        }

        return null;
    }

    /**
     * Returns the black queen
     */
    private Square getBlackQueen() {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackQueen")) {
                    return chessSquare[columns][rows];
                }
            }
        }

        return null;
    }
    //endregion

    //region Kings
    /**
     * Returns the white king
     */
    private Square getWhiteKing() {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("WhiteKing")) {
                    return chessSquare[columns][rows];
                }
            }
        }

        return null;
    }

    /**
     * Returns the black king
     */
    private Square getBlackKing() {
        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("BlackKing")) {
                    return chessSquare[columns][rows];
                }
            }
        }

        return null;
    }
    //endregion

    //endregion
}
