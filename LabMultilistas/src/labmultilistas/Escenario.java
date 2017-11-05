
package labmultilistas;

public class Escenario {
    
    int numero;
    String patrocinador;
    double presupuesto;
    Banda bandas;
    Escenario siguiente;
    double presupuestoRestante;
    int numeroBandas;

    public Escenario(int numero, String patrocinador, double presupuesto) {
	this.numero = numero;
	this.patrocinador = patrocinador;
	this.presupuesto = presupuesto;
	this.presupuestoRestante = presupuesto;
	this.numeroBandas = 0;
    }
    
    public void ordenarBandas() {
	Banda actual = bandas;
	if (actual != null) {
	    Banda ant = null;
	    Banda sig =  actual.siguiente;
	    while (sig != null) {
		if (actual.fans >= sig.fans) {
		    ant = actual;
		    actual = sig;
		    sig = actual.siguiente;
		} else {
		    if (ant == null) {
			bandas = sig;
			actual.siguiente = sig.siguiente;
			sig.siguiente = actual;
			
			actual = bandas;
			sig = actual.siguiente;
		    } else {
			ant.siguiente = sig;
			actual.siguiente = sig.siguiente;
			sig.siguiente = actual;
			
			ant = null;
			actual = bandas;
			sig = actual.siguiente;
		    }
		}
	    }
	}
    }
    
    public boolean busca(String nombre) {
	Banda b = bandas;
	boolean encontrado = false;
	while (b != null && !encontrado) {
	    if (b.nombre.equalsIgnoreCase(nombre)) {
		encontrado = true;
	    }
	    b = b.siguiente;
	}
	return encontrado;
    }
    
    public String generarCostos() {
	String linea1 = "ESCENARIO "+numero+":  -$"+(presupuesto-presupuestoRestante)+"\n";
	String todo = linea1;
	Banda b = bandas;
	while (b != null) {
	    todo = todo + b.generarCostos();
	    b = b.siguiente;
	}
	
	return todo;
    }
    
    public String generarPresupuesto() {
	String todo = "ESCENARIO "+numero+":  $"+presupuesto+"\n";
	return todo;
    }
    
    public String resumen() {
	String asteriscos = "*******************\n";
	String linea1 = "ESCENARIO "+numero+"\n";
	String linea2 = "PATROCINADOR: "+patrocinador+"\n";
	String linea3 = "PRESUPUESTO TOTAL: $"+presupuesto+"\n";
	String linea4 = "PRESUPUESTO RESTANTE: $"+presupuestoRestante+"\n";
	String linea5 = "BANDAS:\n";
	String todo = linea1+linea2+linea3+linea4+linea5;
	Banda b = bandas;
	while (b != null) {
	    todo = todo + asteriscos + b.resumen();
	    b = b.siguiente;
	}
	
	return todo+asteriscos;
    }
}
