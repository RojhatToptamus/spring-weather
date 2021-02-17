package com.spring.weather;

import java.util.*;

public class Root {
		Coord coord;
		List<Weather> weather;		
		Main main;
   		Wind wind;
    	Clouds clouds;
    	long dt;
    	Sys sys;
    	
    	int timezone;
    	int id;
    	int visibility;
    	int cod;
    	String name;
    	String base;

    	
	    public String toString()
	    {
	        return  " City: "+name+" Timezone: "+timezone+weather.toString()+main.toString()+
	        		 coord.toString()+wind.toString()+" Visibility: "+visibility+" Cod: "+cod+" Base: "+base;
	    }
    	
	public Coord getCoord() {
			return coord;
		}

		public List<Weather> getWeather() {
			return weather;
		}

		public String getBase() {
			return base;
		}


		public Main getMain() {
			return main;
		}

		public Wind getWind() {
			return wind;
		}

		public Clouds getClouds() {
			return clouds;
		}

		public long getDt() {
			return dt;
		}


		public Sys getSys() {
			return sys;
		}

		public int getTimezone() {
			return timezone;
		}

		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}

		public int getVisibility() {
			return visibility;
		}

		public int getCod() {
			return cod;
		}

	public class Coord{
		
	    public float lon;
	    public float lat;
	    
	    public String toString()
	    {
	        return  "longitude: "+lon+", Latitude: "+lat+"";
	    }
	    
		public double getLon() {
			return lon;

		}

		public double getLat() {
			return lat;
		}
		    
	}

	public class Weather{
		int id;
	    String main;
	    String description;
	    String icon;
		
	    public String toString()
	    {
	        return  "Description: "+description+", Main: "+main+", Id: "+id+"";
	    }
		

	    public int getId() {
			return id;
		}
	    
		public String getMain() {
			return main;
		}
		
		public String getIcon() {
			return icon;
		}

		public String getDescription() {
			return description;
		}

	}

	public class Main{
	    float temp;
	    double feels_like;
	    float temp_min;
	    float temp_max;
	    public int pressure;
	    public int humidity;
	    
	    public String toString()
	    {
	        return  "Temperature:  "+temp+", Feels Like = "+feels_like+", Min Temp:  "+temp_min+", Max Temp: "+temp_max+" "
	        		+ " Pressure: "+pressure+", Humidity: "+humidity+" ";
	    }
	    
		public float getTemp() {
			return temp;
		}

		public double getFeels_like() {
			return feels_like;
		}

		public float getTemp_min() {
			return temp_min;
		}

		public float getTemp_max() {
			return temp_max;
		}

		public int getPressure() {
			return pressure;
		}
		public int getHumidity() {
			return humidity;
		}
			        
	}

	public class Wind{
	    float speed;
	    int deg;
		public float getSpeed() {
			return speed;
		}

		public int getDeg() {
			return deg;
		}

	    public String toString()
	    {
	        return  "Speed:  "+speed+", Deg: "+deg+" ";
	        
	    }
	    
	    
	}

	public class Clouds{
	    public String toString()
	    {
	        return  "all: = "+all+"";
	    }
		
	    int all;

		public int getAll() {
			return all;
		}

	}

	public class Sys{
		int type;
	    int id;
	    String country;
	    long sunrise;
	    long sunset;
	    
	    public String toString()
	    {
	        return  "Type:  "+type+", Country: "+country+",sunrise:  "+sunrise+",Sunset: "+sunset+" ";
	    }
	
	    public int getType() {
			return type;
		}

		public int getId() {
			return id;
		}

		public String getCountry() {
			return country;
		}

		public long getSunrise() {
			return sunrise;
		}

		public long getSunset() {
			return sunset;
		}

	}
}
