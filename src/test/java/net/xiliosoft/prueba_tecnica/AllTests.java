package net.xiliosoft.prueba_tecnica;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.xiliosoft.prueba_tecnica.model.TaskTest;
import net.xiliosoft.prueba_tecnica.service.TaskServiceTest;

/**
 * Suite de pruebas que ejecuta todas las pruebas unitarias del proyecto.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
public class AllTests extends TestSuite {

    /**
     * Constructor de la suite de pruebas.
     * 
     * @return Suite con todas las pruebas
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("Pruebas Unitarias - Sistema de Gestión de Tareas");
        
        // Agregar todas las suites de pruebas
        suite.addTest(TaskTest.suite());
        suite.addTest(TaskServiceTest.suite());
        suite.addTest(AppTest.suite());
        
        return suite;
    }

    /**
     * Método principal para ejecutar las pruebas desde la línea de comandos.
     * 
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}

