package prefix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to return the sum of the dictionary values in a CSV file
 * @author Dan
 */
public class FastPrefixDictionary implements PrefixDictionary{

	//HashMap of the First letters
	Map<String, Map<String, Integer>> map;
	//HashMap of the rest of the string
	Map<String, Integer> map1;
	
	/**
	 * Constructor to make a hash of the filename
	 * @param fileName name of the file of which to parse into a hash
	 */
	public FastPrefixDictionary(String fileName){
		//create the map of the first letter
		map = new HashMap<String,Map<String, Integer>>();
		try {
			//read the file
		    BufferedReader file = new BufferedReader(new FileReader(fileName));
		    String line;
		    String[] lineList;
		    //while the file is not empty
		    while((line = file.readLine()) != null) {
		    	lineList = line.split(",");
		    	String key = lineList[0].trim();
		    	String first = key.substring(0, 1);
		    	String rest = key.substring(1,key.length());
		    	//if the letter does not exists in the map
		    	if(!map.containsKey(first)){
		    		//create that map
		    		map1 = new HashMap<String, Integer>();
		    		//add the map
		    		map.put(first, map1);
		    		//add the rest of it
		    	  	map1.put(rest, Integer.parseInt(lineList[1].trim()));
		    	}else{
		    		//get that letters map
		    		map1 = map.get(first);
		    		//add the rest of it
		    		map1.put(rest, Integer.parseInt(lineList[1].trim()));
		    	}
		    		
		    }
		    //close file
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
		//trim the prefix
		prefix = prefix.trim();
		long sum = 0;
		//get the first letter
		String first = prefix.substring(0, 1);
		//get the rest of the string
    	String rest = prefix.substring(1,prefix.length());
    	//get that first letters map
    	map1 = map.get(first);
    	//add the rest of the prefix's numerical values
		for(Map.Entry<String, Integer> entry : map1.entrySet())
		{
			//if the entry matches the rest of the prefix add it to sum
			if(entry.getKey().startsWith(rest))
				sum += entry.getValue();
		}
		//return the sum 
		return sum; 
	}

}
