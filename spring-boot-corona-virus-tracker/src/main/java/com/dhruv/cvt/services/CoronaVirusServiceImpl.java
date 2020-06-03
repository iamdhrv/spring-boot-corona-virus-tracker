package com.dhruv.cvt.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dhruv.cvt.model.LocData;

@Service
public class CoronaVirusServiceImpl implements CoronaVirusService {

	private final static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv"; 
	
	private List<LocData> dataList = new ArrayList<>();
	
	@Override
	public List<LocData> getAllData() {
		return dataList;
	}

	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException{
		List<LocData> newList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		URLConnection urlcon =  new URL(DATA_URL).openConnection();

		try(BufferedReader rd = new BufferedReader(new InputStreamReader(
		        urlcon.getInputStream()))){
			StringReader csvReader = new StringReader(rd.toString());
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(rd);
			for (CSVRecord record : records) {
	            LocData locData = new LocData();
	            locData.setState(record.get("Province/State"));
	            locData.setCountry(record.get("Country/Region"));
	            int latestCases = Integer.parseInt(record.get(record.size() - 1));
	            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
	            locData.setTotalCases(latestCases);
	            locData.setDiffFromPrevDay(latestCases - prevDayCases);
	            newList.add(locData);
	        }
	        this.dataList = newList;
			
		}
		catch (Exception e) {
			System.out.println("Exception"+e.getMessage());
		}
		
		
	}
	
	

}
