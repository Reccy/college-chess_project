import java.util.*;

class AIAgent {
    private Random rand = new Random();

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
     * the best outcome in the next move. (The worst outcome for the player)
     */
    Move nextBestMove(Stack<Move> possibilities, ChessState chessState) {

        // The current best move and evaluated function
        ArrayList<Move> bestMove = new ArrayList<>();
        int bestEvaluation = Integer.MIN_VALUE;

        // Create data representation of the Chess Project
        ChessState initialChessState = new ChessState(chessState);

        // Print the current state
        initialChessState.printDataRepresentation("Current State");

        // Evaluate each possible move
        for (Move move : possibilities) {

            // Check the next move
            ChessState nextChessState = new ChessState(initialChessState);

            nextChessState.performMove(move);
            move.setEndState(nextChessState);
            nextChessState.printDataRepresentation("Next Possible Move");

            // If the next move has a better evaluation result, then set that as the next best move
            int boardEvaluation = nextChessState.evaluateBoard();
            if (boardEvaluation == bestEvaluation) {
                bestMove.add(move);
            } else if (boardEvaluation > bestEvaluation) {
                bestMove.clear();
                bestMove.add(move);
                bestEvaluation = boardEvaluation;
            }
        }

        // Return random best move
        return bestMove.get(rand.nextInt(Math.max(bestMove.size(), 1)));
    }

    /**
     * The method nextWorstMove evaluates the board and chooses the move that will result in
     * the worst outcome in the next move. (The best outcome for the player)
     */
    Move nextWorstMove(Stack<Move> possibilities, ChessState chessState) {

        // The current best move and evaluated function
        ArrayList<Move> worstMove = new ArrayList<>();
        int worstEvaluation = Integer.MAX_VALUE;

        // Create data representation of the Chess Project
        ChessState initialChessState = new ChessState(chessState);

        // Print the current state
        initialChessState.printDataRepresentation("Current State");

        // Evaluate each possible move
        for (Move move : possibilities) {

            // Check the next move
            ChessState nextChessState = new ChessState(initialChessState);

            nextChessState.performMove(move);
            move.setEndState(nextChessState);
            nextChessState.printDataRepresentation("Next Possible Move");

            // If the next move has a worse evaluation result, then set that as the next best move
            int boardEvaluation = nextChessState.evaluateBoard();
            if (boardEvaluation == worstEvaluation) {
                worstMove.add(move);
            } else if (boardEvaluation < worstEvaluation) {
                worstMove.clear();
                worstMove.add(move);
                worstEvaluation = boardEvaluation;
            }
        }

        // Return random worst move
        return worstMove.get(rand.nextInt(Math.max(worstMove.size(), 1)));
    }

    /**
     * MinMax to look ahead two levels
     */
    Move twoLevelsDeep(Stack<Move> possibilities, ChessState chessState) {
        int bestMoveEval = Integer.MIN_VALUE;
        Move bestMove = new Move();

        for (Move move : possibilities) {
            Stack<Move> in = new Stack<>();
            in.push(move);
            ChessState newState = new ChessState(chessState);
            int moveEval = negamax(in, newState, 2, 1, true);

            if (bestMoveEval < moveEval) {
                bestMoveEval = moveEval;
                bestMove = move;
            }
        }

        return bestMove;
    }

    /**
     * Negamax algorithm to return the next best possible move
     */
    int negamax(Stack<Move> possibilities, ChessState chessState, int maxDepth, int currentDepth, boolean isAgent) {
        if(currentDepth == maxDepth) {
            return chessState.evaluateBoard();
        }

        int max = Integer.MIN_VALUE;

        for (Move move : possibilities) {
            ChessState newState = new ChessState(chessState);
            newState.performMove(move);
            move.setEndState(newState);
            int evaluation = isAgent ? negamax(chessState.getBlackMoves(), newState, maxDepth, currentDepth + 1, false) : negamax(chessState.getWhiteMoves(), newState, maxDepth, currentDepth + 1, true);

            if (evaluation > max) {
                max = evaluation;
            }
        }

        return max;
    }
}
