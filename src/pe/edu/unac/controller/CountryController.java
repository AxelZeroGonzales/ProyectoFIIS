package pe.edu.unac.controller;

import pe.edu.unac.report.AbstractReport;
import pe.edu.unac.report.FactoryReport;

public class CountryController {
	
	public static String viewReport() {
        return FactoryReport.generarReporte(AbstractReport.COUNTRY);
    }
}
