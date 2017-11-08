class Move{
  private Square start;
  private Square landing;

  Move(Square x, Square y){
    start = x;
    landing = y;
  }

  Move(){
    
  }

  Square getStart(){
    return start;
  }

  Square getLanding(){
    return landing;
  }
}
