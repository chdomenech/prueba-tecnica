package net.xiliosoft.prueba_tecnica.model;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.util.Date;

/**
 * Pruebas unitarias para la entidad Task.
 * Verifica el comportamiento de la entidad y sus métodos.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
public class TaskTest extends TestCase {

    /**
     * Constructor para JUnit.
     * 
     * @param testName Nombre del test
     */
    public TaskTest(String testName) {
        super(testName);
    }

    /**
     * Suite de pruebas.
     * 
     * @return Suite de tests
     */
    public static Test suite() {
        return new TestSuite(TaskTest.class);
    }

    /**
     * Prueba la creación de una tarea vacía.
     */
    public void testCrearTareaVacia() {
        Task task = new Task();
        
        assertNotNull("La tarea debe crearse", task);
        assertNull("El ID debe ser null inicialmente", task.getId());
        assertNull("El título debe ser null inicialmente", task.getTitle());
        assertEquals("El estado completado debe ser 0 por defecto", 
                    Integer.valueOf(0), task.getCompleted());
    }

    /**
     * Prueba el establecimiento y obtención de propiedades.
     */
    public void testGettersYSetters() {
        Task task = new Task();
        
        // ID
        Long id = 1L;
        task.setId(id);
        assertEquals("El ID debe establecerse correctamente", id, task.getId());
        
        // Título
        String titulo = "Tarea de prueba";
        task.setTitle(titulo);
        assertEquals("El título debe establecerse correctamente", titulo, task.getTitle());
        
        // Descripción
        String descripcion = "Descripción de prueba";
        task.setDescription(descripcion);
        assertEquals("La descripción debe establecerse correctamente", descripcion, task.getDescription());
        
        // Fecha límite
        Date fechaLimite = new Date();
        task.setDueDate(fechaLimite);
        assertEquals("La fecha límite debe establecerse correctamente", fechaLimite, task.getDueDate());
        
        // Estado completado
        task.setCompleted(1);
        assertEquals("El estado completado debe establecerse correctamente", 
                    Integer.valueOf(1), task.getCompleted());
        
        // Fecha de creación
        Date fechaCreacion = new Date();
        task.setCreatedAt(fechaCreacion);
        assertEquals("La fecha de creación debe establecerse correctamente", 
                    fechaCreacion, task.getCreatedAt());
    }

    /**
     * Prueba el estado completado de una tarea.
     */
    public void testEstadoCompletado() {
        Task task = new Task();
        
        // Tarea pendiente
        task.setCompleted(0);
        assertEquals("La tarea debe estar pendiente", Integer.valueOf(0), task.getCompleted());
        assertFalse("isCompleted debe ser false", task.getCompleted() == 1);
        
        // Tarea completada
        task.setCompleted(1);
        assertEquals("La tarea debe estar completada", Integer.valueOf(1), task.getCompleted());
        assertTrue("isCompleted debe ser true", task.getCompleted() == 1);
    }

    /**
     * Prueba que el estado por defecto es pendiente.
     */
    public void testEstadoPorDefecto() {
        Task task = new Task();
        
        assertEquals("El estado por defecto debe ser 0 (pendiente)", 
                    Integer.valueOf(0), task.getCompleted());
    }

    /**
     * Prueba la creación de una tarea completa.
     */
    public void testCrearTareaCompleta() {
        Task task = new Task();
        Date fechaLimite = new Date();
        Date fechaCreacion = new Date();
        
        task.setId(1L);
        task.setTitle("Tarea completa");
        task.setDescription("Esta es una tarea completa");
        task.setDueDate(fechaLimite);
        task.setCompleted(0);
        task.setCreatedAt(fechaCreacion);
        
        assertEquals("ID correcto", Long.valueOf(1L), task.getId());
        assertEquals("Título correcto", "Tarea completa", task.getTitle());
        assertEquals("Descripción correcta", "Esta es una tarea completa", task.getDescription());
        assertEquals("Fecha límite correcta", fechaLimite, task.getDueDate());
        assertEquals("Estado correcto", Integer.valueOf(0), task.getCompleted());
        assertEquals("Fecha de creación correcta", fechaCreacion, task.getCreatedAt());
    }

    /**
     * Prueba que se pueden establecer valores null.
     */
    public void testValoresNull() {
        Task task = new Task();
        
        task.setTitle(null);
        assertNull("El título puede ser null", task.getTitle());
        
        task.setDescription(null);
        assertNull("La descripción puede ser null", task.getDescription());
        
        task.setDueDate(null);
        assertNull("La fecha límite puede ser null", task.getDueDate());
        
        task.setCreatedAt(null);
        assertNull("La fecha de creación puede ser null", task.getCreatedAt());
    }
}

