package gameengine;

import java.util.*;
import java.io.*;

public class FileManager {

		private ArrayList<HighScores> scores;
		
		private static final String FILE = "highscores.dat";
		
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		public FileManager(){
			scores = new ArrayList<HighScores>();
		}
		
		public ArrayList<HighScores> getScores(){
			loadScoreFile();
			sort();
			return scores;
		}
		
		private void sort(){
			ScoreCompare comparator = new ScoreCompare();
			Collections.sort(scores, comparator);
		}
		
		@SuppressWarnings("unchecked")
		private void loadScoreFile(){
		        try {
		        	File file = new File(FILE);
		        	if (!file.exists()){
		        		file.createNewFile();
		        	}
		        	else{
			            inputStream = new ObjectInputStream(new FileInputStream(file));
			            scores = (ArrayList<HighScores>) inputStream.readObject();
		        	}
		        } catch (FileNotFoundException e) {
		            System.out.println("[Laad] FNF Error: " + e.getMessage());
		        } catch (IOException e) {
		            System.out.println("[Laad] IO Error: " + e.getMessage());
		        } catch (ClassNotFoundException e) {
		            System.out.println("[Laad] CNF Error: " + e.getMessage());
		        } finally {
		            try {
		                if (outputStream != null) {
		                    outputStream.flush();
		                    outputStream.close();
		                }
		            } catch (IOException e) {
		                System.out.println("[Laad] IO Error: " + e.getMessage());
		            }
		        }
		}
		
		public void updateScoreFile() {
	        try {
	            outputStream = new ObjectOutputStream(new FileOutputStream(FILE));
	            outputStream.writeObject(scores);
	        } catch (FileNotFoundException e) {
	            System.out.println("[Update] FNF Error: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("[Update] IO Error: " + e.getMessage());
	        } finally {
	            try {
	                if (outputStream != null) {
	                    outputStream.flush();
	                    outputStream.close();
	                }
	            } catch (IOException e) {
	                System.out.println("[Update] Error: " + e.getMessage());
	            }
	        }
	}
		
		
		public void addScore(String name, int score){
			loadScoreFile();
			scores.add(new HighScores(name, score));
			updateScoreFile();
		}
		
		
		public String getHighscoreString() {
	        String highscoreString = "";
	        int max = 10;

	        ArrayList<HighScores> scores;
	        scores = getScores();

	        int i = 0;
	        int x = scores.size();
	        if (x > max) {
	            x = max;
	        }
	        while (i < x) {
	            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
	            i++;
	        }
	        return highscoreString;
	}
		
}
