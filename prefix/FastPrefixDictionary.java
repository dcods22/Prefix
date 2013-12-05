package prefix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * Class to create a hash of the String and Integer values in a dictonary
 * @author Dan
 *
 */
public class FastPrefixDictionary implements PrefixDictionary{

	HashMap<String, Integer> hash;
	
	/**
	 * Constructor to make a hash of the filename
	 * @param fileName name of the file of which to parse into a hash
	 */
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
	
	/**
	 * method to add the sum of each case of which the prefix's match
	 * @param String prefix Prefix of which to match and get all the sums
	 */
	@Override
	public long sum(String prefix) {
		prefix = prefix.trim();
		long sum = 0;
		for(String key : hash.keySet())
		{
			if(key.startsWith(prefix))
				sum += hash.get(key);
		}
		return sum;
	}

}
