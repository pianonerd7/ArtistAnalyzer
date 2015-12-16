import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CSVReader {

 private HashMap<String, HashSet<CoordinateNode>> artistIndex = new HashMap<String, HashSet<CoordinateNode>>();
 private ArrayList<ArtistNode> qualifiedPairs = new ArrayList<ArtistNode>();
 
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
   //printMap(artistIndex);
   pairArtists(minOccurance);
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
   HashSet<CoordinateNode> temp = artistIndex.get(artist);
   
   if (temp != null) {
    temp.add(new CoordinateNode(curRow, column));
   }
   else {
    HashSet<CoordinateNode> newSet = new HashSet<CoordinateNode>();
    newSet.add(new CoordinateNode(curRow, column));
    artistIndex.put(artist, newSet);
   }
   column++;
  }
 }
 
 private void filterMap(int minimum) {
  
  String[] keys = artistIndex.keySet().toArray(new String[0]);
  
  for (String artist : keys) {
   if (artistIndex.get(artist).size() < minimum) {
    artistIndex.remove(artist);
   }
  }
  
 }
 
 private void pairArtists(int minOccurance) {
  
  String[] keys = artistIndex.keySet().toArray(new String[0]);
  
  for (int indexi = 0; indexi < keys.length; indexi++) {
   for (int indexj = indexi+1; indexj < keys.length; indexj++) {
    
    HashSet<CoordinateNode> temp = artistIndex.get(keys[indexj]);
    temp.retainAll(artistIndex.get(keys[indexj]));
    
    if (temp.size() > minOccurance) {
     qualifiedPairs.add(new ArtistNode(keys[indexi], keys[indexj]));
    }
   }
  }
  System.out.println("pair artists");
  printPairs(qualifiedPairs);
 }
 
 private void printMap(HashMap<String, HashSet<CoordinateNode>> map) {
  for (Map.Entry<String, HashSet<CoordinateNode>> entry : map.entrySet()) {
   System.out.println(entry.getKey() + ": " + entry.getValue().toString());
  }
 }
 
 private void printPairs(ArrayList<ArtistNode> pairs) {
  System.out.println(pairs.size());
  for (ArtistNode pair : pairs)  {
   System.out.println(pair);
  }
 }
 
 public static void main (String[] args) throws IOException {
  CSVReader reader = new CSVReader();
  
  reader.scanner("SmallData.csv", 2);
 }
}
