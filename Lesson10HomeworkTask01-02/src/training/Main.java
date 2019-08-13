package training;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		Map<String, String> dictionary = new TreeMap<>();
		dictionary.put("monday", "��������");
		dictionary.put("tuesday", "�������");
		dictionary.put("wednesday", "������");
		dictionary.put("thirsday", "������");
		dictionary.put("friday", "�'������");
		dictionary.put("saturday", "������");
		dictionary.put("sunday", "�����");

		Translator translator1 = new Translator(dictionary);		
		System.out.println(translator1);
		
		Translator translator2 = new Translator("dictionary.csv");
		System.out.println(translator2);
		
		try {		
			translator2.addWord();
			translator2.translate("English.in", "Ukrainian.out");			
			translator2.saveDictionary("dictionary.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}