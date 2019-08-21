package ceng.ner;

public class WordType {
	String strWordType;

	public WordType(int wordTypeId) {
		defineWordType(wordTypeId);
	}

	private void defineWordType(int wordTypeId) {

		if (wordTypeId == 1) {
			strWordType = "Abbreviation";
		} else if (wordTypeId == 2) {
			strWordType = "SpaceTab";
		} else if (wordTypeId == 3) {
			strWordType = "NewLine";
		} else if (wordTypeId == 4) {
			strWordType = "Time";
		} else if (wordTypeId == 5) {
			strWordType = "Date";
		} else if (wordTypeId == 6) {
			strWordType = "PercentNumeral";
		} else if (wordTypeId == 7) {
			strWordType = "Number";
		} else if (wordTypeId == 8) {
			strWordType = "URL";
		} else if (wordTypeId == 9) {
			strWordType = "Email";
		} else if (wordTypeId == 10) {
			strWordType = "HashTag";
		} else if (wordTypeId == 11) {
			strWordType = "Mention";
		} else if (wordTypeId == 12) {
			strWordType = "Emoticon";
		} else if (wordTypeId == 13) {
			strWordType = "RomanNumeral";
		} else if (wordTypeId == 14) {
			strWordType = "AbbreviationWithDots";
		} else if (wordTypeId == 15) {
			strWordType = "Word";
		} else if (wordTypeId == 16) {
			strWordType = "WordWithApostrophe";
		} else if (wordTypeId == 17) {
			strWordType = "Punctuation";
		} else if (wordTypeId == 18) {
			strWordType = "UnknownWord";
		} else if (wordTypeId == 19) {
			strWordType = "Unknown";
		}
	}

	public String getName() {
		return strWordType;
	}
}
