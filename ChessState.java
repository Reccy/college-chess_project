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
            Stack<Move> availableMoves = getPawnMoves(pawn.getPosX(), pawn.getPosY(), pawn.getName());
            evaluation += (pawn.getEvaluationValue() + pawn.getPosY() - 1 + availableMoves.size() + getAttackAdvantage(availableMoves, pawn.getName())) * getUnderAttackMultiplierWhitePieces(pawn);
        }

        // Evaluate agent rook positions
        for (Square rook : getWhiteRooks()) {
            Stack<Move> availableMoves = getRookMoves(rook.getPosX(), rook.getPosY(), rook.getName());
            evaluation += (rook.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, rook.getName())) * getUnderAttackMultiplierWhitePieces(rook);
        }

        // Evaluate agent knight positions
        for (Square knight : getWhiteKnights()) {
            Stack<Move> availableMoves = getKnightMoves(knight.getPosX(), knight.getPosY(), knight.getName());
            evaluation += (knight.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, knight.getName())) * getUnderAttackMultiplierWhitePieces(knight);
        }

        // Evaluate agent bishop positions
        for (Square bishop : getWhiteBishops()) {
            Stack<Move> availableMoves = getBishopMoves(bishop.getPosX(), bishop.getPosY(), bishop.getName());
            evaluation += (bishop.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, bishop.getName())) * getUnderAttackMultiplierWhitePieces(bishop);
        }

        // Evaluate agent queen position
        Square whiteQueen = getWhiteQueen();
        if (whiteQueen != null) {
            Stack<Move> availableMoves = getQueenMoves(whiteQueen.getPosX(), whiteQueen.getPosY(), whiteQueen.getName());
            evaluation += (whiteQueen.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, whiteQueen.getName()))  * getUnderAttackMultiplierWhitePieces(whiteQueen);
        }

        // Evaluate agent king position (Subtract available moves because the king is a defensive piece)
        Square whiteKing = getWhiteKing();
        if (whiteKing != null)
        {
            Stack<Move> availableMoves = getKingMoves(whiteKing.getPosX(), whiteKing.getPosY(), whiteKing.getName());
            evaluation += (whiteKing.getEvaluationValue() - availableMoves.size() - getAttackAdvantage(availableMoves, whiteKing.getName()))  * getUnderAttackMultiplierWhitePieces(whiteKing);
        }

        //endregion

        //region player eval

        // Evaluate player pawn positions
        for (Square pawn : getBlackPawns()) {
            Stack<Move> availableMoves = getPawnMoves(pawn.getPosX(), pawn.getPosY(), pawn.getName());
            evaluation -= (pawn.getEvaluationValue() + Math.abs(pawn.getPosY() - 6) + availableMoves.size() + getAttackAdvantage(availableMoves, pawn.getName()))  * getUnderAttackMultiplierBlackPieces(pawn);
        }

        // Evaluate player rook positions
        for (Square rook : getBlackRooks()) {
            Stack<Move> availableMoves = getRookMoves(rook.getPosX(), rook.getPosY(), rook.getName());
            evaluation -= (rook.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, rook.getName())) * getUnderAttackMultiplierBlackPieces(rook);
        }

        // Evaluate player knight positions
        for (Square knight : getBlackKnights()) {
            Stack<Move> availableMoves = getKnightMoves(knight.getPosX(), knight.getPosY(), knight.getName());
            evaluation -= (knight.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, knight.getName())) * getUnderAttackMultiplierBlackPieces(knight);
        }

        // Evaluate player bishop positions
        for (Square bishop : getBlackBishops()) {
            Stack<Move> availableMoves = getBishopMoves(bishop.getPosX(), bishop.getPosY(), bishop.getName());
            evaluation -= (bishop.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, bishop.getName())) * getUnderAttackMultiplierBlackPieces(bishop);
        }

        // Evaluate player queen position
        Square blackQueen = getBlackQueen();
        if (blackQueen != null)
        {
            Stack<Move> availableMoves = getQueenMoves(blackQueen.getPosX(), blackQueen.getPosY(), blackQueen.getName());
            evaluation -= (blackQueen.getEvaluationValue() + availableMoves.size() + getAttackAdvantage(availableMoves, blackQueen.getName())) * getUnderAttackMultiplierBlackPieces(blackQueen);
        }

        // Evaluate player king position (Subtract available moves because the king is a defensive piece)
        Square blackKing = getBlackKing();
        if (blackKing != null)
        {
            Stack<Move> availableMoves = getKingMoves(blackKing.getPosX(), blackKing.getPosY(), blackKing.getName());
            evaluation -= (blackKing.getEvaluationValue() - availableMoves.size() - getAttackAdvantage(availableMoves, blackKing.getName())) * getUnderAttackMultiplierBlackPieces(blackKing);
        }

        //endregion

        return evaluation;
    }

    /**
     * Gets the attack advantage of the pawn piece
     */
    private int getAttackAdvantage(Stack<Move> availableMoves, String pieceName) {
        String thisPieceTeam = getTeamName(pieceName);
        int attackAdvantage = 0;

        // Update evaluation for each move
        for (Move move : availableMoves) {
            int pieceLandingX = move.getLanding().getPosX();
            int pieceLandingY = move.getLanding().getPosY();
            String opponentPieceName = thisPieceTeam.equals("Black") ? "White" : "Black" ;

            if (chessSquare[pieceLandingX][pieceLandingY].getName().contains(opponentPieceName)) {
                attackAdvantage += chessSquare[pieceLandingX][pieceLandingY].getEvaluationValue() * 0.2;
            }
        }

        // Return evaluation
        return attackAdvantage;
    }

    /**
     * Returns 1 if the piece is under attack.
     * Returns 0 if the piece is not under attack.
     */
    private int getUnderAttackMultiplierBlackPieces(Square piece) {
        Stack<Move> opponentMoves = getWhiteMoves();
        int agentPosX = piece.getPosX();
        int agentPosY = piece.getPosY();

        for (Move opponentMove : opponentMoves) {
            int opponentLandingX = opponentMove.getLanding().getPosX();
            int opponentLandingY = opponentMove.getLanding().getPosY();

            if (opponentLandingX == agentPosX && opponentLandingY == agentPosY) {
                System.out.println("BLACK PIECE UNDER ATTACK: " + agentPosX + ", " + agentPosY + " => " + piece.getName());
                return 1;
            }
        }

        return 0;
    }

    /**
     * Returns 1 if the piece is under attack.
     * Returns 0 if the piece is not under attack.
     */
    private int getUnderAttackMultiplierWhitePieces(Square piece) {
        Stack<Move> opponentMoves = getBlackMoves();
        int agentPosX = piece.getPosX();
        int agentPosY = piece.getPosY();

        for (Move opponentMove : opponentMoves) {
            int opponentLandingX = opponentMove.getLanding().getPosX();
            int opponentLandingY = opponentMove.getLanding().getPosY();

            if (opponentLandingX == agentPosX && opponentLandingY == agentPosY) {
                System.out.println("WHITE PIECE UNDER ATTACK: " + agentPosX + ", " + agentPosY + " => " + piece.getName());
                return 0;
            }
        }

        return 1;
    }

    //region Move Finders

    /**
     * Returns a stack of all possible black moves
     */
    private Stack<Move> getBlackMoves() {
        Stack<Square> blackSquares = getBlackSquares();
        Stack<Move> blackMoves = new Stack<>();

        for (Square blackSquare : blackSquares) {
            int squarePosX = blackSquare.getPosX();
            int squarePosY = blackSquare.getPosY();
            String squareName = blackSquare.getName();

            if (blackSquare.getName().contains("BlackPawn")) {
                blackMoves.addAll(getPawnMoves(squarePosX, squarePosY, squareName));

            } else if (blackSquare.getName().contains("BlackKnight")) {
                blackMoves.addAll(getKnightMoves(squarePosX, squarePosY, squareName));

            } else if (blackSquare.getName().contains("BlackRook")) {
                blackMoves.addAll(getRookMoves(squarePosX, squarePosY, squareName));

            } else if (blackSquare.getName().contains("BlackBishop")) {
                blackMoves.addAll(getBishopMoves(squarePosX, squarePosY, squareName));

            } else if (blackSquare.getName().contains("BlackQueen")) {
                blackMoves.addAll(getQueenMoves(squarePosX, squarePosY, squareName));

            } else if (blackSquare.getName().contains("BlackKing")) {
                blackMoves.addAll(getKingMoves(squarePosX, squarePosY, squareName));

            }
        }

        return blackMoves;
    }

    /**
     * Returns a stack of all possible white moves
     */
    private Stack<Move> getWhiteMoves() {
        Stack<Square> whiteSquares = getWhiteSquares();
        Stack<Move> whiteMoves = new Stack<>();

        for (Square whiteSquare : whiteSquares) {
            int squarePosX = whiteSquare.getPosX();
            int squarePosY = whiteSquare.getPosY();
            String squareName = whiteSquare.getName();

            if (whiteSquare.getName().contains("WhitePawn")) {
                whiteMoves.addAll(getPawnMoves(squarePosX, squarePosY, squareName));

            } else if (whiteSquare.getName().contains("WhiteKnight")) {
                whiteMoves.addAll(getKnightMoves(squarePosX, squarePosY, squareName));

            } else if (whiteSquare.getName().contains("WhiteRook")) {
                whiteMoves.addAll(getRookMoves(squarePosX, squarePosY, squareName));

            } else if (whiteSquare.getName().contains("WhiteBishop")) {
                whiteMoves.addAll(getBishopMoves(squarePosX, squarePosY, squareName));

            } else if (whiteSquare.getName().contains("WhiteQueen")) {
                whiteMoves.addAll(getQueenMoves(squarePosX, squarePosY, squareName));

            } else if (whiteSquare.getName().contains("WhiteKing")) {
                whiteMoves.addAll(getKingMoves(squarePosX, squarePosY, squareName));

            }
        }

        return whiteMoves;
    }

    /**
     * Returns all possible pawn moves, changes team depending on the pieceName.
     */
    private Stack<Move> getPawnMoves(int posX, int posY, String pieceName) {
        Stack<Move> possibleMoves = new Stack<>();
        Square startingSquare = new Square(posX, posY, pieceName);
        int forwardMovement = getForwardMovement(pieceName);
        String thisTeamName = getTeamName(pieceName);
        int startingRow = getStartingRow(pieceName);

        if (posX > 0 && !chessSquare[posX - 1][posY + forwardMovement].getName().contains(thisTeamName)) {
            possibleMoves.push(new Move(startingSquare, chessSquare[posX - 1][posY + forwardMovement]));
        }

        if (posX < 7 && !chessSquare[posX + 1][posY + forwardMovement].getName().contains(thisTeamName)) {
            possibleMoves.push(new Move(startingSquare, chessSquare[posX + 1][posY + forwardMovement]));
        }

        if (!chessSquare[posX][posY + forwardMovement].getName().contains(thisTeamName)) {
            possibleMoves.push(new Move(startingSquare, chessSquare[posX][posY + forwardMovement]));

            if (startingSquare.getPosY() == startingRow && !chessSquare[posX][posY + (forwardMovement * 2)].getName().contains(thisTeamName)) {
                possibleMoves.push(new Move(startingSquare, chessSquare[posX][posY + (forwardMovement * 2)]));
            }
        }

        return possibleMoves;
    }

    /**
     * Returns all possible knight moves, changes team depending on the pieceName.
     */
    private Stack<Move> getKnightMoves(int posX, int posY, String pieceName) {
        Stack<Move> allPossibleMoves = new Stack<>();
        Stack<Move> possibleMoves = new Stack<>();
        Square startingSquare = new Square(posX, posY, pieceName);
        String thisTeamName = getTeamName(pieceName);

        // region Setup available moves
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + 1,posY + 2)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + 2,posY + 1)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + 2,posY + -1)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + 1,posY + -2)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + -1,posY + -2)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + -2,posY + -1)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + -2,posY + 1)));
        allPossibleMoves.push(new Move(startingSquare, new Square(posX + -1,posY + 2)));
        // endregion

        for (Move move : allPossibleMoves) {
            if (!(move.getLanding().getPosX() > 7 || move.getLanding().getPosX() < 0 || move.getLanding().getPosY() > 7 || move.getLanding().getPosY() < 0 || move.getLanding().getName().contains(thisTeamName))) {
                possibleMoves.add(move);
            }
        }

        return possibleMoves;
    }

    /**
     * Returns all possible rook moves, changes team depending on the pieceName.
     */
    private Stack<Move> getRookMoves(int posX, int posY, String pieceName) {
        Stack<Move> possibleMoves = new Stack<>();
        Square startingSquare = new Square(posX, posY, pieceName);
        String thisTeamName = getTeamName(pieceName);

        // region Setup available moves
        // Setup available moves (Up)
        for (int y = startingSquare.getPosY() - 1; y >= 0; y--) {
            Square square = chessSquare[startingSquare.getPosX()][y];

            if (!square.getName().contains(thisTeamName)) {
                possibleMoves.push(new Move(startingSquare, square));
            } else {
                break;
            }
        }

        // Setup available moves (Down)
        for (int y = startingSquare.getPosY() + 1; y < 8; y++) {
            Square square = chessSquare[startingSquare.getPosX()][y];

            if (!square.getName().contains(thisTeamName)) {
                possibleMoves.push(new Move(startingSquare, square));
            } else {
                break;
            }
        }

        // Setup available moves (Left)
        for (int x = startingSquare.getPosX() - 1; x >= 0; x--) {
            Square square = chessSquare[x][startingSquare.getPosY()];

            if (!square.getName().contains(thisTeamName)) {
                possibleMoves.push(new Move(startingSquare, square));
            } else {
                break;
            }
        }

        // Setup available moves (Right)
        for (int x = startingSquare.getPosX() + 1; x < 8; x++) {
            Square square = chessSquare[x][startingSquare.getPosY()];

            if (!square.getName().contains(thisTeamName)) {
                possibleMoves.push(new Move(startingSquare, square));
            } else {
                break;
            }
        }
        // endregion

        return possibleMoves;
    }

    private Stack<Move> getBishopMoves(int posX, int posY, String pieceName) {
        Stack<Move> possibleMoves = new Stack<>();
        Square startingSquare = new Square(posX, posY, pieceName);
        String thisTeamName = getTeamName(pieceName);

        // region Setup available moves
        // Setup available moves (BOTTOM RIGHT)
        for (int i = 1; i < 8; i++) {
            if (startingSquare.getPosX() + i >= 8 || startingSquare.getPosY() + i >= 8)
                break;

            Move square = new Move(startingSquare, chessSquare[startingSquare.getPosX() + i][startingSquare.getPosY() + i]);

            if (!square.getLanding().getName().contains(thisTeamName)) {
                possibleMoves.add(square);
            } else {
                break;
            }
        }

        // Setup available moves (TOP RIGHT)
        for (int i = 1; i < 8; i++) {
            if (startingSquare.getPosX() + i >= 8 || startingSquare.getPosY() - i < 0)
                break;

            Move square = new Move(startingSquare, chessSquare[startingSquare.getPosX() + i][startingSquare.getPosY() - i]);

            if (!square.getLanding().getName().contains(thisTeamName)) {
                possibleMoves.add(square);
            } else {
                break;
            }
        }

        // Setup available moves (BOTTOM LEFT)
        for (int i = 1; i < 8; i++) {
            if (startingSquare.getPosX() - i < 0 || startingSquare.getPosY() + i >= 8)
                break;

            Move square = new Move(startingSquare, chessSquare[startingSquare.getPosX() - i][startingSquare.getPosY() + i]);

            if (!square.getLanding().getName().contains(thisTeamName)) {
                possibleMoves.add(square);
            } else {
                break;
            }
        }

        // Setup available moves (TOP LEFT)
        for (int i = 1; i < 8; i++) {
            if (startingSquare.getPosX() - i < 0 || startingSquare.getPosY() - i < 0)
                break;

            Move square = new Move(startingSquare, chessSquare[startingSquare.getPosX() - i][startingSquare.getPosY() - i]);

            if (!square.getLanding().getName().contains(thisTeamName)) {
                possibleMoves.add(square);
            } else {
                break;
            }
        }

        // endregion

        return possibleMoves;
    }

    private Stack<Move> getQueenMoves(int posX, int posY, String pieceName) {
        Stack<Move> possibleMoves = new Stack<>();

        possibleMoves.addAll(getBishopMoves(posX, posY, pieceName));
        possibleMoves.addAll(getRookMoves(posX, posY, pieceName));

        return possibleMoves;
    }

    private Stack<Move> getKingMoves(int posX, int posY, String pieceName) {
        Stack<Move> allPossibleMoves = new Stack<>();
        Stack<Move> possibleMoves = new Stack<>();
        Square startingSquare = new Square(posX, posY, pieceName);
        String thisTeamName = getTeamName(pieceName);

        // region Setup available moves
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + 0,startingSquare.getPosY() + 1)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + 1,startingSquare.getPosY() + 1)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + 1,startingSquare.getPosY() + 0)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + 1,startingSquare.getPosY() + -1)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + 0,startingSquare.getPosY() + -1)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + -1,startingSquare.getPosY() + -1)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + -1,startingSquare.getPosY() + 0)));
        allPossibleMoves.add(new Move(startingSquare, new Square(startingSquare.getPosX() + -1,startingSquare.getPosY() + 1)));
        // endregion

        for (Move move : allPossibleMoves) {
            if (!(move.getLanding().getPosX() > 7 || move.getLanding().getPosX() < 0 || move.getLanding().getPosY() > 7 || move.getLanding().getPosY() < 0 || move.getLanding().getName().contains(thisTeamName))) {
                possibleMoves.add(move);
            }
        }

        return possibleMoves;
    }

    private int getForwardMovement(String piece) {
        return piece.contains("Black") ? -1 : 1;
    }

    private String getTeamName(String piece) {
        return piece.contains("Black") ? "Black" : "White";
    }

    private int getStartingRow(String piece) {
        return piece.contains("Black") ? 6 : 1;
    }

    //endregion

    //region Piece Finders

    //region All Pieces
    private Stack<Square> getBlackSquares() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("Black")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }

    private Stack<Square> getWhiteSquares() {
        Stack<Square> pieces = new Stack<>();

        for (int rows = 0; rows < 8; rows++) {
            for (int columns = 0; columns < 8; columns++) {
                if(chessSquare[columns][rows].getName().contains("White")) {
                    pieces.push(chessSquare[columns][rows]);
                }
            }
        }

        return pieces;
    }
    //endregion

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
