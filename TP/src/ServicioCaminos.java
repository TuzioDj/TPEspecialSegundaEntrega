import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;

	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	
	public List<List<Integer>> caminos() {
		HashSet<Integer> visitados = new HashSet<>();
		List<List<Integer>> caminos = new ArrayList<>();
		boolean llegamos = false;
		dfs(origen, visitados, 0, llegamos, caminos);
		return caminos;
	}
	
	private void dfs(Integer origen, HashSet<Integer> visitados, int cont, boolean llegamos, List<List<Integer>> caminos) {
		visitados.add(origen);
		if (origen.equals(destino)) {
			llegamos = true;
		}
		if (llegamos || cont >= lim) {
			if (llegamos) {
				List<Integer> camino = new ArrayList<>(visitados);
				caminos.add(camino);
			}
			visitados.remove(origen);
			return;
		}
		Iterator<Integer> edge = grafo.obtenerAdyacentes(origen);
		if (edge != null) {
			while (edge.hasNext()) {
				Integer n = edge.next();
				if (!visitados.contains(n)) {
					dfs(n, visitados, cont + 1, llegamos, caminos);
				}
			}
		}
		visitados.remove(origen);
	}
}
