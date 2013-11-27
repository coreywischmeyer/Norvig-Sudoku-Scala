package sudoku
import scala.collection.mutable

object sudoku {
	def main(args: Array[String]){
	  val board = new Board()
	  board.parse_grid("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......")
	  board.grid_values("4.....8.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......")
	}
	
	class Board{
	  val digits = "123456789"
	  val rows = "ABCDEFGHI" 
	  val cols = digits
	  val squares = cross(rows, cols)
	  val rowsquares = List("ABC","DEF","GHI")
	  val colsquares = List("123","456","789")
	  val unitlist = List(
	    		for(c <- cols) yield cross(rows, c.toString),
	    		for(r <- rows) yield cross(r.toString, cols),
	    		for{
	    			rs <- rowsquares
	    			cs <- colsquares
	    		} yield cross(rs, cs)
	        ).flatten
	        
	   val units = make_units()
	   val peers = make_peers() 
	   
	  def make_peers() : mutable.Map[String,Set[String]] = {
	    var peers = mutable.Map.empty[String, Set[String]]
	    for(s <- squares){
	      peers += (s -> ((Set[String]() ++ units(s).flatten) - s))
	    }
	    return peers
	  }
	  
	  def make_units() : mutable.Map[String, List[List[String]]] = {
	    var units = mutable.Map.empty[String, List[List[String]]]
	    for(s <- squares){
	      units += (s -> List[List[String]]()) //initialize (s)
	    	for(u <- unitlist){
    	  		if(u.contains(s)){
    	  			units(s) = u :: units(s)
        		}
      		}
	    }
	    return units
	  } 
	      
	  def cross(A: String, B: String): List[String] = {
	    for{
	      a <- A.toList
	      b <- B.toList
	    } yield (a.toString + b.toString)
	  }
	  /**
	   * Converting a grid to a Map of possible values. {square: possible digits},
	   * return "False" if a contradiction is detected.
	   */
	  def parse_grid(grid: String) : Map[String,String] = {
	    
	    val values = (for{
	      s <- squares
	    }yield (s -> digits)).toMap
	    val grid_values_map = grid_values(grid)
	    for(s <- grid_values_map.keys){
	      if(digits.contains(grid_values_map(s))) {
	        assign(values, s, grid_values_map(s).toString)
	      }
	    }
	      return values
	  }
	  
	  def assign(values: Map[String, String], s: String, d: String) : Map[String, String] = {
	    val other_values = values(s).replace(d, "")
	    return values
	  }
	  
	  def eliminate(values: Map[String, String], s: String, d: String) : Map[String, String] = {
	    if(!(values(s).contains(d))){
	      return values
	    }
	    var ret_values = values(s).replace(d, "")
	    if(values(s).length == 0 ){
	      return Map[String,String]()
	    }else if(values(s).length == 1){
	      val d2 = values(s)
	      if(!)
	    }
	  }
	  
	  def grid_values(grid: String) : Map[String, Char]={
	    val chars = (for{
	      s <- squares
	      c <- grid
	      if(digits.contains(c) || ".0".contains(c))
	    }yield(c))
	    (squares zip chars).toMap
	  }
	  
	}
	
	class Cell(solved: Int = 0){
	  
	}
	
}