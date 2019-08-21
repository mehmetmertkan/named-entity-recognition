package ceng.ner;

import ceng.ner.ui.MyUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.Token;

import ceng.ner.ui.MyUI;
import zemberek.tokenization.TurkishTokenizer;
import ceng.ner.UndetectedWord;
import java.util.Scanner;

public class DoubleString 
{
	public String str;
	public DoubleString(String str)
	{
		this.str=str;
	}
	public String Doubler() 
	{
		return str;
	}

}
