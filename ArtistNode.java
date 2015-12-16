
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

	@Override
	public boolean equals(Object obj) {

		ArtistNode otherNode = (ArtistNode) obj;
		if (this.firstArtist.equals(otherNode.getFirstArtist())
				&& this.secondArtist.equals(otherNode.getSecondArtist())) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return firstArtist + ", " + secondArtist;
	}
}
