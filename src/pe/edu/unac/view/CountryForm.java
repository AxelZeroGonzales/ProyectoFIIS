package pe.edu.unac.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pe.edu.unac.dao.CountryDAO;
import pe.edu.unac.model.Country;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CountryForm extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		scrollPane.setBounds(10, 137, 673, 340);
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
		btnMostrarPaises.setBounds(475, 57, 208, 23);
		contentPane.add(btnMostrarPaises);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboBox.setBounds(26, 21, 99, 23);
		contentPane.add(comboBox);
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
