class Move{
  private Square start;
  private Square landing;

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
}
