package listaenlazadaframe;
public class ListaEnlazada {
    private Nodo cabeza = null;

    public ListaEnlazada(String tipo) {
    }

    public void insertar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    public void eliminar(Object dato) {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            System.out.println("Elemento eliminado.");
            return;
        }

        Nodo temp = cabeza;
        while (temp.siguiente != null) {
            if (temp.siguiente.dato.equals(dato)) {
                temp.siguiente = temp.siguiente.siguiente;
                System.out.println("Elemento eliminado.");
                return;
            }
            temp = temp.siguiente;
        }
        System.out.println("Elemento no encontrado.");
    }

    public void modificar(Object datoViejo, Object datoNuevo) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (temp.dato.equals(datoViejo)) {
                temp.dato = datoNuevo;
                System.out.println("Elemento modificado.");
                return;
            }
            temp = temp.siguiente;
        }
        System.out.println("Elemento no encontrado.");
    }

    public String mostrar() {
    StringBuilder sb = new StringBuilder();
    Nodo temp = cabeza;
    while (temp != null) {
        sb.append(temp.dato).append(" -> ");
        temp = temp.siguiente;
    }
    sb.append("null");
    return sb.toString();
   }
}
