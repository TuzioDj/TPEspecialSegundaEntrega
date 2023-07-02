import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

    protected HashMap<Integer, HashMap<Integer, Arco<T>>> vertices;

    public GrafoDirigido() {
        vertices = new HashMap<>();
    }

    /*
     COMPLEJIDAD: O(1) en el mejor de los casos y O(n) en el peor de los casos.
     En el mejor de los casos, la función simplemente comprueba si el vértice ya
     existe en el HashMap y devuelve sin hacer nada más.
     Esto tiene una complejidad de tiempo constante O(1).
     En el peor de los casos, la función debe agregar un nuevo vértice al HashMap.
     Esto implica crear un nuevo objeto HashMap y agregarlo al HashMap principal.
     La complejidad de esto es O(n), donde n es el número de elementos en el
     HashMap.
     */
    public void agregarVertice(int verticeId) {
        // controlo que el vertice no exista
        if (!this.contieneVertice(verticeId)) {
            // agrego el vertice
            vertices.put(verticeId, new HashMap<>());
        }
    }

    /*
     COMPLEJIDAD: O(V^2), donde V refiere a vertices, ya que primero buscamos el 
     vertice, que esto tiene una complejidad O(1), y despues para eliminar los 
     arcos que apuntan hacia el vertice objetivo, recorremos los vertices y sus
     adyacentes para eliminar los arcos correspondientes.
     Por lo tanto, en el peor de los casos deberiamos recorrer todos los vertices y
     sus adyacentes, lo que lleva a una dificultad de O(V^2).
     Por ultimo, borramos el vertice objetivo, lo cual tiene una dificultad O(1)
     */
    @Override
    public void borrarVertice(int verticeB) {
        if (vertices.containsKey(verticeB)) {
            // Eliminar los arcos que apuntan hacia el vértice
            for (HashMap<Integer, Arco<T>> adyacentes : vertices.values()) {
                adyacentes.remove(verticeB);
            }

            // Eliminar el vértice del mapa de vértices
            vertices.remove(verticeB);
        }
    }

    /*
     COMPLEJIDAD: O(1) Porque hace get, donde obtiene el vertice origen (O(1)) y
     put, donde coloca el arco al vertice obtenido(O(1))
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices
     en el grafo.
    */
    @Override
    public void agregarArco(int verticeOrigen, int verticeDestino, T etiqueta) {
        // controlo que el vertice no exista
        if (this.contieneVertice(verticeOrigen)) {
            // Creamos un arco con los datos ingresados
            Arco<T> auxArco = new Arco<>(verticeOrigen, verticeDestino, etiqueta);
            // Buscamos el vertice y agregamos el arco
            vertices.get(verticeOrigen).put(verticeDestino, auxArco);
        }
    }

    /*
     COMPLEJIDAD: O(1) Porque hace get, donde obtiene el vertice origen (O(1)) y
     remove, donde elimina el arco que se encuentre entre el vertice origen y
     destino.
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices
     en el grafo.
    */
    @Override
    public void borrarArco(int verticeOrigen, int verticeDestino) {
        // Buscamos el arco y lo borramos
        vertices.get(verticeOrigen).remove(verticeDestino);

    }

    /*
     COMPLEJIDAD: O(1) Porque hace get, donde obtiene el vertice origen (O(1)) y
     containsKey, que no recorre "vertices", sino que utiliza una función hash
     para calcular el índice en la tabla donde se almacenaría el elemento con esa
     clave.
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices
     en el grafo.
    */
    @Override
    public boolean contieneVertice(int verticeId) {
        // Buscamos el vertice y devuelve si existe
        boolean contains = vertices.containsKey(verticeId);
        return contains;
    }

    /*
     COMPLEJIDAD: O(1) Porque hace get, donde obtiene el vertice origen (O(1)) y
     containsKey, que no recorre "vertices", sino que utiliza una función hash
     para calcular el índice en la tabla donde se almacenaría el elemento con esa clave.
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices en el grafo.
    */
    @Override
    public boolean existeArco(int verticeOrigen, int verticeDestino) {
        // Buscamos el arco y devuelve si existe
        if (vertices.containsKey(verticeOrigen)) {
            if (vertices.get(verticeOrigen).containsKey(verticeDestino)) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    /*
     COMPLEJIDAD: O(1) Porque hace get, donde obtiene el vertice origen y el
     vertice destino del arco para obtenerlo (O(1))
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices
     en el grafo.
    */
    @Override
    public Arco<T> obtenerArco(int verticeOrigen, int verticeDestino) {
        Arco<T> aux = vertices.get(verticeOrigen).get(verticeDestino);
        return aux;
    }

    /*
     COMPLEJIDAD: O(1) Porque hace size, donde obtiene los numeros de elementos
     que obtiene el Hash
     Por lo tanto, el tiempo no aumenta a medida que aumenta el numero de vertices
     en el grafo.
    */
    @Override
    public int cantidadVertices() {
        int aux = vertices.size();
        return aux;
    }

    /*
     COMPLEJIDAD: O(V), donde V son los vertices, ya que iteramos en el hashmap
     de vertices, lo cual tendria una dificultad de O(V). Despues, obtenemos
     el tamaño de los adyacentes.
     Por ultimo, sumamos los tamaños de los adyacentes, accion la cual la hacemos
     numero de vertices veces, lo cual seria una dificultad O(v)
    */   
    @Override
    public int cantidadArcos() {
        int aux = 0;
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()) {
            int arcos = entry.getValue().size();
            aux = aux + arcos;
        }
        return aux;
    }

    /*     
     COMPLEJIDAD: O(V), donde V son los vertices, ya que iteramos en el hashmap
     de vertices para obtenerlos y guardarlos en una variable para posteriormente
     retornarla.
    */
    @Override
    public Iterator<Integer> obtenerVertices() {
        Iterator<Integer> iterator = vertices.keySet().iterator();
        return iterator;
    }

    /*     
     COMPLEJIDAD: O(A), donde A son los arcos, ya que primero traemos el
     vertice para despues iterar en los arcos  para obtener los adyacentes,
     guardarlos en una variable y luego retornarla.
     retornarla.
    */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        Iterator<Integer> iterator = vertices.get(verticeId).keySet().iterator();
        return iterator;
    }

    /*     
     COMPLEJIDAD: O(V+A), donde V son los vertices y A son los arcos, ya
     que tenemos que iterar entre los vertices, y por cada vertice iterar
     sobre sus arcos para guardarlos y despues retornalos.
    */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        List<Arco<T>> arcos = new ArrayList<>();
        for (Map.Entry<Integer, HashMap<Integer, Arco<T>>> entry : vertices.entrySet()) {
            arcos.addAll(entry.getValue().values());
        }
        return arcos.iterator();
    }

    /*     
     COMPLEJIDAD: O(A), donde A son los arcos, ya que primero traemos el
     vertice objetivo para despues iterar sobre sus arcos, despues
     guardarlos en una variable y luego retornarlos.
    */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        Iterator<Arco<T>> iterator = vertices.get(verticeId).values().iterator();
        return iterator;
    }

    @Override
    public String toString() {
        for (int verticeId : this.vertices.keySet()) {
            for (int destinoId : this.vertices.get(verticeId).keySet()) {
                System.out.println(verticeId + ": " + verticeId + " -> " + destinoId);
            }
        }
        return super.toString();
    }
}
