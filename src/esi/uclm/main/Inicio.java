package esi.uclm.main;

import esi.uclm.maze.Frontera;
import esi.uclm.maze.NodoArbol;
import esi.uclm.maze.Problema;
import esi.uclm.maze.Sucesor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/*****************************************************************************
 * 
 * Class Name: Inicio
 * Author/s Name: Antonio, Luis y Teresa
 * Description of the class: En esta clase se realizan las diferentes pruebas 
 * para comprobar el correcto funcionamiento.
 * 
 *****************************************************************************/
public class Inicio {
    
    //Atributos
    private final static int PROF_MAX = 1000000;
    private static final String RUTA_FUENTES = "./ejemplos/";
    private static final String RUTA_SOLUCION = "./soluciones/";
    private static final String [] FICHEROS = {"problema_5x5.json", "problema_10x10.json", "problema_25x25.json", "problema_25x50.json", "problema_50x25.json", "problema_50x50.json", "problema_100x100.json"};
    private static final String [] FICHEROS_SOL = {"sol_5x5_", "sol_10x10_", "sol_25x25_", "sol_25x50_", "sol_50x25_", "sol_50x50_", "sol_100x100_"};
    
    /*****************************************************************************
    * 
    * Method Name: main
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: se encarga de crear las búsquedas con los problemas 
    * proporcionados usando las distintas estrategias de búsqueda(BREADTH, DEPTH,
    * UNIFORM, GREEDY, A)
    * 
    * Además se comprueba de forma automática si el problema en esa estrategia da 
    * la misma solución o no.
    * 
    *****************************************************************************/ 
    public static void main(String[] args) {
        String [] estrategias = {"BREADTH", "DEPTH", "UNIFORM", "GREEDY", "A"};
        
        for (String fichero : FICHEROS) {
            for (String estrategia : estrategias) {
                busqueda(new Problema(RUTA_FUENTES + fichero), estrategia, PROF_MAX);
            }
        } 
        
        for (int i = 0; i < FICHEROS_SOL.length; i++) {
            for (String estrategia : estrategias) {
                if (comprobarFicherosIguales(FICHEROS_SOL[i], estrategia)) {
                    System.out.println("El problema " + FICHEROS[i] + " con la estrategia " + estrategia + " da la misma solución");
                } else {
                    System.out.println("El problema " + FICHEROS[i] + " con la estrategia " + estrategia + " NO da la misma solución");
                }
            }
        }
    }
    
    /*****************************************************************************
    * 
    * Method Name: búsqueda
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: proporcionando por parámetros el problema, la estrategia,
    * y la profundidad máxima de realizar la búsqueda. Devuelve si el problema tiene 
    * solución o no.
    * 
    *****************************************************************************/ 
    public static boolean busqueda(Problema prob, String estrategia, int Prof_Max) {
        NodoArbol.setContadorId(0);
        
        //Se crea la frontera vacia
        Frontera frontera = new Frontera();
        frontera.crearFronteraVacia();
        
        //Se crea el nodo inicial y se inserta en la frontera
        NodoArbol n_inicial = new NodoArbol(null, prob.getEstadoInicial(), 0.0, null, 0, 0.0);
        switch (estrategia) {
            case "DEPTH":
                n_inicial.setF(1/(n_inicial.getP() + 1));
                break;
            case "GREEDY":
                n_inicial.setF(prob.getEstadoInicial().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                break;
            case "A":
                n_inicial.setF(prob.getEstadoInicial().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                break;
        }
        
        frontera.insertar(n_inicial);
         
        //Atributos necesarios para la busqueda
        boolean solucion = false;
        NodoArbol n_actual = null;
        Map<String, Double> nodosVisitados = new HashMap(); // HashMap para la poda de estados repetidos
        Deque<NodoArbol> nodosSolucion = new LinkedList(); // cola doble para almacenar la solución generada

        while (!solucion && !frontera.estaVacia()) {
            n_actual = frontera.eliminar();

            if (prob.esObjetivo(n_actual.getEstado())) {
                solucion = true;
            } else if (!nodosVisitados.containsKey(n_actual.getEstado().getID()) && n_actual.getP() < PROF_MAX) {
                nodosVisitados.put(n_actual.getEstado().getID(), n_actual.getF());
                
                List<Sucesor> LS = prob.getEspacioDeEstados().getSucesores(n_actual.getEstado());
                List<NodoArbol> LN = CreaListaNodosArbol(prob, LS, n_actual, PROF_MAX, estrategia);
                
                LN.forEach((nodo) -> {                
                    frontera.insertar(nodo);
                });
            }
        }
        
        if (solucion) {
            //Si encontramos solucion la introducimos en la cola doble nodosSolucion            
            while (n_actual.getPadre() != null) {
                nodosSolucion.addFirst(n_actual);
                n_actual = n_actual.getPadre();
            }
            
            //Se inserta el nodo inicial y se genera el fichero en la carpeta de la solución
            nodosSolucion.addFirst(n_inicial);
            generarFichero(prob, nodosSolucion, estrategia);
        }
        
        return solucion;
    }
    
    /*****************************************************************************
    * 
    * Method Name: CreaListaNodosArbol
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: crea una lista de nodos arbol a partir de los parámetros
    * proporcionados(problema, lista de sucesores, n_ actual, profundidad máxima y la 
    * estrategia a seguir.
    * 
    *****************************************************************************/ 
    public static List<NodoArbol> CreaListaNodosArbol(Problema prob, List<Sucesor> LS, NodoArbol n_actual, int Prof_Max, String estrategia) {
        List<NodoArbol> LN = new ArrayList();
        if (n_actual.getP() < Prof_Max) { // Si aún podemos seguir iterando por no alcanzar la profundidad máxima
            NodoArbol aux = null;
            for (Sucesor sucesor : LS) {
                //Dependiendo de la estrategia generamos los nodos
                switch (estrategia) {
                    case "BREADTH":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getP() + 1);
                        break;
                    case "DEPTH":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, 1.0/((double) n_actual.getP() + 2));
                        break;
                    case "UNIFORM":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste());
                        break;
                    case "GREEDY":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, sucesor.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                        break;
                    case "A":
                        aux = new NodoArbol(n_actual, sucesor.getEstado(), n_actual.getCoste() + sucesor.getCoste(), sucesor.getAccion(),
                                n_actual.getP() + 1, n_actual.getCoste() + sucesor.getCoste() + sucesor.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna()));
                        break;
                }
                LN.add(aux);
            }
        }
        return LN;
    }
    
    /*****************************************************************************
    * 
    * Method Name: generaFichero
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: genera un fichero proporcionando al método el problema,
    * el camino y la estrategia a seguir.
    * 
    *****************************************************************************/ 
    public static void generarFichero(Problema prob, Deque<NodoArbol> camino, String estrategia) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); 
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(RUTA_SOLUCION + "sol_" + prob.getEspacioDeEstados().getFilas() + "x" +prob.getEspacioDeEstados().getColumnas()+ "_" + estrategia + ".txt")))) {
            pw.println("[id][cost,state,father_id,action,depth,h,value]");
            int i = 0;
            if (estrategia.equals("DEPTH")) {
                for (NodoArbol nodoarbol : camino) {
                    if (i == 0) {
                        pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + ",None,None," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + nodoarbol.getF() + "]");
                    } else {
                        pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + "," + nodoarbol.getPadre().getID() + "," + nodoarbol.getAccion().getMov() + "," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + df.format(nodoarbol.getF()) + "]");
                    }
                    i++; 
                }
            } else {
                for (NodoArbol nodoarbol : camino) {
                    if (i == 0) {
                        pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + ",None,None," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + Math.abs((int) nodoarbol.getF()) + "]");
                    } else {
                        pw.println("[" + nodoarbol.getID() + "][" + (int) nodoarbol.getCoste() + "," + nodoarbol.getEstado().getID() + "," + nodoarbol.getPadre().getID() + "," + nodoarbol.getAccion().getMov() + "," + nodoarbol.getP() + "," + Math.abs(nodoarbol.getEstado().getHeuristica(prob.getEstadoFinal().getFila(), prob.getEstadoFinal().getColumna())) + "," + Math.abs((int) nodoarbol.getF()) + "]");
                    }
                    i++; 
                }
            }
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    /*****************************************************************************
    * 
    * Method Name: comprobarFicherosIguales
    * Author/s Name: Antonio, Luis y Teresa
    * Description of method: comprueba si dos ficheros proporcionados por parámetros
    * son iguales, si lo son devuelve true, en caso contrario false.
    * 
    *****************************************************************************/     
    public static boolean comprobarFicherosIguales (String ruta, String estrategia) {
        boolean iguales = true;
        
        String fichero = ""; String fichero2 = "";
        String rutaFichero = RUTA_FUENTES + ruta  + estrategia + ".txt";
        String rutaFichero2 = RUTA_SOLUCION + ruta + estrategia + ".txt";
        try (BufferedReader reader = new BufferedReader (new FileReader(rutaFichero)); BufferedReader reader2 = new BufferedReader (new FileReader(rutaFichero2))) {  
            String line = reader.readLine();
            while (line != null) {
                fichero = fichero.concat(line);
                line = reader.readLine();
            }
            
            String line2 = reader2.readLine();
            while (line2 != null) {
                fichero2 = fichero2.concat(line2);
                line2 = reader2.readLine();
            }
            
            iguales = fichero.equals(fichero2);
        } catch (IOException ex) {
            System.out.println("ERROR AL LEER EL FICHERO SOLUCION - " + ex.toString());
        }
        
        return iguales;
    }
}
