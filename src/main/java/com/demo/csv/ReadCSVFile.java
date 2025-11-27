package com.demo.csv;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;





public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {
		
		// Read Data from Csv File in java!!!!
		
		//File csvFile= new File("C:\\Users\\umesh\\git\\repository\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\loginCredits.csv");
		//FileReader fr= new FileReader(csvFile);
		String csvdata=null;
		InputStream irs= Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCredits.csv");
		InputStreamReader reader= new InputStreamReader(irs);
		CSVReader csvReader= new CSVReader(reader);
		
List<String[]>	dataList=	csvReader.readAll();

     // System.out.print(dataList);
//open new file csv
      for(String[]dataArray :dataList)
      {
    	System.out.println(dataArray[0]);
    	System.out.println(dataArray[1]);
      }
		
		

	}

}
