import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;


public class ServicioDFS {

	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}
	
	
    public List<Integer> dfsForest() {
        // Resolver DFS
        // Crear una lista de vertices
        Iterator<Integer> vertices = this.grafo.obtenerVertices();
        List<Integer> verticesList = new ArrayList<Integer>();
        Set<Integer> visitados = new HashSet<>();
        while (vertices.hasNext()) {
            Integer n = vertices.next();
            if (!visitados.contains(n)) {
                verticesList.addAll(this.dfs(n, visitados));
            }
        }
        return verticesList;
    }

    private List<Integer> dfs(Integer origen, Set<Integer> visitados) {
        List<Integer> aux = new ArrayList<>(Arrays.asList(origen));
        visitados.add(origen);
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);
        if (adyacentes != null) {
            while (adyacentes.hasNext()) {
                Integer n = adyacentes.next();
                if (!visitados.contains(n)) {
                    aux.addAll(this.dfs(n, visitados));
                }
            }
        }
        return aux;
    }
    

}
