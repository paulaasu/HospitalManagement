package paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import clases.BaseDeDatos;
import clases.Cita;
import clases.TipoCita;
import paneles.PanelPacientes.Panel4;
import paneles.PanelPacientes.PanelAbajo;
import paneles.PanelPacientes.PanelArriba;
import vistas.VentanaCitas;
import vistas.VentanaPaciente;

public class PanelCitas extends JPanel {
	//nuevo
	private static Connection con;
	private JTable tabla;
	private static DefaultTableModel modeloTabla;
	public TreeMap<String, Cita>tmCitas = new TreeMap<>();
	static SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
	Boolean reiniciarBD;

	public PanelCitas() {
		setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JLabel datos = new JLabel("CITAS");
		datos.setHorizontalAlignment(SwingConstants.CENTER);
		datos.setFont(new Font("Sherif", Font.PLAIN, 24));
		setBackground(new Color(176, 196, 222));
		add(datos, BorderLayout.NORTH);
		Panel4 panel4 = new Panel4();
		add(panel4, BorderLayout.CENTER);
		//hacemos la conexion con la BD
		con = BaseDeDatos.initBD("BaseDeDatos.db");
		BaseDeDatos.crearTablas(con);
		BaseDeDatos.closeBD(con);
		cargarModeloTabla();
		tmCitas = new TreeMap<>();
	}
	class Panel4 extends JPanel { //AQUI ESTA EL GRIDLAYOUT DEL CENTRO
		public Panel4() {
			setLayout(new GridLayout(2,1));
			PanelArriba panelarriba = new PanelArriba();
			add(panelarriba);
			PanelAbajo panelabajo = new PanelAbajo();
			add(panelabajo);
		}
	}
	class PanelArriba extends JPanel{
		public PanelArriba() {
			setLayout(new BorderLayout());
			setBackground(Color.BLUE);
			//hacemos la conexion con la BD
			con = BaseDeDatos.initBD("BaseDeDatos.db");
			BaseDeDatos.crearTablas(con);
			BaseDeDatos.closeBD(con);
	
			Object columnas[] = {"Dni","Nombre","Apellido","Fecha y Hora","Tipo cita"};
			modeloTabla = new DefaultTableModel();
			modeloTabla.setColumnIdentifiers(columnas);
			cargarModeloTabla();
			tabla = new JTable(modeloTabla);
			
			
			JScrollPane scrollTabla = new JScrollPane(tabla);
			add(scrollTabla);
			
			tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
				
				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					if(row==0) {
						c.setBackground(Color.WHITE);
					}else {
						String nombreFilaActual = (String) modeloTabla.getValueAt(row, 0);
						String nombreFilaAnterior = (String) modeloTabla.getValueAt(row-1, 0);
						if(nombreFilaActual.equals(nombreFilaAnterior)) {
							c.setBackground(Color.WHITE);
						}else {
							c.setBackground(Color.LIGHT_GRAY);
						}
					}
					return c;
				}
			});
		}
	}
	
	public class PanelAbajo extends JPanel{
		private JTextField buscar;
		private JTextField añadir;
		private JButton botonBuscar;
		private JButton botonAñadir;
		private JButton botonBorrar;
		private JButton btnCrearFichero;
		public PanelAbajo() {
			setLayout(new FlowLayout());
		//	setBackground(Color.BLUE);
			
			JPanel PanelBuscar = new JPanel();
			PanelBuscar.setLayout(new GridLayout(4, 1));
			PanelBuscar.add(new JLabel("Busar cita..."));
			buscar = new JTextField();
			PanelBuscar.add(buscar);
			botonBuscar = new JButton("Buscar");
			PanelBuscar.add(botonBuscar);
			add(PanelBuscar);
			JFrame f= new JFrame();
			f.addWindowListener( new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					if (new File("BaseDeDatos.db").exists()) {
						// Poner el parÃ¡metro a true si se quiere reiniciar la base de datos
						BaseDeDatos.initBD( "BaseDeDatos.db" );  // Abrir base de datos existente
					} else {
						BaseDeDatos.initBD( "BaseDeDatos.db");  // Crear base de datos con datos iniciales
					}
					// BaseDeDatos.volcarJTableATablaCita(con, modeloTabla);// SegÃºn se inicia la ventana se visualizan los productos
				}
				@Override
				public void windowClosed(WindowEvent e) {
					BaseDeDatos.closeBD(con);;
				}
			});
			botonBuscar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String nombre= buscar.getText() ;
				//nuevo
					try {
						con = BaseDeDatos.initBD("BaseDeDatos.db");
						String sentSQL = "SELECT * FROM Cita WHERE Nombre = '" + nombre + "' ";
						Statement stmt = BaseDeDatos.con.createStatement();
						ResultSet rs = stmt.executeQuery(sentSQL);
						
						if(BaseDeDatos.rs.next()) {
							int rowCount = modeloTabla.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
								modeloTabla.removeRow(i);
							}
							
							
							Object[] fila = new Object[5]; // hay 4 columnas en la tabla cita
							//se rellena cada posición del array con una de las columnas de la tabla de bd
							for (int i=0; i<5; i++) {
								fila[i] = BaseDeDatos.rs.getObject(i+1);
							}
							modeloTabla.addRow(fila);
						}else {
							JOptionPane.showMessageDialog(PanelBuscar, "Dni incorrecto, no existe en la base de datos");
							int rowCount =modeloTabla.getRowCount();
							//Elimina las filas uno a uno desde el final de la tabla
							for (int i = rowCount - 1; i >= 0; i--) {
								modeloTabla.removeRow(i);
							}
							ArrayList<Cita> a = new ArrayList<>();
							BaseDeDatos.obtenerTodasLasCitasOrdenadasPorNombreDni(con,a);
						}					
							BaseDeDatos.closeBD(con);
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
				});
			
			JPanel PanelVacio = new JPanel();
			PanelVacio.setLayout(new GridLayout(1, 1));
			PanelVacio.add(new JLabel(""));
			add(PanelVacio);
			
			JPanel PanelAñadir = new JPanel();
			PanelAñadir.setLayout(new GridLayout(2, 1));
			PanelAñadir.add(new JLabel("Añadir cita..."));
			botonAñadir = new JButton("Añadir");
			PanelAñadir.add(botonAñadir);
			add(PanelAñadir);
			VentanaCitas ventanaCitas = new VentanaCitas();
			botonAñadir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ventanaCitas.setVisible(true);
						
					}
				});
			
			
			JPanel PanelBorrar = new JPanel();
			PanelBorrar.setLayout(new GridLayout(2, 1));
			PanelBorrar.add(new JLabel("Borrar paciente..."));
			botonBorrar = new JButton("Borrar");
			PanelBorrar.add(botonBorrar);
			add(PanelBorrar);
			//nuevo 4/01
			botonBorrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int filaSeleccionada = tabla.getSelectedRow();
					if(filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(null, "Primero debes seleccionar una fila de la tabla");
					}else {
						modeloTabla.removeRow(filaSeleccionada);
						con = BaseDeDatos.initBD("BaseDeDatos.db");
						BaseDeDatos.eliminarCita(con);
						eliminaTablaCita();
						PanelCitas.cargarModeloTabla();
						BaseDeDatos.closeBD(con);
					}
				}
			});
			
			JPanel Panelfichero = new JPanel();
			Panelfichero.setLayout(new GridLayout(2, 1));
			btnCrearFichero = new JButton("CREAR FICHERO");
			JLabel lblcrearfichero = new JLabel();
			Panelfichero.add(btnCrearFichero);
			add(Panelfichero);
			btnCrearFichero.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
						crearFicheroDeTexto();
					
				}
			});
				
			}
		
		
		}
	
	public static  void eliminaTablaCita(){
		int rowCount = modeloTabla.getRowCount();
		//Elimina las filas uno a uno desde el final de la tabla
		for (int i = rowCount - 1; i >= 0; i--) {
			modeloTabla.removeRow(i);
		}
		
	}
	
			
//	public static  void actualizarTablaCita() throws SQLException {
//		
//		Connection con;
//			//nuevo
//		
//			con = BaseDeDatos.initBD("BaseDeDatos.db");
//			BaseDeDatos.volcarJTableATablaCita(con ,modeloTabla);
//			BaseDeDatos.closeBD(con);
//		
//			System.out.println("No se puede rellenar la tabla");
//			
//		while(modeloTabla.getRowCount()>0)
//			modeloTabla.removeRow(0);
//		for(Cita c: a) {
//			System.out.println(c);
//			String [] fila = {c.getDni(),c.getNombre(),c.getApellidos(),sdf.format(c.getFechaYHoraCita()),String.valueOf(c.getTipodecita())};
//			modeloTabla.addRow(fila);
//		}
		
	//}
		private String obtenerTexto() {
			String texto = "INFORMACIÓN DE LAS CITAS\n";
			for(String clave: tmCitas.keySet()) {
				Cita valor = tmCitas.get(clave);
				texto = texto + clave + "\n";
			}
			return texto;
		}
		
	
	private void crearFicheroDeTexto() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		long milis = System.currentTimeMillis();
		Date fecha = new Date(milis);
		String f = sdf.format(fecha);
		
		PrintWriter pw = null;
		/*
		 * Si el fichero Matriculas.txt no existe, lo crea y lo abre
		 * Si el fichero Matriculas.txt sí existe, lo abre y borra su contenido
		 * */
		try {
			String nomfich = JOptionPane.showInputDialog("Introduce el nombre del fichero: ");
			nomfich = nomfich + f +".txt";
			//pw = new PrintWriter("Matriculas.txt");
			pw = new PrintWriter(nomfich);
			String texto = obtenerTexto();
			pw.println(texto);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
	}
	//posible
	public static  void cargarModeloTabla() {
		con = BaseDeDatos.initBD("BaseDeDatos.db");
	
		ArrayList<Cita> a = new ArrayList<>();
		try {
			BaseDeDatos.obtenerTodasLasCitasOrdenadasPorNombreDni(con, a);
			//System.out.println(a.get(0).getDni());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseDeDatos.closeBD(con);
		while(modeloTabla.getRowCount()>0)
			modeloTabla.removeRow(0);
		for(Cita c: a) {
			String f = sdf1.format(c.getFechaYHoraCita());
			String fila[] = {c.getDni(),c.getNombre(),c.getApellidos(),f,c.getTipodecita().toString()};
			System.out.println(c.getDni());
			modeloTabla.addRow(fila);
		}
		
	}

}
