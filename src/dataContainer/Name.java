package dataContainer;

public class Name {
	private String fName;
	private String lName;

	public Name(String Name) {

		String[] fullName = Name.split(",");
		this.lName = fullName[0];
		this.fName = fullName[1];
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

}
