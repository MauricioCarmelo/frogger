package gameengine;
import java.io.Serializable;

public class HighScores implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	
	public HighScores(String name, int score){
		this.score = score;
		this.name = name;
	}
	
}
