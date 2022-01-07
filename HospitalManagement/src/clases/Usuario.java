package clases;


public  class Usuario {
	private String nom;
	private String contrasena;
	private int ID_usuario;
	private TipoUsuario rol;

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
		
	}
	public int getID_usuario() {
		return ID_usuario;
	}
	public void setID_usuario(int iD_usuario) {
		ID_usuario = iD_usuario;
	}
	public TipoUsuario getRol() {
		return rol;
	}
	public void setRol(TipoUsuario rol) {
		this.rol = rol;
	}
	
	public Usuario(String nom, String contrasena, int iD_usuario, TipoUsuario rol) {
		super();
		this.nom = nom;
		this.contrasena = contrasena;
		ID_usuario = iD_usuario;
		this.rol = rol;
	}
	
	public Usuario() {
		super();
		this.nom = "";
		this.contrasena = "";
		ID_usuario = 0;
		this.rol = TipoUsuario.MEDICO;
	}
	
	@Override
	public String toString() {
		return "Usuario [nom=" + nom + ", contrasena=" + contrasena + ", ID_usuario=" + ID_usuario + ", rol=" + rol
				+ "]";
	}
	
	
	
	
	

}

