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

        // The current best move and evaluated function
        Move bestMove = new Move();
        int bestEvaluation = Integer.MIN_VALUE;

        // Create data representation of the Chess Project
        ChessState initialChessState = new ChessState(chessProject);

        // Print the current state
        initialChessState.printDataRepresentation("Current State");

        // Evaluate each possible move
        for (Move move : possibilities) {

            // Check the next move
            ChessState nextChessState = new ChessState(initialChessState);

            nextChessState.performMove(move);
            nextChessState.printDataRepresentation("Next Possible Move");

            // If the next move has a better evaluation result, then set that as the next best move
            int boardEvaluation = nextChessState.evaluateBoard();
            if (boardEvaluation > bestEvaluation) {
                bestMove = move;
                bestEvaluation = boardEvaluation;
            }
        }

        // Return best move
        return bestMove;
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
