import java.util.*;

public class AIAgent{
  private Random rand;

  AIAgent(){
    rand = new Random();
  }

/**
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/
  Move randomMove(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }

    return (Move)possibilities.pop();
  }

  public Move nextBestMove(Stack possibilities){
    // TODO: Implement twoLevelsDeep

    return new Move();
  }

  public Move twoLevelsDeep(Stack possibilities){
    // TODO: Implement twoLevelsDeep

    return new Move();
  }
}
