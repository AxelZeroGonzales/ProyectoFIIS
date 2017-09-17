package pe.edu.unac.report;

import static pe.edu.unac.report.AbstractReport.*;

public class FactoryReport {

	public static String generarReporte(int tipo)
    {
        AbstractReport report;
        switch(tipo)
        {
            case COUNTRY:
            	report = new CountryReport();
                break;
            default:
                return "Debe seleccionar una entidad registrada en la estrategia.";
        }      
        return report.viewReport();
    }
	
}
