package org.tothought.utilities.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tothought.utilities.collections.interfaces.ListConverter;

public class CollectionUtil {

	
	public static <T> Map<String, T> listToMap(List<T> list, ListConverter<T> converter){
		Map<String,T> map = new HashMap<String, T>();
		
		for(T item:list){
			map.put(converter.getKey(item), item);
		}
		
		return map;
	}
}
