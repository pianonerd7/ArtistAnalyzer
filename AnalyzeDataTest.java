import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class AnalyzeDataTest {

	@Test
	public void filterMapTest() {
		HashMap<String, HashSet<Integer>> artistIndex = new HashMap<String, HashSet<Integer>>();

		HashSet<Integer> set1 = new HashSet<Integer>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		set1.addAll(list1);

		HashSet<Integer> set2 = new HashSet<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(Arrays.asList(5, 6, 7, 8, 9, 10));
		set2.addAll(list2);

		HashSet<Integer> set3 = new HashSet<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.addAll(Arrays.asList(5, 9, 12));
		set3.addAll(list3);

		artistIndex.put("artist1", set1);
		artistIndex.put("artist2", set2);
		artistIndex.put("artist3", set3);

		AnalyzeData analyze = new AnalyzeData("", 3);

		analyze.setArtistIndex(artistIndex);
		analyze.filterMap(3);

		Assert.assertEquals(artistIndex, analyze.getArtistIndex());
	}

	@Test
	public void pairArtistsTest() {
		HashMap<String, HashSet<Integer>> artistIndex = new HashMap<String, HashSet<Integer>>();

		HashSet<Integer> set1 = new HashSet<Integer>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
		set1.addAll(list1);

		HashSet<Integer> set2 = new HashSet<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(Arrays.asList(5, 6, 7, 8, 9, 10));
		set2.addAll(list2);

		HashSet<Integer> set3 = new HashSet<Integer>();
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.addAll(Arrays.asList(14, 15, 16));
		set3.addAll(list3);

		artistIndex.put("artist1", set1);
		artistIndex.put("artist2", set2);
		artistIndex.put("artist3", set3);

		ArrayList<ArtistNode> results = new ArrayList<ArtistNode>();
		results.add(new ArtistNode("artist1", "artist2"));

		AnalyzeData analyze = new AnalyzeData("", 2);
		analyze.setArtistIndex(artistIndex);
		analyze.pairArtists(2);

		Assert.assertEquals("The qualified pairs of artists and the expected are not the same.", results,
				analyze.getQualifiedPairs());

	}
}
