import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class CSVReader {

	private static HashMap<String, HashSet<CoordinateNode>> artistIndex = new HashMap<String, HashSet<CoordinateNode>>();

	public static HashMap<String, HashSet<CoordinateNode>> scanner(String fileName, int minOccurance)
			throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		int curRow = 1;
		try {
			String curLine = reader.readLine();
			while (curLine != null) {
				populateMap(curLine, curRow);
				curLine = reader.readLine();
				curRow++;
			}
			// filterMap(minOccurance);
			// printMap(artistIndex);
			// pairArtists(minOccurance);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			reader.close();
		}
		return artistIndex;
	}

	private static void populateMap(String curLine, int curRow) {
		String[] artists = curLine.split(",");

		int column = 1;
		for (String artist : artists) {
			HashSet<CoordinateNode> temp = artistIndex.get(artist);

			if (temp != null) {
				temp.add(new CoordinateNode(curRow, column));
			} else {
				HashSet<CoordinateNode> newSet = new HashSet<CoordinateNode>();
				newSet.add(new CoordinateNode(curRow, column));
				artistIndex.put(artist, newSet);
			}
			column++;
		}
	}
}