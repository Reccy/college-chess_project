class Square{
 private int posX;
 private int posY;
 private String pieceName;

  Square(int x, int y, String name){
    posX = x;
    posY = y;
    pieceName = name;
  }

  public Square(int x, int y){
    posX = x;
    posY = y;
    pieceName = "";
  }

  int getPosX(){
    return posX;
  }

  int getPosY(){
    return posY;
  }

  String getName(){
      return pieceName;
  }

  int getEvaluationValue() {

      if (pieceName.contains("Pawn")) {
          return 1;
      } else if (pieceName.contains("Knight")) {
          return 3;
      } else if (pieceName.contains("Rook")) {
          return 5;
      } else if (pieceName.contains("Bishop")) {
          return 5;
      } else if (pieceName.contains("Queen")) {
          return 8;
      } else if (pieceName.contains("King")) {
          return 100;
      }

      // Return 0 if null piece
      return 0;
  }

  void print() {
      System.out.println("pieceName: " + pieceName + ", x: " + posX + ", y: " + posY);
  }

    @Override
    public String toString() {
        return "pieceName: " + pieceName + ", x: " + posX + ", y: " + posY;
    }
}
