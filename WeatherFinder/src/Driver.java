import java.util.Scanner;

import net.webservicex.GetCitiesByCountry;
import net.webservicex.GetWeather;
import net.webservicex.GetWeatherResponse;
import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			String city,country,weather,output,cities;
			
			Scanner scan=new Scanner(System.in);
		    System.out.println("Enter the country name");
			country=scan.nextLine();
			GlobalWeather globalWeather=new GlobalWeather();
			GlobalWeatherSoap service=globalWeather.getGlobalWeatherSoap();
			cities=service.getCitiesByCountry(country);
			GetCitiesByCountry gcb=new GetCitiesByCountry();	
			// set the country name
			gcb.setCountryName(country);
			// get the country name
			output=gcb.getCountryName();
			System.out.println(cities);
			System.out.println("Using Getter method (country name)");
			System.out.println(output);
		

	}

}
