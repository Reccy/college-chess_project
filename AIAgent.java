import java.util.*;

class AIAgent {
    private Random rand;

    AIAgent() {
        rand = new Random();
    }

    /**
     * The method randomMove takes as input a stack of potential moves that the AI agent
     * can make. The agent uses a random number generator to randomly select a move from
     * the inputted Stack and returns this to the calling agent.
     */
    Move randomMove(Stack<Move> possibilities) {
        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : " + moveID);
        for (int i = 1; i < (possibilities.size() - (moveID)); i++) {
            possibilities.pop();
        }

        return possibilities.pop();
    }

    /**
     * The method nextBestMove evaluates the board and chooses the move that will result in
     * the best outcome in the next move. However, this strategy will not take into account
     * the player's move. This has the disadvantage that the AI can easily fall into traps
     * set by the player.
     */
    Move nextBestMove(Stack<Move> possibilities, ChessProject chessProject) {

        // Create data representation of the Chess Project
        ChessState chessState = new ChessState(chessProject);

        // Print the current state
        System.out.println("Current State:");
        chessState.printDataRepresentation();

        for (Move move : possibilities) {

            // Will aggressively take a piece if it can
            if (chessProject.piecePresent(move.getLanding()) && chessProject.checkBlackOpponent(move.getLanding().getPosX(), move.getLanding().getPosY())) {
                System.out.println("Agent selected attack move: " + move.getLanding());
                return move;
            }
        }

        // Return random move as a fail safe
        return randomMove(possibilities);
    }

    /**
     * The method twoLevelsDeep evaluates the board for the next teo move by using the nextBestMove
     * method. This checks the AI's next move and the Player's next best move using minmax algorithm.
     */
    Move twoLevelsDeep(Stack<Move> possibilities, ChessProject chessProject) {
        return nextBestMove(possibilities, chessProject);
    }

    /**
     * Evaluates the advantage of the AI agent depending on the white and black piece values.
     * The higher the value, the better the AI advantage
     * @param chessProject Instance of the Chess Project
     * @return The AI Agent's advantage
     */
    float evaluateChessBoard(ChessProject chessProject) {
        return rand.nextInt(100);
    }
}
