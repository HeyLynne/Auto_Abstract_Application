package algorithmOperation;

public class AbstractInfo {

	private String source = "";
	private String digest = "";
	private String digest1 = "";
	private String keyword = "";
	private String people = "";
	private String location = "";
	
	public void setSource(String aSource) {
		source = aSource;
	}
	public String getSource() {
		return source;
	}
	
	public void setDigest(String aDigest) {
		digest = aDigest;
	}
	public String getDigest() {
		return digest;
	}
	
	public void setDigest1(String aDigest1) {
		digest1 = aDigest1;
	}
	public String getDigest1() {
		return digest1;
	}
	
	public void setKeyword(String aKeyword) {
		keyword = aKeyword;
	}
	public String getKeyword() {
		return keyword;
	}
	
	public void setPeople(String aPeople) {
		people = aPeople;
	}
	public String getPeople() {
		return people;
	}
	
	public void setLocation(String aLocation) {
		location = aLocation;
	}
	public String getLocation() {
		return location;
	}
	
	public void reset() {
		source = "";
		digest = "";
		digest1 = "";
		keyword = "";
		people = "";
		location = "";
	}
}
