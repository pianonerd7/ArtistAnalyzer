import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CSVReader {

 HashMap<String, HashSet<Node>> artistIndex = new HashMap<String, HashSet<Node>>();
 
 public void scanner(String fileName, int minOccurance) throws IOException {
  
  BufferedReader reader = new BufferedReader(new FileReader(fileName));
  int curRow = 1;
  try {
   String curLine = reader.readLine();
   while (curLine != null) {
    populateMap(curLine, curRow);
    curLine = reader.readLine();
    curRow++;
   }
   filterMap(minOccurance);
   printMap(artistIndex);
  }
  catch (Exception e) {
   System.out.println(e.getMessage());
  }
  finally {
   reader.close();
  }
 }
 
 private void populateMap(String curLine, int curRow) {
  String[] artists = curLine.split(",");
  
  int column = 1;
  for (String artist : artists) {
   HashSet<Node> temp = artistIndex.get(artist);
   
   if (temp != null) {
    temp.add(new Node(curRow, column));
   }
   else {
    HashSet<Node> newSet = new HashSet<Node>();
    newSet.add(new Node(curRow, column));
    artistIndex.put(artist, newSet);
   }
   column++;
  }
 }
 
 private void filterMap(int minimum) {
  
  Stirng[] keys = artistIndex.keySet();
  HashMap<String, HashSet<Node>> newIndex = new HashMap<String, HashSet<Node>>();
  
  for (String artist : keys) {
   if (artistIndex.get(artist).size() >= minimum) {
    newIndex.put(artist, artistIndex.get(artist));
   }
  }
  
  artistIndex = newIndex;
 }
 
 private void pairArtists() {
	 
	 String[] keys = artistIndex.keySet().toArray(new String[0]);
	 
	 for (int indexi = 0; indexi < keys.length; indexi++) {
		 for (int indexj = indexi; indexj < keys.length; indexj++) {
			 
		 }
	 }
	 
 }
 
 private void printMap(HashMap<String, HashSet<Node>> map) {
  for (Map.Entry<String, HashSet<Node>> entry : map.entrySet()) {
   System.out.println(entry.getKey() + ": " + entry.getValue().toString());
  }
 }
 
 public static void main (String[] args) throws IOException {
  CSVReader reader = new CSVReader();
  
  reader.scanner("SmallData.csv", 3);
 }
}
