package cr.ac.una.backend.prolog;


import org.jpl7.Query;
import org.jpl7.Term;
import java.util.List;
import java.util.Map;

public class PrologEngine {

    // Constructor para cargar el archivo de reglas Prolog
    public PrologEngine() {
        try {
            String consultQuery = "consult('src/main/resources/prolog/tareas.pl')";
            Query q1 = new Query(consultQuery);
            if (q1.hasSolution()) {
                System.out.println("Archivo Prolog cargado correctamente.");
            } else {
                throw new PrologExecutionException("Error: No se pudo cargar el archivo Prolog.");
            }
        } catch (Exception e) {
            throw new PrologExecutionException("Excepción al cargar el archivo Prolog.", e);
        }
    }

    // Método para realizar consultas en Prolog
    public Map<String, Term> realizarConsulta(String consulta) {
        try {
            Query query = new Query(consulta);
            if (query.hasSolution()) {
                return query.oneSolution();
            } else {
                throw new PrologExecutionException("Error: Consulta sin solución en Prolog para: " + consulta);
            }
        } catch (Exception e) {
            throw new PrologExecutionException("Excepción al realizar consulta en Prolog: " + consulta, e);
        }
    }

    // Método específico para obtener un plan de tareas optimizado
    public List<Map<String, Term>> obtenerPlanDeTareas(List<Map<String, Object>> tareas) {
        try {
            String consulta = "plan_optimo(Tareas, Plan)";
            Query query = new Query(consulta);
            if (query.hasSolution()) {
                return List.of(query.allSolutions());
            } else {
                throw new PrologExecutionException("Error: No se pudo obtener un plan de tareas óptimo.");
            }
        } catch (Exception e) {
            throw new PrologExecutionException("Excepción al obtener plan de tareas en Prolog.", e);
        }
    }
}
