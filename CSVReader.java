import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CSVReader {

	HashMap<String, HashSet<Integer>> artistIndex = new HashMap<String, HashSet<Integer>>();
	
	public void Scanner(String fileName) throws IOException {
		
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
		
		for (String artist : artists) {
			HashSet<Integer> temp = artistIndex.get(artist);
			
			if (temp != null) {
				temp.add(curRow);
			}
			else {
				HashSet<Integer> newSet = new HashSet<Integer>();
				newSet.add(curRow);
				artistIndex.put(artist, newSet);
			}
		}
	}
	
	private void printMap() {
		for (Map.Entry<String, HashSet<Integer>> entry : artistIndex.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue().toString());
		}
	}
}
