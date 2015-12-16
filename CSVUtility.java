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

	private static HashMap<String, HashSet<Integer>> artistIndex = new HashMap<String, HashSet<Integer>>();

	/**
	 * Scans input file, and reads row by row into artistIndex
	 * 
	 * @param filePath
	 *            File which we are reading from
	 * @param minOccurance
	 *            Minimum occurrence needed for two artists
	 * @return The map containing all artists and their corresponding row and
	 *         column index
	 * @throws IOException
	 *             Error during file reading and handling
	 */
	public static HashMap<String, HashSet<Integer>> scanner(String filePath) throws Exception {

		File fileDirectory = new File(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileDirectory), "UTF8"));
		int curRow = 1;
		String curLine = reader.readLine();
		while (curLine != null) {
			populateMap(curLine, curRow);
			curLine = reader.readLine();
			curRow++;
		}
		reader.close();
		return artistIndex;
	}

	/**
	 * For each line read in scanner, line is split at comma and added to the
	 * hashmap
	 * 
	 * @param curLine
	 *            Current line
	 * @param curRow
	 *            Current row
	 */
	private static void populateMap(String curLine, int curRow) {
		String[] artists = curLine.split(",");

		for (String artist : artists) {
			HashSet<Integer> temp = artistIndex.get(artist);

			if (temp != null) {
				temp.add(curRow);
			} else {
				HashSet<Integer> newSet = new HashSet<Integer>();
				newSet.add(curRow);
				artistIndex.put(artist, newSet);
			}
		}
	}

	/**
	 * Writes the list to an output file
	 * 
	 * @param list
	 *            The list containing the data to write in the output file
	 */
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