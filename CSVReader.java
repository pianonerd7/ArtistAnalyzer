import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CSVReader {

 HashMap<String, HashSet<Node>> artistIndex = new HashMap<String, HashSet<Node>>();
 
 public HashMap<String, HashSet<Node>> getMap() {
   return artistIndex;
 }
 
 public void scanner(String fileName) throws IOException {
  
  BufferedReader reader = new BufferedReader(new FileReader(fileName));
  int curRow = 1;
  try {
   String curLine = reader.readLine();
   while (curLine != null) {
    populateMap(curLine, curRow);
    curLine = reader.readLine();
    curRow++;
   }
   printMap(artistIndex);
   System.out.println("\n\n\n\n ******* \n\n\n\n");
   filterMap(3);
   System.out.println("\n\n\n\n ******* \n\n\n\n");
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
  
  Set<String> keys = artistIndex.keySet();
  HashMap<String, HashSet<Node>> newIndex = new HashMap<String, HashSet<Node>>();
  
  for (String artist : keys) {
   if (artistIndex.get(artist).size() >= minimum) {
    newIndex.put(artist, artistIndex.get(artist));
   }
  }
  
  System.out.println("New Index Map");
  printMap(newIndex);
  artistIndex = newIndex;
  
  /*for (Map.Entry<String, HashSet<Node>> entry : artistIndex.entrySet()) {
    if (entry.getValue().size() < minimum) {
    //System.out.println(entry.getKey());
    artistIndex.remove(entry.getKey());
   }
  }*/
 }
 
 private void printMap(HashMap<String, HashSet<Node>> map) {
  for (Map.Entry<String, HashSet<Node>> entry : map.entrySet()) {
   System.out.println(entry.getKey() + ": " + entry.getValue().toString());
  }
 }
 
 public static void main (String[] args) throws IOException {
  CSVReader reader = new CSVReader();
  
  reader.scanner("SmallData.csv");
 }
}
