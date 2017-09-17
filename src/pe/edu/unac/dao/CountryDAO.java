package pe.edu.unac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.unac.mapper.CountryMapper;
import pe.edu.unac.model.Country;

public class CountryDAO {
	
	private static Connection connection;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    private static final String COUNTRY_CREATE = "INSERT INTO country VALUES (null,?,null)";
    private static final String COUNTRY_READ = "SELECT * FROM country WHERE country_id = ?";
    private static final String COUNTRY_UPDATE = "UPDATE country SET country = ? WHERE country_id = ?";
    private static final String COUNTRY_DELETE = "DELETE FROM country WHERE country_id = ?";
    private static final String COUNTRY_SELECT_ALL = "SELECT * FROM country";
    private static final String COUNTRY_SELECT_FILTER = "SELECT * FROM country WHERE country LIKE ?";

    public static Integer createCountry(Country country) {

        Integer response = 0;

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_CREATE);
            preparedStatement.setString(1, country.getCountry());
            response = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    public static Country readCountry(Integer country_id) {

        Country country = new Country();

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_READ);
            preparedStatement.setInt(1, country_id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                country = CountryMapper.mapperCountry(resultSet);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return country;
    }

    public static Integer updateCountry(Country country) {
        Integer response = 0;

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_UPDATE);
            preparedStatement.setString(1, country.getCountry());
            preparedStatement.setInt(2, country.getCountryId());
            response = preparedStatement.executeUpdate();
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

        return response;
    }

    public static Integer deleteCountry(Integer country_id) {

        Integer response = 0;

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_DELETE);
            preparedStatement.setInt(1, country_id);
            response = preparedStatement.executeUpdate();
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

        return response;
    }

    public static List<Country> getListCountry() {

        List<Country> listCountry = new ArrayList<>();

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_SELECT_ALL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCountry.add(CountryMapper.mapperCountry(resultSet));
            }
            System.out.println("DAO COUNTRY");
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

        return listCountry;
    }

    public static List<Country> getListCountry(String country) {

        List<Country> listCountry = new ArrayList<>();

        try {
            connection = FactoryDAO.connection();
            preparedStatement = connection.prepareStatement(COUNTRY_SELECT_FILTER);
            preparedStatement.setString(1, country);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listCountry.add(CountryMapper.mapperCountry(resultSet));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listCountry;
    }

	
}
