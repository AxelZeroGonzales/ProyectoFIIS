package pe.edu.unac.app;

import java.util.List;

import pe.edu.unac.dao.CountryDAO;
import pe.edu.unac.model.Country;

public class HelloWorld {

	public static void main(String[] args) {
		
		List<Country> listCountry = CountryDAO.getListCountry();

		for (int i=0; i < listCountry.size(); i++ ) {
			System.out.println(listCountry.get(i).toString());
		}
		
	}
	
}
