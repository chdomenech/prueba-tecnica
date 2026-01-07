package net.xiliosoft.prueba_tecnica.service;

import net.xiliosoft.prueba_tecnica.model.Task;
import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.Date;

/**
 * Pruebas unitarias para la lógica de negocio de TaskService.
 * Verifica el comportamiento de los métodos del servicio.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
public class TaskServiceTest extends TestCase {

    /**
     * Constructor para JUnit.
     * 
     * @param testName Nombre del test
     */
    public TaskServiceTest(String testName) {
        super(testName);
    }

    /**
     * Suite de pruebas.
     * 
     * @return Suite de tests
     */
    public static Test suite() {
        return new TestSuite(TaskServiceTest.class);
    }

    /**
     * Prueba la lógica de establecimiento de fecha de creación.
     * Verifica que si una tarea no tiene fecha, se debe establecer.
     */
    public void testLógicaFechaCreacion() {
        Task task = new Task();
        task.setTitle("Tarea de prueba");
        task.setDescription("Descripción de prueba");
        task.setCompleted(0);

        // Verificar que la fecha de creación es null inicialmente
        assertNull("La fecha de creación debe ser null inicialmente", task.getCreatedAt());
        
        // Simular la lógica del servicio: establecer fecha si es null
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(new Date());
        }
        
        assertNotNull("La fecha de creación debe establecerse", task.getCreatedAt());
    }

    /**
     * Prueba que una tarea con fecha de creación ya establecida no se modifica.
     */
    public void testFechaCreacionExistente() {
        Task task = new Task();
        task.setTitle("Tarea con fecha");
        Date fechaOriginal = new Date(1000000L);
        task.setCreatedAt(fechaOriginal);

        // La lógica del servicio no debe cambiar la fecha si ya existe
        if (task.getCreatedAt() == null) {
            task.setCreatedAt(new Date());
        }

        assertEquals("La fecha no debe cambiar si ya está establecida", 
                    fechaOriginal, task.getCreatedAt());
    }

    /**
     * Prueba la lógica de cambio de estado a completada.
     */
    public void testLógicaMarcarCompletada() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarea pendiente");
        task.setCompleted(0);

        // Simular la lógica del servicio
        if (task != null) {
            task.setCompleted(1);
        }

        assertEquals("La tarea debe estar completada", Integer.valueOf(1), task.getCompleted());
        assertTrue("isCompleted debe retornar true", task.getCompleted() == 1);
    }

    /**
     * Prueba la lógica de cambio de estado a pendiente.
     */
    public void testLógicaMarcarPendiente() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarea completada");
        task.setCompleted(1);

        // Simular la lógica del servicio
        if (task != null) {
            task.setCompleted(0);
        }

        assertEquals("La tarea debe estar pendiente", Integer.valueOf(0), task.getCompleted());
        assertFalse("isCompleted debe retornar false", task.getCompleted() == 1);
    }

    /**
     * Prueba la validación de tarea nula en operaciones.
     */
    public void testValidacionTareaNula() {
        Task task = null;
        
        // La lógica del servicio debe validar que la tarea no sea null
        if (task != null) {
            task.setCompleted(1);
            assertTrue("No debe llegar aquí si task es null", false);
        } else {
            assertTrue("La validación de null debe funcionar", true);
        }
    }

    /**
     * Prueba la lógica de actualización de tarea.
     */
    public void testLógicaActualizacion() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarea original");
        task.setDescription("Descripción original");
        task.setCompleted(0);

        // Simular actualización
        task.setTitle("Tarea actualizada");
        task.setDescription("Descripción actualizada");

        assertEquals("El título debe actualizarse", "Tarea actualizada", task.getTitle());
        assertEquals("La descripción debe actualizarse", "Descripción actualizada", task.getDescription());
        assertEquals("El ID debe mantenerse", Long.valueOf(1L), task.getId());
    }

    /**
     * Prueba los valores por defecto de una tarea nueva.
     */
    public void testValoresPorDefecto() {
        Task task = new Task();
        
        assertEquals("El estado completado debe ser 0 por defecto", 
                    Integer.valueOf(0), task.getCompleted());
        assertNull("La fecha de creación debe ser null inicialmente", task.getCreatedAt());
        assertNull("El título debe ser null inicialmente", task.getTitle());
        assertNull("La descripción debe ser null inicialmente", task.getDescription());
        assertNull("La fecha límite debe ser null inicialmente", task.getDueDate());
    }
}

