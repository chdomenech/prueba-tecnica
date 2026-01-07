package net.xiliosoft.prueba_tecnica.controller;

import net.xiliosoft.prueba_tecnica.model.Task;
import net.xiliosoft.prueba_tecnica.service.TaskService;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Managed Bean JSF para la gestión de tareas.
 * Controla la interacción entre la vista y el servicio de negocio.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
@Named
@RequestScoped
public class TaskBean {

    private static final Logger logger = Logger.getLogger(TaskBean.class);

    @Inject
    private TaskService taskService;

    private Task task;
    private List<Task> tasks;

    /**
     * Inicializa el bean después de la construcción.
     * Carga las tareas y prepara el formulario para una nueva tarea.
     */
    @PostConstruct
    public void init() {
        logger.debug("Inicializando TaskBean");
        try {
            cargarTareas();
            nuevaTarea();
        } catch (Exception e) {
            logger.error("Error al inicializar TaskBean", e);
            // Inicializar lista vacía para evitar NullPointerException
            tasks = new java.util.ArrayList<>();
            nuevaTarea();
            agregarMensaje("Error al cargar las tareas: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }


    /**
     * Inicializa el formulario para crear una nueva tarea.
     */
    public void nuevaTarea() {
        task = new Task();
        task.setCompleted(0);
    }

    /**
     * Carga todas las tareas del sistema.
     */
    public void cargarTareas() {
        logger.debug("Cargando lista de tareas");
        tasks = taskService.buscarTodasLasTareas();
        logger.debug("Tareas cargadas: " + (tasks != null ? tasks.size() : 0));
    }

    /**
     * Guarda una tarea (crea nueva o actualiza existente).
     * 
     * @return String de navegación o null si hay error
     */
    public String guardar() {
        try {
            logger.info("Guardando tarea: " + task.getTitle());
            
            // Validaciones
            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                logger.warn("Intento de guardar tarea sin título");
                agregarMensaje("El título es obligatorio", FacesMessage.SEVERITY_ERROR);
                return null;
            }

            if (task.getId() == null) {
                taskService.crearTarea(task);
                agregarMensaje("Tarea creada exitosamente", FacesMessage.SEVERITY_INFO);
                logger.info("Tarea creada exitosamente");
            } else {
                taskService.actualizarTarea(task);
                agregarMensaje("Tarea actualizada exitosamente", FacesMessage.SEVERITY_INFO);
                logger.info("Tarea actualizada exitosamente");
            }

            cargarTareas();
            nuevaTarea();
            // Retornar null para quedarse en la misma página (el update del botón maneja la actualización)
            return null;
        } catch (Exception e) {
            logger.error("Error al guardar tarea: " + task.getTitle(), e);
            agregarMensaje("Error al guardar: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    /**
     * Elimina una tarea del sistema.
     * 
     * @param id El ID de la tarea a eliminar
     */
    public void eliminar(Long id) {
        try {
            logger.info("Eliminando tarea con ID: " + id);
            taskService.eliminarTarea(id);
            agregarMensaje("Tarea eliminada exitosamente", FacesMessage.SEVERITY_INFO);
            cargarTareas();
            logger.info("Tarea eliminada exitosamente");
        } catch (Exception e) {
            logger.error("Error al eliminar tarea con ID: " + id, e);
            agregarMensaje("Error al eliminar: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Marca una tarea como completada.
     * 
     * @param id El ID de la tarea a marcar como completada
     */
    public void marcarCompletada(Long id) {
        try {
            logger.info("Marcando tarea como completada, ID: " + id);
            taskService.marcarComoCompletada(id);
            agregarMensaje("Tarea marcada como completada", FacesMessage.SEVERITY_INFO);
            cargarTareas();
        } catch (Exception e) {
            logger.error("Error al marcar tarea como completada, ID: " + id, e);
            agregarMensaje("Error al marcar como completada: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Marca una tarea como pendiente.
     * 
     * @param id El ID de la tarea a marcar como pendiente
     */
    public void marcarPendiente(Long id) {
        try {
            logger.info("Marcando tarea como pendiente, ID: " + id);
            taskService.marcarComoPendiente(id);
            agregarMensaje("Tarea marcada como pendiente", FacesMessage.SEVERITY_INFO);
            cargarTareas();
        } catch (Exception e) {
            logger.error("Error al marcar tarea como pendiente, ID: " + id, e);
            agregarMensaje("Error al marcar como pendiente: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    /**
     * Carga una tarea específica para edición.
     * 
     * @param id El ID de la tarea a cargar
     */
    public void cargarTarea(Long id) {
        logger.debug("Cargando tarea con ID: " + id);
        task = taskService.buscarTareaPorId(id);
        if (task == null) {
            logger.warn("No se encontró la tarea con ID: " + id);
            task = new Task();
        }
    }

    /**
     * Agrega un mensaje a la interfaz de usuario.
     * 
     * @param mensaje El texto del mensaje
     * @param severidad La severidad del mensaje (INFO, WARN, ERROR)
     */
    private void agregarMensaje(String mensaje, FacesMessage.Severity severidad) {
        FacesContext.getCurrentInstance()
            .addMessage(null, new FacesMessage(severidad, mensaje, null));
    }

    /**
     * Verifica si una tarea está completada.
     * 
     * @param task La tarea a verificar
     * @return true si la tarea está completada, false en caso contrario
     */
    public boolean isCompletada(Task task) {
        return task != null && task.getCompleted() != null && task.getCompleted() == 1;
    }


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

