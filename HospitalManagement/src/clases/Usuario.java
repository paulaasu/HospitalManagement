


package clases;


public  class Usuario {
	private String nom;
	private String contrasena;
	public Usuario(String nom, String contrasena) {
		super();
		this.nom = nom;
		this.contrasena = contrasena;
	}
	public Usuario() {
		super();
		this.nom = " ";
		this.contrasena = " ";
	}
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
	@Override
	public String toString() {
		return "Usuario nombre =" + nom + ", contrasena=" + contrasena ;
	}
	
	
	
	
	

}

