import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.io.*;

public class WordFrequencies {

	public static void main(String[] args) throws Exception {

		boolean noCount = false;
		boolean upperCase = true;
		BufferedReader txtFile = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		String a = " ";

		if(args.length > 1 && (args[0].equals("-s") && args[1].equals("-c"))){
			upperCase = false;
			noCount = true;
		}
		if(args.length > 1 && (args[0].equals("-c") && args[1].equals("-s"))){
			noCount = true;
			upperCase = false;
		}
		if(args.length > 0 && args[0].equals("-s")){
			upperCase = false;
		}
		if(args.length > 0 && args[0].equals("-c")){
			noCount = true;
		}
		
		String line;

		while((line = txtFile.readLine()) != null){
			String[] words = line.replaceAll("[^a-zA-Z0-9\\-]", " ").split(a);
			for(String word : words){
				if(word.length() == 0){
					continue;
				}
				if(upperCase){
					word = word.toUpperCase();
				}
				Integer f = map.get(word);
				if(f == null){
					map.put(word, 1);
				} else {
					map.put(word, f+1);
				} 
			}
		}

		ArrayList<String> aL = new ArrayList<String>(map.keySet());
		Collections.sort(aL);

		if(!noCount){
			for(String str : aL){
				System.out.println(str + " " + map.get(str));
			}
		}
		if(noCount){
			for(String str : aL){
				System.out.println(str);
			}
		}
    }
}
