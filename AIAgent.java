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

    Move nextBestMove(Stack<Move> possibilities, ChessProject chessProject) {

        for (Move move : possibilities) {
            // Will aggressively take a piece if it can
            if (chessProject.piecePresent(move.getLanding()) && chessProject.checkBlackOpponent(move.getLanding().getPosX(), move.getLanding().getPosY())) {
                System.out.println("Agent selected attack move: " + move.getLanding());
                return move;
            }
        }

        return randomMove(possibilities);
    }

    Move twoLevelsDeep(Stack<Move> possibilities) {
        // TODO: Implement twoLevelsDeep

        return new Move();
    }
}
