package net.xiliosoft.prueba_tecnica.service;

import net.xiliosoft.prueba_tecnica.model.Task;
import net.xiliosoft.prueba_tecnica.repository.TaskRepository;
import org.apache.log4j.Logger;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Servicio que contiene la lógica de negocio para la gestión de tareas.
 * Actúa como capa intermedia entre el Controller y el Repository.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
@Dependent
public class TaskService {

    private static final Logger logger = Logger.getLogger(TaskService.class);

    @Inject
    private TaskRepository taskRepository;

    /**
     * Crea una nueva tarea en el sistema.
     * Establece la fecha de creación si no está definida.
     * 
     * @param task La tarea a crear
     * @return La tarea creada con su ID generado
     */
    @Transactional
    public Task crearTarea(Task task) {
        logger.info("Creando nueva tarea: " + task.getTitle());
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(new java.util.Date());
        }
        Task tareaCreada = taskRepository.crear(task);
        logger.info("Tarea creada exitosamente con ID: " + tareaCreada.getId());
        return tareaCreada;
    }

    /**
     * Actualiza una tarea existente.
     * 
     * @param task La tarea a actualizar
     * @return La tarea actualizada
     */
    @Transactional
    public Task actualizarTarea(Task task) {
        logger.info("Actualizando tarea con ID: " + task.getId());
        return taskRepository.actualizar(task);
    }

    /**
     * Elimina una tarea del sistema.
     * 
     * @param id El ID de la tarea a eliminar
     */
    @Transactional
    public void eliminarTarea(Long id) {
        logger.info("Eliminando tarea con ID: " + id);
        taskRepository.eliminar(id);
    }

    /**
     * Busca una tarea por su ID.
     * 
     * @param id El ID de la tarea
     * @return La tarea encontrada o null si no existe
     */
    public Task buscarTareaPorId(Long id) {
        return taskRepository.buscarPorId(id);
    }

    /**
     * Obtiene todas las tareas del sistema.
     * 
     * @return Lista de todas las tareas
     */
    public List<Task> buscarTodasLasTareas() {
        logger.debug("Buscando todas las tareas");
        return taskRepository.buscarTodas();
    }

    /**
     * Obtiene todas las tareas completadas.
     * 
     * @return Lista de tareas completadas
     */
    public List<Task> buscarTareasCompletadas() {
        logger.debug("Buscando tareas completadas");
        return taskRepository.buscarCompletadas();
    }

    /**
     * Obtiene todas las tareas pendientes.
     * 
     * @return Lista de tareas pendientes
     */
    public List<Task> buscarTareasPendientes() {
        logger.debug("Buscando tareas pendientes");
        return taskRepository.buscarPendientes();
    }

    /**
     * Marca una tarea como completada.
     * 
     * @param id El ID de la tarea a marcar como completada
     */
    @Transactional
    public void marcarComoCompletada(Long id) {
        logger.info("Marcando tarea como completada, ID: " + id);
        Task task = taskRepository.buscarPorId(id);
        if (task != null) {
            task.setCompleted(1);
            taskRepository.actualizar(task);
            logger.info("Tarea marcada como completada exitosamente");
        } else {
            logger.warn("No se encontró la tarea con ID: " + id + " para marcar como completada");
        }
    }

    /**
     * Marca una tarea como pendiente.
     * 
     * @param id El ID de la tarea a marcar como pendiente
     */
    @Transactional
    public void marcarComoPendiente(Long id) {
        logger.info("Marcando tarea como pendiente, ID: " + id);
        Task task = taskRepository.buscarPorId(id);
        if (task != null) {
            task.setCompleted(0);
            taskRepository.actualizar(task);
            logger.info("Tarea marcada como pendiente exitosamente");
        } else {
            logger.warn("No se encontró la tarea con ID: " + id + " para marcar como pendiente");
        }
    }
}

