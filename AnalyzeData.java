import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AnalyzeData {

	private String fileName;
	private int minOccurance;
	private HashMap<String, HashSet<Integer>> artistIndex;
	private ArrayList<ArtistNode> qualifiedPairs;

	public AnalyzeData(String fileName, int minOccurance) {
		this.fileName = fileName;
		this.minOccurance = minOccurance;
		qualifiedPairs = new ArrayList<ArtistNode>();
	}

	/**
	 * Analyzes the artists. Scans, then filters, then pairs, then writes to
	 * output
	 */
	public void analyzeData() {
		try {
			artistIndex = CSVUtility.scanner(fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		filterMap(minOccurance);
		pairArtists(minOccurance);

		CSVUtility.writer(qualifiedPairs);
		System.out.println("done writing");
	}

	/**
	 * Filters from the artistIndex any artist that has less than the minimum
	 * number of occurrences (in this case, 50)
	 * 
	 * @param minimum
	 *            The minimum number of occurrence
	 */
	public void filterMap(int minimum) {

		String[] keys = artistIndex.keySet().toArray(new String[0]);

		for (String artist : keys) {
			if (artistIndex.get(artist).size() < minimum) {
				artistIndex.remove(artist);
			}
		}
	}

	/**
	 * Traverses the filtered out map and finds the intersect of two artists. If
	 * greater than minOccurance, then added to arrayList
	 * 
	 * @param minOccurance
	 */
	public void pairArtists(int minOccurance) {

		String[] keys = artistIndex.keySet().toArray(new String[0]);

		for (int indexi = 0; indexi < keys.length; indexi++) {
			for (int indexj = indexi + 1; indexj < keys.length; indexj++) {

				HashSet<Integer> temp = artistIndex.get(keys[indexi]);
				temp.retainAll(artistIndex.get(keys[indexj]));

				if (temp.size() >= minOccurance) {
					qualifiedPairs.add(new ArtistNode(keys[indexi], keys[indexj]));
				}
			}
		}
	}

	public ArrayList<ArtistNode> getQualifiedPairs() {
		return qualifiedPairs;
	}

	public HashMap<String, HashSet<Integer>> getArtistIndex() {
		return artistIndex;
	}

	public void setArtistIndex(HashMap<String, HashSet<Integer>> map) {
		this.artistIndex = map;
	}

	public static void main(String[] args) throws IOException {
		AnalyzeData ad = new AnalyzeData(args[0], Integer.parseInt(args[1]));
		ad.analyzeData();
	}
}
