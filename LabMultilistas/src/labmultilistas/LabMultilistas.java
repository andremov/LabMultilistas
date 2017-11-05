
package labmultilistas;

public abstract class LabMultilistas {
    
    static Escenario escenarios;
    static int numeroEscenarios;
    
    public static String resumen() {
	Escenario e = escenarios;
	String todo = "";
	while (e != null) {
	    todo = todo + e.resumen();
	    e = e.siguiente;
	}
	return todo;
    }
    
    public static boolean nuevoEscenario(int numero, String patrocinador, double presupuesto) throws Exception {
	Escenario temp1 = new Escenario(numero, patrocinador, presupuesto);
	boolean exito = false;
	if (numeroEscenarios < 5) {
	    boolean puedeAgregar=true;
	    try {
		escenarioNumero(numero);
		puedeAgregar = false;
	    } catch (Exception e) { }
	    if (puedeAgregar) {
		if (escenarios == null) {
		    escenarios = temp1;
		} else {
		    Escenario temp2 = escenarios;
		    while(temp2.siguiente != null) {
			temp2 = temp2.siguiente;
		    }
		    temp2.siguiente = temp1;
		}
		numeroEscenarios = numeroEscenarios + 1;
		exito = true;
	    } else {
		throw new Exception("Ya existe un escenario con ese numero!");
	    }
	} else {
	    throw new Exception("Numero maximo de escenarios alcanzado!");
	}
	return exito;
    }
    
    public static boolean nuevaBanda(int numeroEscenario, String nombre, int fans, int canciones, double costo) throws Exception {
	Banda temp1 = new Banda(nombre, fans, canciones, costo);
	Escenario escenario = escenarioNumero(numeroEscenario);
	boolean exito = false;
	if (escenario.numeroBandas < 10) {
	    if (escenario.presupuestoRestante >= temp1.costo) {
		if (escenario.bandas == null) {
		    escenario.bandas = temp1;
		} else {
		    Banda temp2 = escenario.bandas;
		    while(temp2.siguiente != null) {
			temp2 = temp2.siguiente;
		    }
		    temp2.siguiente = temp1;
		}
		escenario.presupuestoRestante = escenario.presupuestoRestante - temp1.costo;
		escenario.numeroBandas = escenario.numeroBandas + 1;
		escenario.ordenarBandas();
		exito = true;
	    }  else {
		throw new Exception("No hay suficiente presupuesto para esa banda!");
	    }
	} else {
	    throw new Exception("Numero maximo de bandas alcanzado!");
	} 
	return exito;
    }
    
    public static Escenario escenarioNumero(int numeroEscenario) throws Exception {
	Escenario temp = escenarios;
	Escenario retorno = null;
	while (temp != null && retorno == null) {
	    if (temp.numero == numeroEscenario) {
		retorno = temp;
	    } else {
		temp = temp.siguiente;
	    }
	}
	if (retorno == null) {
	    throw new Exception("No existe escenario con el numero dado!");
	}
	return retorno;
    }
    
    public static String generarCostos() {
	Escenario e = escenarios;
	String todo = "";
	todo = todo + "PRESUPUESTO:\n";
	String nada = "\n";
	while (e != null) {
	    todo = todo + e.generarPresupuesto();
	    e = e.siguiente;
	}
	todo = todo + nada;
	double presupuesto = 0;
	double gastos = 0;
	e = escenarios;
	todo = todo + "GASTOS:\n";
	while (e != null) {
	    todo = todo + e.generarCostos();
	    presupuesto = presupuesto + e.presupuesto;
	    gastos = gastos + (e.presupuesto - e.presupuestoRestante);
	    e = e.siguiente;
	}
	String total1 = "Presupuesto Total: $"+presupuesto+"\n";
	String total2 = "Gastos Totales: -$"+gastos+"\n";
	String total3 = "Presupuesto Restante Total: $"+(presupuesto-gastos)+"\n";
	todo = todo + nada + total1 + total2 + total3;
	
	return todo;
    }
    
    public static int busca(String nombre) throws Exception {
	Escenario e = escenarios;
	int num = -1;
	while (e != null && num == -1) {
	    if (e.busca(nombre)) {
		num = e.numero;
	    }
	    e = e.siguiente;
	}
	if (num == -1) {
	    throw new Exception("No se encontró la banda!");
	}
	return num;
    }
    
}
