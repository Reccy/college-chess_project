class Move{
  private Square start;
  private Square landing;
  private ChessState endState;

  Move(Square x, Square y){
    start = x;
    landing = y;
  }

  Move(){
    
  }

  @Override
  public String toString() {
    return "MOVE FROM: [" + start.toString() + "], TO: [" + landing.toString() + "]";
  }

  Square getStart(){
    return start;
  }

  Square getLanding(){
    return landing;
  }

  public void setEndState(ChessState endState) {
    this.endState = endState;
  }

  public ChessState getEndState() {
    return endState;
  }
}
