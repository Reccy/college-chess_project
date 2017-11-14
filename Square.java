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

  void print() {
      System.out.println("pieceName: " + pieceName + ", x: " + posX + ", y: " + posY);
  }
}
