public class App {

    public static void main(String[] args) {
        String path = "src/datasets/dataset3.txt";
        CSVReader reader = new CSVReader(path);
        reader.read();

        // Obtiene el grafo resultante
        GrafoDirigido<Integer> grafo = reader.getGrafo();

        // Crea el servicio para backtracking pasandole el grafo
        ServicioBacktracking servicioBacktracking = new ServicioBacktracking(grafo);
        servicioBacktracking.encontrarMejorSolucion();
        System.out.println(servicioBacktracking.formatResult());

        // Crea el servicio greedy utilizando el algoritmo de Dijstra pasandole el grafo
        ServicioGreedy servicioGreedy = new ServicioGreedy(grafo);
        servicioGreedy.encontrarMejorSolucion();
        System.out.println(servicioGreedy.formatResult());
    }
}