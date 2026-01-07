package net.xiliosoft.prueba_tecnica.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidad JPA que representa una tarea en el sistema.
 * Mapea la tabla TASKS en la base de datos Oracle.
 * 
 * @author Christian Domenech
 * @version 1.0
 */
@Entity
@Table(name = "TASKS")
@SequenceGenerator(
        name = "tasks_seq",
        sequenceName = "TASKS_SEQ",
        allocationSize = 1
)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "COMPLETED")
    private Integer completed = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    public Task() {}

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = new Date();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public Integer getCompleted() { return completed; }
    public void setCompleted(Integer completed) { this.completed = completed; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
