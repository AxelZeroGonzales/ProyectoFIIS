package pe.edu.unac.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import pe.edu.unac.dao.CountryDAO;
import pe.edu.unac.dao.FactoryDAO;

public abstract class AbstractReport {

	@SuppressWarnings("unused")
	private Connection connection;
	protected String jrxml;
    protected String title;
	
    public static final int COUNTRY = 0;
    
    public String viewReport() {
    	
   // 	String path = System.getProperty("user.dir") +"\\src\\pe\\edu\\unac\\report\\jrxml\\";
   // 	String path = getClass().getResource("/pe/edu/unac/report/jrxml/").toString();
    	InputStream pathJRXml = getClass().getResourceAsStream("/pe/edu/unac/report/jrxml/" + jrxml);
   // 	String pathJRXml = path + jrxml;
    	
//    	JasperReport jasperReport;
    	JasperPrint jasperPrint;
    	
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	
    	try {
    		connection = FactoryDAO.connection();
    		
    	//	jasperReport = JasperCompileManager.compileReport(pathJRXml);
    		jasperPrint = JasperFillManager.fillReport(pathJRXml, parameters, new JRBeanCollectionDataSource(CountryDAO.getListCountry()));
						
    		JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "OK";
    }
    
}
