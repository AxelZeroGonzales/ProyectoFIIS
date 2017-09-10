package pe.edu.unac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.unac.mapper.CountryMapper;
import pe.edu.unac.model.City;
import pe.edu.unac.model.Country;

public class CityDAO {

	private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    
    private static final String CITY_SELECT_ALL 	= "SELECT * FROM city ci INNER JOIN country co ON ci.country_id = co.country_id";

    public static List<City> getListCity() {
    	
    	List<City> listCity = new ArrayList<>();

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(CITY_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	City city = new City();
            	city.setCityId(resultSet.getInt("city_id"));
            	city.setCity(resultSet.getString("city"));
            	city.setLastUpdate(resultSet.getTimestamp("last_update"));
            	
            	Country country = CountryMapper.mapperCountry(resultSet);
            	city.setCountry(country);
            	listCity.add(city);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listCity;
    }
}
