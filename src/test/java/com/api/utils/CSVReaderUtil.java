package com.api.utils;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private CSVReaderUtil() {

	}

	public static <T>Iterator<T> loadCSV(String pathOfCsvFile,Class<T> bean) {

		InputStream irs = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(pathOfCsvFile);
		InputStreamReader reader = new InputStreamReader(irs);
		CSVReader csvReader = new CSVReader(reader);

		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();

		List<T> List = csvToBean.parse();
		System.out.println(List);
		return List.iterator();
	}

}
