package ceng.ner;
import java.util.ArrayList;
import java.util.List;

import zemberek.tokenization.TurkishSentenceExtractor;

public class SentenceGroup {

	private List<Sentence> listSentences;
	private String intent;

	public SentenceGroup(String strSentenceGroup, String intent) {
		this.intent = intent;
		listSentences = new ArrayList<Sentence>();
		parseSentenceGroup(strSentenceGroup);
	}

	private List<Sentence> parseSentenceGroup(String strSentenceGroup) {
		TurkishSentenceExtractor extractor = TurkishSentenceExtractor.DEFAULT;
		List<String> listStringSentences = extractor.fromParagraph(strSentenceGroup);
		for (String strSentence : listStringSentences) {
			Sentence sentence;
			sentence = new Sentence(strSentence, intent);
			listSentences.add(sentence);
		}
		return listSentences;
	}

	public List<Sentence> getListSentences() {
		return listSentences;
	}

}
