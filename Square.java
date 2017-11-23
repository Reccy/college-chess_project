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
          return 5;
      } else if (pieceName.contains("Knight")) {
          return 10;
      } else if (pieceName.contains("Rook")) {
          return 15;
      } else if (pieceName.contains("Bishop")) {
          return 15;
      } else if (pieceName.contains("Queen")) {
          return 30;
      } else if (pieceName.contains("King")) {
          return 1000;
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
