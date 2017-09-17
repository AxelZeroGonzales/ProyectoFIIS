package pe.edu.unac.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pe.edu.unac.controller.CountryController;
import pe.edu.unac.dao.CityDAO;
import pe.edu.unac.dao.CountryDAO;
import pe.edu.unac.model.City;
import pe.edu.unac.model.Country;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class CountryForm extends JFrame {

	private static final long serialVersionUID = 4664524955165344153L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<Country> cbCountry;
	private JComboBox<City> cbCity;
	private JLabel lblPathJar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CountryForm frame = new CountryForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CountryForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 299, 673, 178);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Nombre", "Ult. Actualizaci\u00F3n"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnMostrarPaises = new JButton("Mostrar Paises");
		btnMostrarPaises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarTabla();
			}
		});
		btnMostrarPaises.setBounds(475, 260, 208, 23);
		contentPane.add(btnMostrarPaises);
		
		cbCountry = new JComboBox<Country>();
		cbCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initComboCity((Country) cbCountry.getSelectedItem());
			}
		});
		
		cbCountry.setBounds(10, 57, 126, 23);
		contentPane.add(cbCountry);
		
		cbCity = new JComboBox<City>();
		cbCity.setBounds(146, 57, 126, 23);
		contentPane.add(cbCity);
		
		JLabel lblPais = new JLabel("PAIS");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPais.setBounds(10, 32, 99, 14);
		contentPane.add(lblPais);
		
		JLabel lblCiudad = new JLabel("CIUDAD");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCiudad.setBounds(146, 32, 46, 14);
		contentPane.add(lblCiudad);
		
		JButton btnReportCountry = new JButton("Report Country");
		btnReportCountry.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent arg0) {
				String rpta = CountryController.viewReport();
				lblPathJar.setText(getClass().getResource("/pe/edu/unac/report/jrxml/").toString());
			}
		});
		btnReportCountry.setBounds(10, 119, 262, 23);
		contentPane.add(btnReportCountry);
		
		lblPathJar = new JLabel("");
		lblPathJar.setIcon(new ImageIcon(CountryForm.class.getResource("/pe/edu/unac/report/jrxml/screenshot-www.twitch.tv-2017-09-10-14-16-48.png")));
		lblPathJar.setBounds(54, 153, 233, 137);
		contentPane.add(lblPathJar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(407, 103, 46, 14);
		contentPane.add(lblNewLabel);
		
		initComboCountry();
	}
	
	private void initComboCountry(){
		
		cbCountry.addItem(new Country(0, "SELECCIONE"));
		for(Country country : CountryDAO.getListCountry()) {
			cbCountry.addItem(country);
		}
	}
	
	private void initComboCity(Country _country){
		cbCity.removeAllItems();
		cbCity.addItem(new City(0, "SELECCIONE"));
		for(City city : CityDAO.getListCity(_country)) {
			cbCity.addItem(city);
		}
	}
	
	private void cargarTabla() {
		
		String[] title = {"Id", "Nombre", "Ult. Actualización"};
		String[] data = new String[3];
		
		DefaultTableModel defaultTableModel = new DefaultTableModel(null, title);
		
		List<Country> listCountry = CountryDAO.getListCountry();
		
		for (int i=0; i <listCountry.size() ; i++) {
			
			data[0] = listCountry.get(i).getCountryId().toString();
			data[1] = listCountry.get(i).getCountry();
			data[2] = listCountry.get(i).getLastUpdate().toString();
			
			defaultTableModel.addRow(data);
		}
		
		table.setModel(defaultTableModel);
		
		
	}
}
