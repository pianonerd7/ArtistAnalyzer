import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CSVUtility {

	private static HashMap<String, HashSet<CoordinateNode>> artistIndex = new HashMap<String, HashSet<CoordinateNode>>();

	public static HashMap<String, HashSet<CoordinateNode>> scanner(String filePath, int minOccurance)
			throws IOException {

		File fileDirectory = new File(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory), "UTF8"));
		int curRow = 1;
		try {
			String curLine = reader.readLine();
			while (curLine != null) {
				populateMap(curLine, curRow);
				curLine = reader.readLine();
				curRow++;
			}
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

	public static void writer(ArrayList<ArtistNode> list) {
		PrintStream outputFile = null;
		try {
			outputFile = new PrintStream("Output");

			for (ArtistNode artist : list) {
				outputFile.print(artist);
				outputFile.print("\n");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			outputFile.close();
		}
	}

}