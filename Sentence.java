package ceng.ner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.Token;

import ceng.ner.ui.MyUI;
import zemberek.tokenization.TurkishTokenizer;
import ceng.ner.UndetectedWord;
import java.util.Scanner;

public class Sentence {

	public String strSentence;
	public List<Word> listWordsOfSentence;
	public String intent;
	public String Organization;
	public String Organization2;
	public String Amount;
	public String Account;
	public String Account2;
	public String Date;
	public String Date2;
	public String AmountUpperBound;
	public String AmountLowerBound;
	public String Bill;
	public String Bill2;
	public String TimeInterval;
	public String TimeInterval2;
	private int flagAmount;
	public static List<Sentence> sentenceHistory;

	public Sentence(String strSentence, String intent) {
		this.strSentence = strSentence;
		this.intent = intent;
		this.Amount = null;
		this.Account = null;
		this.Account2 = null;
		this.Organization = null;
		this.Organization2 = null;
		this.AmountUpperBound = null;
		this.AmountLowerBound = null;
		this.Date = null;
		this.Date2 = null;
		this.Bill = null;
		this.Bill2 = null;
		this.TimeInterval = null;
		this.TimeInterval2 = null;
		this.flagAmount = 0;

		listWordsOfSentence = new ArrayList<Word>();
		parseSentence();
	}

	private List<Word> parseSentence() {
		TurkishTokenizer tokenizer = TurkishTokenizer.DEFAULT;
		List<Token> listTokens = tokenizer.tokenize(strSentence);
		for (Token token : listTokens) {
			Word word = new Word(token.getText(), intent);
			WordType wordType = new WordType(token.getType());
			word.setWordType(wordType);
			listWordsOfSentence.add(word);
		}
		return listWordsOfSentence;
	}

	public String getSentence() {
		return strSentence;
	}

	public String getOrganization() {
		return Organization;
	}

	public String getOrganization2() {
		return Organization2;
	}

	public String getAmount() {
		return Amount;
	}

	public String getAccount() {
		return Account;
	}

	public String getAccount2() {
		return Account2;
	}

	public String getBill() {
		return Bill;
	}

	public String getBill2() {
		return Bill2;
	}

	public String getDate() {
		return Date;
	}

	public String getDate2() {
		return Date2;
	}

	public String getUpperbound() {
		return AmountUpperBound;
	}

	public String getLowerbound() {
		return AmountLowerBound;
	}

	public String getTimeInterval() {
		return TimeInterval;
	}

	public String getTimeInterval2() {
		return TimeInterval2;

	}

	private void setTimeInterval(String timeInterval) {
		this.TimeInterval = timeInterval;
	}

	private void setTimeInterval2(String timeInterval) {
		this.TimeInterval2 = timeInterval;
	}

	private void setUpperBound(String Uperbound) {
		AmountUpperBound = Uperbound;
	}

	private void setLowerBound(String Lowerbound) {
		AmountLowerBound = Lowerbound;
	}

	private void setDate(String date) {
		Date = date;
	}

	private void setDate2(String date2) {
		Date2 = date2;
	}

	private void setAmount(String amount) {
		Amount = amount;
	}

	private void setOrganization(String organization) {
		Organization = organization;
	}

	private void setOrganization2(String organization2) {
		Organization2 = organization2;
	}

	private void setAccount(String account) {
		Account = account;
	}

	private void setAccount2(String account2) {
		Account2 = account2;
	}

	private void setBill(String bill) {
		Bill = bill;
	}

	private void setBill2(String bill2) {
		Bill2 = bill2;
	}

	private int getFlagAmount() {
		return flagAmount;
	}

	public List<Word> getListWordsOfSentence() {

		return listWordsOfSentence;
	}

	// returns amount ,if not found,returns zero
	public String findAmount() {
		List<Word> ListOfWords = getListWordsOfSentence();
		String tl = "tl";
		String lira = "lira";
		String dolar = "dolar";
		String euro = "euro";
		String pound = "pound";
		int sum = 0;
		String strCurrency = " ";
		boolean amountIsAvailable = false;
		float floatAmount = 0;
		for (Word word : ListOfWords) {
			if (word.getWord().equals(tl)) {
				amountIsAvailable = true;
				strCurrency = tl;
			} else if (word.getWord().equals(lira)) {
				amountIsAvailable = true;
				strCurrency = lira;
			} else if (word.getWord().equals(euro)) {
				amountIsAvailable = true;
				strCurrency = euro;
			} else if (word.getWord().equals(pound)) {
				amountIsAvailable = true;
				strCurrency = pound;
			} else if (word.getWord().equals(dolar)) {
				amountIsAvailable = true;
				strCurrency = dolar;
			}
		}
		if (amountIsAvailable) {
			String milyon = "milyon";
			String yuz = "yuz";
			String bin = "bin";
			int mult = 1;
			String strWord;
			for (Word word : ListOfWords) {
				strWord = word.getWord();
				if (word.getWordType().getName().equals("Number")) {

					int number = Integer.parseInt(strWord);
					sum = (number * mult) + sum;
				} else if (strWord.equals(bin)) {
					mult = 1000;
					sum = sum * mult;
				} else if ((strWord.equals(lira)) || (strWord.equals(tl))
						|| (strWord.equals(dolar) || (strWord.equals(pound) || (strWord.equals(euro))))) {
					floatAmount = sum;

				} else {
					mult = 1;
					sum = 0;
				}
				flagAmount = 1;
			}
		}
		return String.valueOf(floatAmount) + ' ' + strCurrency;
	}

	public Word getPrev(int index) {
		String prev_word;
		if (index > 0) {
			prev_word = listWordsOfSentence.get(index - 1).getWord();
		} else
			prev_word = " ";
		Word prev = new Word(prev_word, listWordsOfSentence.get(0).getIntent());
		return prev;
	}

	public Word getPrev1(Word curr) {
		Word null_prev = new Word(" ", "0");
		List<String> ListOfWordString = new ArrayList<String>();
		for (Word word : listWordsOfSentence) {
			ListOfWordString.add(word.getWord());
		}
		String first_word = ListOfWordString.get(0);
		if (curr.getWord().equals(first_word))
			return null_prev;
		else {
			Word prev = new Word(ListOfWordString.get((ListOfWordString.indexOf(curr.getWord())) - 1),
					listWordsOfSentence.get(0).getIntent());
			return prev;
		}
	}

	public Word getNext(Word curr) {
		Word null_next = new Word(" ", "0");
		List<String> ListOfWordString = new ArrayList<String>();
		for (Word word : listWordsOfSentence) {
			ListOfWordString.add(word.getWord());
		}
		String last_word = ListOfWordString.get(listWordsOfSentence.size() - 1);
		if (curr.getWord().equals(last_word))
			return null_next;
		else {
			Word next = new Word(ListOfWordString.get((ListOfWordString.indexOf(curr.getWord())) + 1),
					listWordsOfSentence.get(0).getIntent());
			return next;
		}
	}
	public ArrayList<String> findEntities() {

		List<Word> ListWords = null;
		String Amount = findAmount();
		setAmount(Amount);
		ListWords = getListWordsOfSentence();
		ArrayList<String> ListOfEntities = new ArrayList<String>();
		String Org = " ";
		String Org2 = " ";
		String OrgYedek = " "; // for when there are two accounts and both are
								// org account
		String OrgYedek2 = " "; // for when there are two accounts and both are
								// org account
		// There are two orgyedeks because there could be org1 org2 but also acc1 acc2
		String Time = " ";
		String Account = " ";
		String Account2 = " ";
		String Bill = " ";
		String Bill2 = " ";
		int index = 0;
		int flag = 0;
		int numOfOrg = 0;
		Word prevW = new Word(" ", "asd");
		List<String> kucukList = Arrays.asList("küçük", "küçk", "kçük", "kçk", "üçük", "az", "minik", "kucuk", "kuck",
				"kck", "düşük");
		List<String> buyukList = Arrays.asList("büyük", "fazla", "çok", "büyk", "byük", "byk", "büyü", "kucuk", "üyük",
				"fzla", "fazl", "fzl", "fazal", "buyuk", "byk", "yüksek");
		for (Word word : ListWords) {

			if (kucukList.contains(word.getWord())) {
				String tmp = "küçük";
				Word tmp_word = new Word(word.getWord(), word.getIntent());
				int tmp_index = index;
				while (!(getPrev(tmp_index)).getTag().equals("NumCard")) {
					tmp = getPrev(tmp_index).getWord() + " " + tmp;
					tmp_word = getPrev(tmp_index);
					tmp_index--;
					if (tmp_index == 0)
						break;
				}
				tmp = getPrev(tmp_index).getWord() + " " + tmp;
				this.setLowerBound(tmp);
			}
			if (buyukList.contains(word.getWord())) {
				String tmp = "büyük";
				Word tmp_word = new Word(word.getWord(), word.getIntent());
				int tmp_index = index;
				while (!(getPrev(tmp_index)).getTag().equals("NumCard")) {
					tmp = getPrev(tmp_index).getWord() + " " + tmp;
					tmp_word = getPrev(tmp_index);
					tmp_index--;
					if (tmp_index == 0)
						break;
				}
				tmp = getPrev(tmp_index).getWord() + " " + tmp;
				this.setUpperBound(tmp);
			}
			if (word.getTag().equals("NumCard")) {
				String tmp = "";
				int tmp_index = index;
				if (buyukList.contains(getPrev(tmp_index).getWord())) {
					if (getPrev(tmp_index - 1).getWord().equals("en")) {
						if (getNext(word).getWord().equals("tl")) {
							tmp = getPrev(tmp_index - 1).getWord() + " " + getPrev(tmp_index).getWord() + " "
									+ word.getWord() + " tl";
							this.setUpperBound(tmp);
						} else {
							tmp = getPrev(tmp_index - 1).getWord() + " " + getPrev(tmp_index).getWord() + " "
									+ word.getWord();
							this.setUpperBound(tmp);
						}

					}
				} else if (kucukList.contains(getPrev(tmp_index).getWord())) {
					if (getPrev(tmp_index - 1).getWord().equals("en")) {
						if (getNext(word).getWord().equals("tl")) {
							tmp = getPrev(tmp_index - 1).getWord() + " " + getPrev(tmp_index).getWord() + " "
									+ word.getWord() + " tl";
							this.setLowerBound(tmp);
						} else {
							tmp = getPrev(tmp_index - 1).getWord() + " " + getPrev(tmp_index).getWord() + " "
									+ word.getWord();
							this.setLowerBound(tmp);
						}
					}
				}
			}

			else if (word.getWord().equals("son") || word.getWord().equals("sonu")) {
				String tmp = "sonu";
				Word tmp_word = new Word(word.getWord(), word.getIntent());
				while (getNext(tmp_word).getTag().equals("NumCard")) {
					tmp = tmp + " " + getNext(tmp_word).getWord();
					tmp_word = getNext(tmp_word);
				}
				Time = Time + " " + tmp;
				setDate(Time);
			} else if (word.getWord().equals("babama"))
				setAccount("babama");
			else if (word.getWord().equals("anneme"))
				setAccount("anneme");
			else if (word.getTag().equals("NounTime")) {
				Time = Time + " " + word.getWord();
				setDate(Time);
			} else if (word.getTag().equals("Adv")) {
				if (word.isDay(word.getWord())) {
					Time = Time + " " + word.getWord();
					setDate(Time);
				} else {
					List<Root> roots = word.getListPossibleRoots();
					String tmp = " ";
					int f_root = 0;
					for (Root root : roots) {
						Word checkRoot = new Word(root.getRoot(), word.getIntent());
						if (checkRoot.getTag().equals("Time")) {
							tmp = checkRoot.getWord();
							f_root++;
						}
					}
					if (f_root >= 1) {
						Time = Time + " " + tmp;
						setDate(Time);
					}
				}
			} else if (word.getTag().equals("Month")) {
				List<Root> roots = word.getListPossibleRoots();
				int tmp_cnt = 0;
				for (Root root : roots) {
					Word IsRootMonth = new Word(root.getRoot(), word.getIntent());
					if (IsRootMonth.getTag().equals("Month")) {
						if (getNext(word).getTag().equals("NumCard")) {
							Time = IsRootMonth.getWord() + " " + getNext(word).getWord();
							if (this.getDate() != null)
								{
								setDate2(Time);
								}
							else
								{	
									setDate(Time);
								}
							} else {
							Time = word.getWord();
							if (getDate() != null)
								setDate2(Time);
							else
								setDate(Time);
						}
					} else if (IsRootMonth.getTag().equals("Day")) {
						if (tmp_cnt == 0) {
							Time = Time + " " + IsRootMonth.getWord();
							if (getDate() != null)
								setDate2(Time);
							else
								setDate(Time);
							tmp_cnt++;
						}
					}
				}
			} // aski faturası öde

			else if (word.getTag().equals("Organization") && numOfOrg == 0) {
				Word tmp = getPrev(index);
				if (Org == " " || tmp.getTag().equals("Organization")) {
					Org = Org + " " + word.getWord();
					setOrganization(Org);
				}
			} else if (word.getTag().equals("Organization") && numOfOrg == 1) {
				Word tmp = getPrev(index);
				if (!tmp.getTag().equals("Organization") && flag == 1) {
					Org2 = Org2 + " " + word.getWord();
					flag = 0;
					setOrganization2(Org2);
				}
				if (tmp.getTag().equals("Organization")) {
					Org2 = Org2 + " " + word.getWord();
					setOrganization2(Org2);
				}
			}
			if (!word.getTag().equals("Organization")) {
				Word tmp = getPrev(index);
				if (tmp.getTag().equals("Organization")) {
					numOfOrg = 1;
					flag = 1;
				}
			}

			if (word.getTag().equals("Fatura")) {
				Bill = Org + " " + "faturası";
				setBill(Bill);
				if (Org2 != " ") {
					Bill2 = Org2 + " " + "faturası";
					setBill2(Bill2);
				}
			}

			if (word.getTag().equals("Hesap")) {
				Word tmp = getPrev(index);
				if (Account.length() < 2) {
					if (tmp.getWord().endsWith("ın") || tmp.getWord().endsWith("in")) {
						Account = Account + tmp.getWord() + " " + "hesabı";
					}
					if (tmp.getTag().equals("Organization")) {
						if (Org != " ") {
							Account = Org + " " + "hesabı";
						}
						if (Org2 != " ") {
							Account = Org2 + " " + "hesabı";
						}
					}
					setAccount(Account);
				} else {
					if (tmp.getWord().endsWith("ın") || tmp.getWord().endsWith("in")) {
						Account2 = Account2 + tmp.getWord() + " " + "hesabı";
					}
					if (tmp.getTag().equals("Organization")) {
						if (Org != " ") {
							Account2 = Org + " " + "hesabı";
						}
						if (Org2 != " ") {
							Account2 = Org2 + " " + "hesabı";
						}
					}
					setAccount2(Account2);
				}

			}

			if (word.getWord().equals("öde") || word.getWord().equals("yatır")) {
				Word tmp = getPrev(index);
				String str = tmp.getWord();
				if (tmp.getTag().equals("Organization")
						&& (tmp.getWord().endsWith("yi") || tmp.getWord().endsWith("yı"))) {
					String upToNCharacters = str.substring(0, Math.min(str.length(), str.length() - 2));
					Bill = upToNCharacters + " " + "faturası";
					setBill(Bill);
				} else if (tmp.getTag().equals("Organization")
						&& (tmp.getWord().endsWith("i") || tmp.getWord().endsWith("ı"))) {
					String upToNCharacters = str.substring(0, Math.min(str.length(), str.length() - 1));
					Bill = upToNCharacters + " " + "faturası";
					setBill(Bill);
				}
			}

			if (word.getTag().startsWith("Time") || word.getTag().startsWith("Day")) {
				Time = Time + " " + word.getWord();
				setDate(Time);
				Word tmp = getPrev(index);
				if (tmp.getTag().equals("NumCard"))
					Time = tmp.getWord() + " " + Time;
				else if (tmp.getWord().equals("önceki"))
					Time = " önceki " + word.getWord();
				else if (tmp.getWord().equals("geçen"))
					Time = " geçen " + word.getWord();
				else if (tmp.getWord().equals("geçtiğimiz"))
					Time = " geçtiğimiz " + word.getWord();
				setDate(Time);
			} else if (word.getTag().equals("TimeInterval")) {
				String tmp = "";
				int tmp_index = index;
				Word prev = getPrev(index);
				if (prev.getTag().equals("Month")) {
					tmp = prev.getWord();
					tmp_index--;
					Word prevsPrev = getPrev(tmp_index);

					if (prevsPrev.getTag().equals("NumCard")) {
						tmp_index--;
						tmp = prevsPrev.getWord() + " " + tmp;
						Word thirdPrev = getPrev(tmp_index);

						if (thirdPrev.getTag().equals("Month")) {
							tmp = thirdPrev.getWord() + " " + tmp;
							tmp_index--;
							Word forthPrev = getPrev(tmp_index);

							if (forthPrev.getTag().equals("NumCard"))
								tmp = forthPrev.getWord() + " " + tmp + " aralığındaki";
						}
					}
				}

				setTimeInterval(tmp);
			}

			else if (word.getTag().equals("TimeInterval2")) {
				String tmp = "";
				Word prev = getPrev(index);
				Word prevsPrev = getPrev(index - 1);
				tmp = prevsPrev.getWord() + " " + prev.getWord() + " " + "sonraki";
				setTimeInterval2(tmp);
			}
			prevW = word; // for previous word finding, more accurate than getPrev()
			index++;
		}
		if (getFlagAmount() == 1) {
			ListOfEntities.add(getAmount() + "-> " + "[Amount]");
		}
		if (getUpperbound() != null) {
			ListOfEntities.add(this.AmountUpperBound + "-> " + "[Upper Amount Bound]");
		}
		if (getLowerbound() != null) {
			ListOfEntities.add(this.AmountLowerBound + "-> " + "[Lower Amount Bound]");
		}

		if (getDate() != null) {
			// if (getDate2() != null) {
			// setDate(getDate() + " ve " + getDate2());
			// }
			ListOfEntities.add(getDate() + "-> " + "[Date]");
		}

		if (getOrganization() != null) {
			ListOfEntities.add(getOrganization() + "-> " + "[Organization]");
		}
		if (getOrganization2() != null) {
			ListOfEntities.add(getOrganization2() + "-> " + "[Organization2]");
		}
		if (getAccount() != null) {
			ListOfEntities.add(getAccount() + "-> " + "[Account]");
		}
		if (getAccount2() != null) {
			ListOfEntities.add(getAccount2() + "-> " + "[Account2]");
		}

		if (getBill() != null) {
			ListOfEntities.add(getBill() + "-> " + "[Bill]");
		}

		if (getBill2() != null) {
			ListOfEntities.add(getBill2() + "-> " + "[Bill2]");
		}

		if (TimeInterval != null) {
			ListOfEntities.add(this.TimeInterval + "-> " + "[Time Interval]");
		}

		if (TimeInterval2 != null) {
			ListOfEntities.add(this.TimeInterval2 + "-> " + "[Time Interval]");
		}
		return ListOfEntities;
	}

	private void findMissOrg() {
		sentenceHistory = MyUI.getSentenceHistory();
		if (getOrganization() == null) {
			if (sentenceHistory.size() == 0) {
				System.out.println("There is no history!");
			} else {
				int i;
				for (i = sentenceHistory.size() - 1; i >= 0; i--) {
					if (sentenceHistory.get(i).getOrganization() != null) {
						this.setOrganization(sentenceHistory.get(i).getOrganization());
						break;
					}
				}
				if (i == -1)
					System.out.println("Organization cannot found!");
				else {
					System.out.println(this.getOrganization() + "-> [Organization] is found in the history!");
				}
			}
		}
	}

	private void findMissAmount() {
		sentenceHistory = MyUI.getSentenceHistory();
		if (flagAmount == 0) {
			if (sentenceHistory.size() == 0) {
				System.out.println("There is no history!");
			} else {
				int i;
				for (i = sentenceHistory.size() - 1; i >= 0; i--) {
					if (sentenceHistory.get(i).getFlagAmount() == 1) {
						this.setAmount(sentenceHistory.get(i).getAmount());
						break;
					}
				}
				if (i == -1)
					System.out.println("Amount cannot found!");
				else {
					System.out.println(this.getAmount() + "-> [Amount] is found in the history!");
				}
			}
		}
	}

	private void findMissDate() {
		sentenceHistory = MyUI.getSentenceHistory();
		if (getDate() == null) {
			if (sentenceHistory.size() == 0) {
				System.out.println("There is no history!");
			} else {
				int i;
				for (i = sentenceHistory.size() - 1; i >= 0; i--) {
					if (sentenceHistory.get(i).getDate() != null) {
						this.setDate(sentenceHistory.get(i).getDate());
						break;
					}
				}
				if (i == -1)
					System.out.println("Date cannot found!");
				else {
					System.out.println(this.getDate() + "-> [Date] is found in the history!");
				}
			}
		}
	}

	private void findMissAccount() {
		sentenceHistory = MyUI.getSentenceHistory();
		if (getAccount() == null) {
			if (sentenceHistory.size() == 0) {
				System.out.println("There is no history!");
			} else {
				int i;
				for (i = sentenceHistory.size() - 1; i >= 0; i--) {
					if (sentenceHistory.get(i).getAccount() != null) {
						this.setAccount(sentenceHistory.get(i).getAccount());
						break;
					}
				}
				if (i == -1)
					System.out.println("Account cannot found!");
				else {
					System.out.println(this.getAccount() + "-> [Account] is found in the history!");
				}
			}
		}
	}

	public void findMissingEntities() {
		sentenceHistory = MyUI.getSentenceHistory();
		if (intent.equals("Bill Payment")) // org,Amount,time(sometimes)
		{
			findMissOrg();
			findMissAmount();
		} else if (intent.equals("PFM")) // date,Org,Amount(sometimes)
		{
			findMissDate();
			findMissOrg();
		} else // Money Transfer Amount
		{
			findMissAccount();
			findMissAmount();
		}
	}

}
