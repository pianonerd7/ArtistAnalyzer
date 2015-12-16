import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class AnalyzeData {

 private String fileName;
 private int minOccurance;
 private HashMap<String, HashSet<CoordinateNode>> artistIndex;
 private ArrayList<ArtistNode> qualifiedPairs;

 public AnalyzeData(String fileName, int minOccurance) {
  this.fileName = fileName;
  this.minOccurance = minOccurance;
  qualifiedPairs = new ArrayList<ArtistNode>();
 }

 public void analyzeData() {
  try {
   artistIndex = CSVUtility.scanner(fileName, minOccurance);
  } catch (Exception e) {
   System.out.println(e.getMessage());
  }

  filterMap(minOccurance);
  //printMap(artistIndex);
  pairArtists(minOccurance);
  CSVUtility.writer(qualifiedPairs);
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
   for (int indexj = indexi + 1; indexj < keys.length; indexj++) {

    HashSet<CoordinateNode> temp = artistIndex.get(keys[indexj]);
    temp.retainAll(artistIndex.get(keys[indexj]));

    if (temp.size() > minOccurance) {
     qualifiedPairs.add(new ArtistNode(keys[indexi], keys[indexj]));
    }
   }
  }
  System.out.println("pair artists");
  // printPairs(qualifiedPairs);
 }

 private void printMap(HashMap<String, HashSet<CoordinateNode>> map) {
  for (Map.Entry<String, HashSet<CoordinateNode>> entry : map.entrySet()) {
   System.out.println(entry.getKey() + ": " + entry.getValue().toString());
  }
 }

 private void printPairs(ArrayList<ArtistNode> pairs) {
  System.out.println(pairs.size());
  for (ArtistNode pair : pairs) {
   System.out.println(pair);
  }
 }

 public static void main(String[] args) throws IOException {
  AnalyzeData ad = new AnalyzeData("LargeData.csv", 50);
  ad.analyzeData();
 }
}
