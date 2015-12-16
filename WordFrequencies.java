import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;


public class WordFrequencies {

	public static void main(String[] args) throws Exception {

		boolean caseSensitive = false;
		boolean clean = false;
		BufferedReader txtFile = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> map = new HashMap<>();
		String a = " ";
		String[] words = txtFile.readLine().split(a);
		
		for(String word : words){
			Integer f = map.get(word);
			if(f == null){
				map.put(word, 1);
			} else {
				map.put(word, f+1);
			}
		}
		System.out.println(map);

		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.putAll(map);
		System.out.print(treeMap);
    }
}
