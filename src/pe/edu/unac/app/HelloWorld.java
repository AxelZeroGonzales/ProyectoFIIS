package pe.edu.unac.app;

import java.util.List;

import pe.edu.unac.dao.CountryDAO;
import pe.edu.unac.model.Country;

public class HelloWorld {

	public static void main(String[] args) {
		
		List<Country> listCountry = CountryDAO.getListCountry();

		System.out.println(listCountry.toString());
	}
	
}
