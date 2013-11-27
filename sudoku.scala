package sudoku
import scala.collection.mutable

object sudoku {
	def main(args: Array[String]){
		val board = new Board()
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
	        
    
	  
	  
	  def make_units() : mutable.Map[String, List[String]] = {
	    var units = mutable.Map.empty[String, List[String]]
	    for(s <- squares){
	    	for(u <- unitlist){
    	  		if(u.contains(s)){
        			units += (s -> u)
        			println(s)
        			println(units(s))
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
	}
	
	class Cell(solved: Int = 0){
	  
	}
	
}