package ceng.ner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ceng.ner.ui.MyUI;
import zemberek.morphology.analysis.WordAnalysis;
import zemberek.morphology.analysis.tr.TurkishMorphology;

public class Word {

	private String strWord;
	private WordType wordType;
	private String tag;
	private List<Root> listPossibleRoots;
	private List<Suffix> wordEnding;
	private WordAnalysis wordAnalysis;
	private List<WordAnalysis> listWordAnalysis;
	private List<String> listPossibleNERs;
	private String intent;

	public Word(String strWord, String intent) {
		this.intent = intent;
		this.strWord = strWord;
		tag = "";
		listPossibleRoots = new ArrayList<Root>();
		wordEnding = new ArrayList<Suffix>();
		listPossibleNERs = new ArrayList<String>();
		analyzeWord();
		findPossibleRoots();
		findTag();

	}

	public String getWord() {
		return strWord;
	}

	public WordType getWordType() {
		return wordType;
	}

	public void setWordType(WordType wordType) {
		this.wordType = wordType;
	}

	public void findPossibleRoots() {
		for (WordAnalysis possibleRoot : listWordAnalysis) {
			Root root = new Root(possibleRoot.getLemma());
			if (!checkIfExists(root, listPossibleRoots)) {
				listPossibleRoots.add(root);
			}
		}
	}

	private void findTag() {
		String strAnalysis = wordAnalysis.getDictionaryItem().getId();
		String[] tokens = strAnalysis.split("_");
		int i = 0;

		for (String token : tokens) {
			if (i > 0) {
				tag += token;
			}
			i++;
		}
		String strControl = strWord.toLowerCase();
		if (strControl.equals("tl") || strControl.equals("lira") || strControl.equals("dolar") || strControl.equals("₺")
				|| strControl.equals("$")) {
			tag = "Currency";
		}
		String[] daysOfWeek = new String[] { "pazar", "pazartesi", "salı", "çarşamba", "perşembe", "cuma",
				"cumartesi" };
		List<String> list = Arrays.asList(daysOfWeek);
		if (list.contains(strControl)) {
			tag = "Day";
			return;
		}
		if (strControl.contains("ocak") || strControl.contains("şubat") || strControl.contains("mart")
				|| strControl.contains("nisan") || strControl.contains("mayıs") || strControl.contains("haziran")
				|| strControl.contains("ağustos") || strControl.contains("temmuz") || strControl.contains("eylül")
				|| strControl.contains("aralık") || strControl.contains("kasım") || strControl.contains("ekim")) {
			tag = "Month";
			return;
		}
		if (strControl.startsWith("yarın") || strControl.startsWith("bugün") || strControl.startsWith("önce")
				|| strControl.startsWith("gün") || strControl.startsWith("sonra") || strControl.startsWith("ay") 
				|| strControl.startsWith("yıl") || strControl.startsWith("hafta") || strControl.startsWith("geçe")) {
			tag = "Time";
			return;
		}

		else if (strControl.contains("hesab") || strControl.contains("hsab") || strControl.contains("hesb")
				|| strControl.contains("heab") || strControl.contains("esab") || strControl.contains("esap")
				|| strControl.contains("hsb") || strControl.contains("hesa")) {
			tag = "Hesap";
			return;
		}
/*
		else if (strControl.contains("ayındaki") || strControl.contains("ayında") || strControl.contains("ayki")) {
			tag = "MonthSecond";
			return;
		}
*/
		else if (strControl.contains("aralığında") || strControl.contains("aralığındaki")
				|| strControl.contains("aralığı") || strControl.contains("aralıgında")
				|| strControl.contains("aralıgındaki") || strControl.contains("aralıgı")
				|| strControl.contains("arasında") || strControl.contains("arasındaki")
				|| strControl.contains("arası")) {
			tag = "TimeInterval";
			return;
		}

		if (strControl.contains("sonrak") || strControl.contains("sonraki") || strControl.contains("sonrki")
				|| strControl.contains("snraki") || strControl.contains("snrki") || strControl.contains("onraki")) {
			tag = "TimeInterval2";
		}

		if (strControl.contains("fatura") || strControl.contains("fatra") || strControl.contains("ftra")
				|| strControl.contains("ftura") || strControl.contains("atura") || strControl.contains("fatur")
				|| strControl.contains("faura") || strControl.contains("borc")) {
			tag = "Fatura";
			return;
		}

		String[] organizations2 = new String[] { "aski", "turkcell", "enerjisa", "aksa", "başkentgaz", "enerya",
				"esgaz", "igdaş", "kargaz", "palgaz", "3c1b", "digiturk", "binet", "bringo", "d-smart", "extranet",
				"fiberix", "fixnet", "flynet", "foniva", "goonet", "göknet", "hıznet", "kktc telekom", "kktc vodafone",
				"kkturkcell", "linktel", "micronet", "millenicom", "mobilişim", "net mobil", "netgalaksi-elektrohat",
				"oplavus", "pocell", "sahilnet", "sonet", "superonline", "metronet", "teknomobil", "teknosacell",
				"turkinternet", "turknet", "türk telekom", "türksat", "vodafone", "wenet", "asat", "buski", "deski",
				"diski", "gaski", "eski", "hatsu", "izmit su", "iski", "kaski", "koski", "maski", "marsu", "meski",
				"muski", "oski", "saski", "şuski", "taski", "teski", "tiski", "vaski", "betav", "koçfinans", "mobiliz",
				"omv", "oyak", "orfin", "oriflame", "aymakoop", "woongjin", "yemeksepeti", "yurtiçi", "shell", "bp",
				"total", "avea", "vodafone", "vodafon", "alpet", "po", "migros", "bim", "a101", "aras", "sürat",
				"kargo", "benzin", "petrol", "istasyon", "şirket", "kurum", "organizasyon", "kıbrıs", "cell", "fatura",
				"trepaş", "bank", "iş", "şube", "bnk", "manus", "yazılım", "softtech", "odtü", "edirne", "ceyran",
				"ceryan","spotify","bilkent","kızılay","şube","şirket","elektrik","doğalgaz","trepaş","maximum",
				"kart","maksimum","ofis","fesfut","telefon" };

		for (int j = 0; j < organizations2.length; j++) {
			if (strControl.contains(organizations2[j])) {
				tag = "Organization";
			}
		}
	}

	public boolean checkIfExists(Root rootControl, List<Root> listPossibleRoots) {
		boolean isExist = false;
		for (Root root : listPossibleRoots) {
			if (root.getRoot().equals(rootControl.getRoot())) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	public String getTag() {
		return tag;
	}

	public List<Root> getListPossibleRoots() {
		findPossibleRoots();
		return listPossibleRoots;
	}

	private void analyzeWord() {
		TurkishMorphology morphology;
		listPossibleRoots = new ArrayList<Root>();
		morphology = MyUI.getMorphology();
		listWordAnalysis = morphology.analyze(this.getWord());
		wordAnalysis = listWordAnalysis.get(0);
	}

	public void findEnding() {
		for (WordAnalysis endings : listWordAnalysis) {
			Suffix ending = new Suffix(endings.getEnding());
			wordEnding.add(ending);
		}
	}

	public List<Suffix> getEnding() {
		findEnding();
		return wordEnding;
	}

	private String extendedWordTypes(String word) {
		String Wordtype = null;
		String[] daysOfWeek = new String[] { "Pazar", "Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma",
				"Cumartesi" };
		List<String> DaysOfWeek = Arrays.asList(daysOfWeek);

		String[] days = new String[] { "yarın", "bugün", "gün", "geçe" };
		List<String> Days = Arrays.asList(days);

		String[] months = new String[] { "ocak", "şubat", "mart", "nisan", "mayıs", "haziran", "ağustos", "temmuz",
				"eylül", "aralık", "kasım", "ekim", "aya" };
		List<String> Months = Arrays.asList(months);

		String[] organizations = new String[] { "aski", "turkcell", "enerjisa", "aksa", "başkentgaz", "enerya", "esgaz",
				"igdaş", "kargaz", "palgaz", "3c1b", "digiturk", "binet", "bringo", "d-smart", "extranet", "fiberix",
				"fixnet", "flynet", "foniva", "goonet", "göknet", "hıznet", "kktc telekom", "kktc vodafone",
				"kkturkcell", "linktel", "micronet", "millenicom", "mobilişim", "net mobil", "netgalaksi-elektrohat",
				"oplavus", "pocell", "sahilnet", "sonet", "superonline", "metronet", "teknomobil", "teknosacell",
				"turkinternet", "turknet", "türk telekom", "türksat", "vodafone", "wenet", "asat", "buski", "deski",
				"diski", "gaski", "eski", "hatsu", "izmit su", "iski", "kaski", "koski", "maski", "marsu", "meski",
				"muski", "oski", "saski", "şuski", "taski", "teski", "tiski", "vaski", "betav", "koçfinans", "mobiliz",
				"omv", "oyak", "orfin", "oriflame", "aymakoop", "woongjin", "yemeksepeti", "yurtiçi", "shell", "bp",
				"total", "avea", "vodafone", "vodafon", "alpet", "po", "migros", "bim", "a101", "aras", "sürat",
				"kargo", "benzin", "petrol", "istasyon", "şirket", "kurum", "organizasyon", "kıbrıs", "cell", "fatura",
				"trepaş", "bank", "iş", "şube", "akbank", "alternatifbank", "anadolubank", "bayındır", "citibank",
				"denizbank", "dışbank", "finans", "şeker", "tekfen", "icbc", "turkish", "teb", "ekonomi", "eximbank",
				"türkbank", "ttb", "tcmb", "ziraat", "emlak", "sigorta", "aksa", "alkim", "koç", "sabancı", "gıda",
				"bilgisayar", "yazılım", "bilişim", "borusan", "çağdaş", "makro", "yunus","şube","şirket","trepaş",
				"maximum","maksimum","kart","ofis","fesfut","telefon"};
		List<String> Organizations = Arrays.asList(organizations);
		if (DaysOfWeek.contains(word)) {
			System.out.println("dayOfweek");
			Wordtype = "dayOfWeek";
		} else if (Months.contains(word)) {
			Wordtype = "month";
		} else if (Days.contains(word)) {
			System.out.println("day");
			Wordtype = "day";
		} else if (Months.contains(word)) {
			System.out.println("month");
			Wordtype = "month";
		} else if (Organizations.contains(word)) {
			System.out.println("organization");
			Wordtype = "organization";
		}

		return Wordtype;
	}

	public boolean IsDayOfWeek(String word) {
		if (extendedWordTypes(word) == "dayOfWeek")
			return true;
		else
			return false;
	}

	public boolean isDay(String word) {
		if (extendedWordTypes(word) == "day")
			return true;
		else
			return false;
	}

	public boolean isMonth(String word) {
		if (extendedWordTypes(word) == ("month"))
			return true;
		else
			return false;
	}

	public boolean isOrganization(String word) {
		if (extendedWordTypes(word) == "organization")
			return true;
		else
			return false;
	}

	public List<String> getPossibleNERs() {
		return listPossibleNERs;
	}

	public void setPossibleNERs(List<String> listPossibleNERs) {
		this.listPossibleNERs = listPossibleNERs;
	}

	public List<String> findPossibleNers() {
		if (tag.equals("Currency")) {
			listPossibleNERs.add("Currency");
		} else if (tag.equals("Day")) {
			listPossibleNERs.add("Day");
		} else if (wordType.getName().equals("Abbreviation")) {
			listPossibleNERs.add("Organization");
		} else if (wordType.getName().equals("Date")) {
			listPossibleNERs.add("Date");
		} else if (wordType.getName().equals("Time")) {
			listPossibleNERs.add("Time");
		} else if (wordType.getName().equals("Number")) {
			listPossibleNERs.add("Money");
			if (strWord.length() < 5) {
				listPossibleNERs.add("Date");
				listPossibleNERs.add("Time");
			}
			if (intent.equals("bill_payment") && strWord.length() > 5) {
				listPossibleNERs.add("Bill No");
				listPossibleNERs.add("Subscriber No");
			} else if (intent.equals("transfer") && strWord.length() > 5) {
				listPossibleNERs.add("Account No");
			}
		} else if (wordType.getName().equals("Date")) {
			listPossibleNERs.add("Date");
		} else if (wordType.getName().equals("Word")) {
			if (tag.contains("Prop")) {
				listPossibleNERs.add("Organization");
				listPossibleNERs.add("Person");
			} else if (tag.contains("Adv")) {
				listPossibleNERs.add("Organization");
				listPossibleNERs.add("Person");
			} else if (tag.contains("Time")) {
				listPossibleNERs.add("Time");
			} else if (tag.contains("Verb")) {
				listPossibleNERs.add("Verb");
			} else if (tag.contains("Organization")) {
				listPossibleNERs.add("Organization");
			} else {
				listPossibleNERs.add("Person");
			}
		} else if (tag.equals("Date")) {
			listPossibleNERs.add("Date");
		} else if (tag.equals("Date")) {
			listPossibleNERs.add("Date");
		}
		return listPossibleNERs;
	}

	public String getIntent() {
		return this.intent;
	}

}