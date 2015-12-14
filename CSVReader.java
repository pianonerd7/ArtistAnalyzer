import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CSVReader {

 HashMap<String, HashSet<Node>> artistIndex = new HashMap<String, HashSet<Node>>();
 
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
   printMap();
   System.out.println("\n\n\n\n ******* \n\n\n\n");
   filterMap(2);
   System.out.println("\n\n\n\n ******* \n\n\n\n");
   printMap();
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
  for (Map.Entry<String, HashSet<Node>> entry : artistIndex.entrySet()) {
    if (entry.getValue().size() < minimum) {
    //System.out.println(entry.getKey());
    System.out.println(artistIndex.remove(entry.getKey()));
   }
  }
 }
 
 private void printMap() {
  for (Map.Entry<String, HashSet<Node>> entry : artistIndex.entrySet()) {
   System.out.println(entry.getKey() + ": " + entry.getValue().toString());
  }
 }
 
 public static void main (String[] args) throws IOException {
  CSVReader reader = new CSVReader();
  
  reader.scanner("SmallData.csv");
 }
}
