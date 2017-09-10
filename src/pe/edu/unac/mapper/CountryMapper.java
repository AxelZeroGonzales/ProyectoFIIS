package pe.edu.unac.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import pe.edu.unac.model.Country;

public class CountryMapper {

	public static Country mapperCountry(ResultSet resultSet) throws SQLException {
        Country country = new Country();

        country.setCountryId(resultSet.getInt("country_id"));
        country.setCountry(resultSet.getString("country"));
        country.setLastUpdate(resultSet.getTimestamp("last_update"));

        return country;
    }
}
