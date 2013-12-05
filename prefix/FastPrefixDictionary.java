package prefix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class FastPrefixDictionary implements PrefixDictionary{

	HashMap<String, Integer> hash;
	
	public FastPrefixDictionary(String fileName){
		hash = new HashMap<String,Integer>();
		try {
		    BufferedReader file = new BufferedReader(new FileReader(fileName));
		    String line;
		    String[] lineList;
		    while((line = file.readLine()) != null) {
		    	lineList = line.split(",");
		    	hash.put(lineList[0].trim(), Integer.parseInt(lineList[1].trim()));
		    }
		    file.close();
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	@Override
	public long sum(String prefix) {
		
		// TODO Auto-generated method stub
		return 0;
	}

}
