package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String filename,Class<T[]> classobj) {
    //"testData/demo.json"
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);

		ObjectMapper   objectMapper= new ObjectMapper();
		T[] classArray;
		List<T> listObj = null;
		try {
			classArray = objectMapper.readValue(is, classobj);
			listObj	=Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return	listObj.iterator();







	}

}
