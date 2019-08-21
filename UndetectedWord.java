package ceng.ner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import zemberek.morphology.analysis.tr.TurkishMorphology;
import zemberek.normalization.TurkishSpellChecker;

public class UndetectedWord {

	private TurkishMorphology morphology;
	private TurkishSpellChecker spellChecker;
	private String strWord;

	public UndetectedWord(String strWord) throws IOException {

		this.strWord = strWord;
		morphology = TurkishMorphology.createWithDefaults();
		spellChecker = new TurkishSpellChecker(morphology);
	}

	public boolean isNear(char chr1, char chr2) {
		HashMap<Character, String> keyMap = new HashMap<Character, String>();
		keyMap.put('a', "qwsz<");
		keyMap.put('b', " vghn");
		keyMap.put('c', " vxdf");
		keyMap.put('ç', "ölş.");
		keyMap.put('d', "serfcx");
		keyMap.put('e', "w34rds");
		keyMap.put('f', "drtgvc");
		keyMap.put('g', "ftyhbv");
		keyMap.put('ğ', "p*-üiş");
		keyMap.put('h', "gyujnb");
		keyMap.put('ı', "u89okj");
		keyMap.put('i', "şğü,.");
		keyMap.put('j', "huıkmn");
		keyMap.put('k', "jıolöm");
		keyMap.put('l', "kopşçö");
		keyMap.put('m', "njkö");
		keyMap.put('n', "bhjm");
		keyMap.put('o', "kı90pl");
		keyMap.put('ö', "mklç");
		keyMap.put('p', "o0*ğşl");
		keyMap.put('q', "12wsa");
		keyMap.put('r', "e45tfd");
		keyMap.put('s', "awedxz");
		keyMap.put('ş', "lpği.ç");
		keyMap.put('t', "r56ygf");
		keyMap.put('u', "y78ıjh");
		keyMap.put('ü', "-ği,");
		keyMap.put('v', "cfgb");
		keyMap.put('w', "q23esa");
		keyMap.put('x', "zsdc");
		keyMap.put('y', "t67uhg");
		keyMap.put('z', "asx<");
		keyMap.put('1', "2q");
		keyMap.put('2', "1qw3");
		keyMap.put('3', "2we4");
		keyMap.put('4', "3er5");
		keyMap.put('5', "4rt6");
		keyMap.put('6', "5ty7");
		keyMap.put('7', "6yu8");
		keyMap.put('8', "7uı9");
		keyMap.put('9', "8ıo0");
		keyMap.put('0', "9op*");
		keyMap.put('*', "0pğ-");
		keyMap.put('-', "*ğü");
		keyMap.put('<', "az");
		keyMap.put('.', "çşi");
		keyMap.put(',', "üi");

		String str = keyMap.get(chr1);
		for (int i = 0; i < str.length(); i++) {
			if (chr2 == str.charAt(i))
				return true;
		}
		return false;
	}

	public Word wordCorrection() {

		int counter = 0;
		int minCost = 1000;
		int gap = 15;
		int substitution = 16;
		int match = 0;
		int nearKeyPenalty = 1;
		Word wordCorrected = null;

		List<String> listWords = suggestWords();
		int costs[] = new int[listWords.size()];
		for (int k = 0; k < listWords.size(); k++) {
			String strControl = listWords.get(k);
			int[][] opt = new int[strControl.length() + 1][strWord.length() + 1];
			for (int i = 1; i <= strControl.length(); i++) {
				opt[i][0] = opt[i - 1][0] + gap;
			}
			for (int j = 1; j <= strWord.length(); j++) {
				opt[0][j] = opt[0][j - 1] + gap;
			}

			for (int i = 1; i <= strControl.length(); i++) {
				for (int j = 1; j <= strWord.length(); j++) {
					int scoreDiag;
					if (strControl.charAt(i - 1) == strWord.charAt(j - 1)) {
						scoreDiag = opt[i - 1][j - 1] + match;
					} else if (isNear(strControl.charAt(i - 1), strWord.charAt(j - 1)) == true) {
						scoreDiag = opt[i - 1][j - 1] + nearKeyPenalty;
					} else {
						scoreDiag = opt[i - 1][j - 1] + substitution;
					}
					int scoreLeft = opt[i][j - 1] + gap; // insertion
					int scoreUp = opt[i - 1][j] + gap; // deletion
					opt[i][j] = Math.min(Math.min(scoreDiag, scoreLeft), scoreUp);
				}
			}
			costs[k] = opt[strControl.length() - 1][strWord.length() - 1];
			if (costs[k] < minCost) {
				minCost = costs[k];
				counter = k;
			}
		}
		if (listWords.size() != 0) {
			System.out.println(minCost);
			wordCorrected = new Word(listWords.get(counter), "undetected");
		}
		return wordCorrected;
	}

	public List<String> suggestWords() {
		List<String> listStrWords = spellChecker.suggestForWord(strWord);
		return listStrWords;
	}
}