
package labmultilistas;

public class Banda {
    
    String nombre;
    int fans;
    int canciones;
    double costo;
    Banda siguiente;

    public Banda(String nombre, int fans, int canciones, double costo) {
	this.nombre = nombre;
	this.fans = fans;
	this.canciones = canciones;
	this.costo = costo;
    }
    
    public String resumen() {
	String linea1 = "    BANDA: "+nombre+"\n";
	String linea2 = "    FANS: "+fans+"\n";
	String linea3 = "    CANCIONES: "+canciones+"\n";
	String linea4 = "    COSTO: $"+costo+"\n";
	
	
	return linea1+linea2+linea3+linea4;
    }
    
    public String generarCostos() {
	String linea1 = "  "+nombre+" -$"+costo+"\n";
	return linea1;
    }
}
