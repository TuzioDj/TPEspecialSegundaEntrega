import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServicioBacktracking {
    private GrafoDirigido<Integer> grafo;
    private int minDistanciaTotal;
    private List<Integer> mejorSolucion;
    private long metrica; // Variable para almacenar el tiempo de inicio

    public ServicioBacktracking(GrafoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.minDistanciaTotal = Integer.MAX_VALUE;
        this.mejorSolucion = new ArrayList<>();
        this.metrica = 0;
    }

    public void encontrarMejorSolucion() {
        List<Integer> solucionActual = new ArrayList<>();

        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
            Integer estacion = it.next();
            solucionActual.add(estacion);
            int distanciaActual = 0;
            backtracking(estacion, solucionActual, distanciaActual);
            solucionActual.remove(estacion);
        }
    }

    private void backtracking(int estacionActual, List<Integer> solucionActual, int distanciaActual) {
        // si se recorrio todos los vertices
        if (todasLasEstacionesVisitadas(solucionActual)) {
            if (distanciaActual < minDistanciaTotal) { // menor distancia
                minDistanciaTotal = distanciaActual;
                mejorSolucion = new ArrayList<>(solucionActual);
            }
        }

        Iterator<Integer> iterator = grafo.obtenerAdyacentes(estacionActual);
        while (iterator.hasNext()) {
            int siguienteEstacion = iterator.next();
            if (!solucionActual.contains(siguienteEstacion)) { // si no se visita la siguiente estacion
                solucionActual.add(siguienteEstacion);

                int distancia = getDistanciaEntreEstacion(estacionActual, siguienteEstacion);
                //incrementa el contador
                this.metrica++;
                backtracking(siguienteEstacion, solucionActual, distanciaActual + distancia);

                solucionActual.remove(solucionActual.size() - 1);
            }
        }
    }

    private boolean todasLasEstacionesVisitadas(List<Integer> solution) {
        int totalStations = grafo.cantidadVertices();
        return solution.size() == totalStations;
    }

    private int getDistanciaEntreEstacion(int station1, int station2) {
        Arco<Integer> arco = grafo.obtenerArco(station1, station2);
        if (arco != null) {
            return arco.getEtiqueta();
        }
        return Integer.MAX_VALUE; // Asignar un valor alto si las estaciones no están conectadas
    }
    public String formatResult() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Backtracking\n");
        int totalKilometers = 0;

        for (int i = 0; i < mejorSolucion.size() - 1; i++) {
            int estacion1 = mejorSolucion.get(i);
            int estacion2 = mejorSolucion.get(i + 1);
            int distancia = getDistanciaEntreEstacion(estacion1, estacion2);
            resultado.append("E").append(estacion1).append("-E").append(estacion2);

            // Agregar coma si no es el último arco
            if (i < mejorSolucion.size() - 2) {
                resultado.append(",");
            }

            totalKilometers += distancia;
        }
        resultado.append("\n");
        resultado.append(totalKilometers).append(" kms").append("\n");
            resultado.append(metrica).append(" cantidad de loops").append("\n");

        return resultado.toString();
    }
}
