package com.springboot.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.springboot.model.CovidDataModel;

@Service
public class CovidService {

	public static String DATA_SOURCE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	public ArrayList<CovidDataModel> allDataList = new ArrayList<>();
	public int totalTodayCount = 0, totalNewCount = 0;

	@PostConstruct
	@Scheduled(cron = "* * * * * *")
	public void getData() throws IOException, InterruptedException {
		ArrayList<CovidDataModel> dataList = new ArrayList<>();
		int currentCount, newCount;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(DATA_SOURCE)).build();
		HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
		StringReader in = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		for (CSVRecord record : records) {
			currentCount = Integer.parseInt(record.get(record.size() - 1));
			newCount = currentCount - Integer.parseInt(record.get(record.size() - 2));
			CovidDataModel model = new CovidDataModel();
			model.setProvince(record.get("Province/State"));
			model.setCountry(record.get("Country/Region"));
			model.setCurrentCount(currentCount);
			model.setNewCount(newCount);
			totalTodayCount += currentCount;
			totalNewCount += newCount;
			dataList.add(model);
		}
		allDataList = dataList;
	}

}
