package prefix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to create a hash of the String and Integer values in a dictionary
 * @author Dan
 *
 */
public class FastPrefixDictionary implements PrefixDictionary{

	Map<String, Map<String, Integer>> map;
	Map<String, Integer> map1;
	
	/**
	 * Constructor to make a hash of the filename
	 * @param fileName name of the file of which to parse into a hash
	 */
	public FastPrefixDictionary(String fileName){
		map = new HashMap<String,Map<String, Integer>>();
		try {
		    BufferedReader file = new BufferedReader(new FileReader(fileName));
		    String line;
		    String[] lineList;
		    while((line = file.readLine()) != null) {
		    	lineList = line.split(",");
		    	String key = lineList[0].trim();
		    	String first = key.substring(0, 1);
		    	String rest = key.substring(1,key.length());
		    	if(!map.containsKey(first)){
		    		map1 = new HashMap<String, Integer>();
		    		map.put(first, map1);
		    	  	map1.put(rest, Integer.parseInt(lineList[1].trim()));
		    	}else{
		    		map1 = map.get(first);
		    		map1.put(rest, Integer.parseInt(lineList[1].trim()));
		    	}
		    		
		    }
		    file.close();
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
	
	/**
	 * method to add the sum of each case of which the prefix's match
	 * @param String prefix Prefix of which to match and get all the sums
	 */
	@Override
	public long sum(String prefix) {
		prefix = prefix.trim();
		long sum = 0;
		String first = prefix.substring(0, 1);
    	String rest = prefix.substring(1,prefix.length());
    	map1 = map.get(first);
		for(Map.Entry<String, Integer> entry : map1.entrySet())
		{
			if(entry.getKey().startsWith(rest))
				sum += entry.getValue();
		}
		return sum; 
	}

}
