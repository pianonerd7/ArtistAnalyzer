
public class ArtistNode {
	private String firstArtist;
	private String secondArtist;
	
	public ArtistNode(String firstArtist, String secondArtist) {
		this.firstArtist = firstArtist;
		this.secondArtist = secondArtist;
	}
	
	public String getFirstArtist() {
		return firstArtist;
	}
	
	public String getSecondArtist() {
		return secondArtist;
	}
	
	public String toString() {
		return "First: " + firstArtist + ", Second: " + secondArtist;
	}
}
