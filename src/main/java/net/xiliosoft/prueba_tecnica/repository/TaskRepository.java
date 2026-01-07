package net.xiliosoft.prueba_tecnica.repository;

import net.xiliosoft.prueba_tecnica.model.Task;
import org.apache.log4j.Logger;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repository para operaciones de acceso a datos de la entidad Task.
 * Implementa el patrón Repository para abstraer la capa de persistencia.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
@Dependent
public class TaskRepository {

    private static final Logger logger = Logger.getLogger(TaskRepository.class);

    @PersistenceContext(unitName = "prueba-tecnica")
    private EntityManager em;

    /**
     * Crea una nueva tarea en la base de datos.
     * 
     * @param task La tarea a persistir
     * @return La tarea persistida con su ID generado
     */
    public Task crear(Task task) {
        logger.info("Creando nueva tarea: " + task.getTitle());
        em.persist(task);
        em.flush();
        logger.info("Tarea creada con ID: " + task.getId());
        return task;
    }

    /**
     * Actualiza una tarea existente en la base de datos.
     * 
     * @param task La tarea a actualizar
     * @return La tarea actualizada
     */
    public Task actualizar(Task task) {
        logger.info("Actualizando tarea con ID: " + task.getId());
        return em.merge(task);
    }

    /**
     * Elimina una tarea de la base de datos.
     * 
     * @param id El ID de la tarea a eliminar
     */
    public void eliminar(Long id) {
        logger.info("Eliminando tarea con ID: " + id);
        Task task = em.find(Task.class, id);
        if (task != null) {
            em.remove(task);
            logger.info("Tarea eliminada exitosamente");
        } else {
            logger.warn("No se encontró la tarea con ID: " + id);
        }
    }

    /**
     * Busca una tarea por su ID.
     * 
     * @param id El ID de la tarea a buscar
     * @return La tarea encontrada o null si no existe
     */
    public Task buscarPorId(Long id) {
        return em.find(Task.class, id);
    }

    /**
     * Obtiene todas las tareas ordenadas por fecha de creación descendente.
     * 
     * @return Lista de todas las tareas
     */
    public List<Task> buscarTodas() {
        logger.debug("Buscando todas las tareas");
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t ORDER BY t.createdAt DESC", Task.class);
        return query.getResultList();
    }

    /**
     * Obtiene todas las tareas completadas.
     * 
     * @return Lista de tareas completadas
     */
    public List<Task> buscarCompletadas() {
        logger.debug("Buscando tareas completadas");
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.completed = 1 ORDER BY t.createdAt DESC", Task.class);
        return query.getResultList();
    }

    /**
     * Obtiene todas las tareas pendientes ordenadas por fecha límite.
     * 
     * @return Lista de tareas pendientes
     */
    public List<Task> buscarPendientes() {
        logger.debug("Buscando tareas pendientes");
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.completed = 0 ORDER BY t.dueDate ASC, t.createdAt DESC", Task.class);
        return query.getResultList();
    }
}

