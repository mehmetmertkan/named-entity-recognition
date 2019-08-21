package ceng.ner;

public class Suffix {

	private String suffixAttribute;
	private String person;
	private String time;
	private boolean plural;

	public Suffix(String attr) {
		this.suffixAttribute = attr;
		findSuffixAttribute();
	}

	private void findSuffixAttribute() {
		if (this.suffixAttribute.startsWith("P") && this.suffixAttribute.length() == 4
				&& ((this.suffixAttribute.endsWith("sg")) || (this.suffixAttribute.endsWith("pl"))
						|| (this.suffixAttribute.endsWith("on")))) {
			analyzeSuffix(suffixAttribute.charAt(1), suffixAttribute.charAt(2));

		}

		if (this.suffixAttribute.startsWith("A") && this.suffixAttribute.length() == 4
				&& ((this.suffixAttribute.endsWith("sg")) || (this.suffixAttribute.endsWith("pl"))
						|| (this.suffixAttribute.endsWith("on")))) {
			analyzeSuffix(suffixAttribute.charAt(1), suffixAttribute.charAt(2));
		}

		if (this.suffixAttribute.equals("Prog"))
			time = "Present";
		if (this.suffixAttribute.equals("Past"))
			time = "Past";
		if (this.suffixAttribute.equals("Fut"))
			time = "Future";
	}

	private void analyzeSuffix(char second, char third) {
		if (second == '1')
			person = "First";
		else if (second == '2')
			person = "Second";
		else if (second == '3')
			person = "Third";

		if (third == 'p')
			plural = true;
		else if (third == 's')
			plural = false;

	}

	public String getPerson() {
		return person;
	}

	public String getTime() {
		return time;
	}

	public boolean getPlurality() {
		return plural;
	}
}
