package com.spring.weather;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.spring.weather.Root.Weather;
import com.sun.el.stream.Stream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {
	private OkHttpClient client;
	private Response response;
	private String cityName;

	public WeatherService() {
	}

	public WeatherService(OkHttpClient client, Response response, String cityName) {
		super();
		this.client = client;
		this.response = response;
		this.cityName = cityName;
		
	}
	
	public Root getWeather() {
		client = new OkHttpClient();
		Request request = new Request.Builder()
				.url("http://api.openweathermap.org/data/2.5/weather?q="+getCityName()+"&units=metric&appid=e472c5582117057e7d81f9fe63881dd7")
						.build();
				try {
					response = client.newCall(request).execute();
					String res = response.body().string().toString();
					Gson gson = new Gson();
					Root root = gson.fromJson(res, Root.class);
					return root;			
				
				}
				catch(IOException  | JSONException e) {
					e.printStackTrace();
				}
				return null;
	}

	public Root findDatas() {
		Root rootResult=new Root();
		rootResult=getWeather();
		return rootResult;
		
	}

	public OkHttpClient getClient() {
		return client;
	}

	public void setClient(OkHttpClient client) {
		this.client = client;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


}
