import java.util.*;

public class ServicioGreedy {
    private GrafoDirigido<Integer> grafo;
    private List<Arco<Integer>> mejorSolucion;
    private int distanciaTotal;

    private long metrica; // Variable para almacenar el tiempo de inicio

    public ServicioGreedy(GrafoDirigido<Integer> grafo) {
        this.grafo = grafo;
        this.mejorSolucion = new ArrayList<>();
        this.distanciaTotal = 0;
        this.metrica = 0;
    }

    public void encontrarMejorSolucion() {
        Set<Integer> visitados = new HashSet<>();
        // arma una lista de arcos ordenados por su etiqueta usando la interfaz Comparator de Integer (metodo para el criterio Greedy)
        PriorityQueue<Arco<Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Arco::getEtiqueta));

        Integer estacionInicial = grafo.obtenerVertices().next();
        visitados.add(estacionInicial);

        while (visitados.size() < grafo.cantidadVertices()) {
            // incrementa el contador de ciclos
            this.metrica++;
            for (Integer estacion : visitados) {
                Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(estacion);
                while (adyacentes.hasNext()) {
                    // incrementa el contador de ciclos
                    this.metrica++;
                    Integer estacionAdyacente = adyacentes.next();
                    if (!visitados.contains(estacionAdyacente)) {
                        Arco<Integer> arco = grafo.obtenerArco(estacion, estacionAdyacente);
                        priorityQueue.add(arco);
                    }
                }
            }

            Arco<Integer> minArco = priorityQueue.poll();

            if (minArco != null) {
                Integer siguienteEstacion = minArco.getVerticeDestino();

                if (!visitados.contains(siguienteEstacion)) {
                    visitados.add(siguienteEstacion);
                    mejorSolucion.add(minArco);
                    distanciaTotal += minArco.getEtiqueta();
                }
            }
        }
    }

    public String formatResult() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Greedy\n");
        for (int i = 0; i < mejorSolucion.size(); i++) {
            Arco<Integer> arco = mejorSolucion.get(i);
            int origen = arco.getVerticeOrigen();
            int destino = arco.getVerticeDestino();
            resultado.append("E" + origen + "-E" + destino);

            // Agregar coma si no es el último arco
            if (i < mejorSolucion.size() - 1) {
                resultado.append(",");
            }
        }
        resultado.append("\n");
        resultado.append(distanciaTotal).append(" kms\n");
        resultado.append(metrica).append(" cantidad de loops").append("\n");

        return resultado.toString();
    }

}
