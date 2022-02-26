package com.infobarbosa.task.model;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
    
    @Id
    private UUID id;

    @Column(length=100, nullable=false)
    private String description;

    public Task(){}

    public Task(String description){
        this.description = description;
    }
    
    public Task(UUID id, String description){
        this.id = id;
        this.description = description;
    }

    public UUID getId(){ return this.id; }
    public String getDescription(){ return this.description; }

    public void setId(UUID id){ this.id = id; }
    public void setDescription(String description){ this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
