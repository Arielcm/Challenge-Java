package com.java_app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String name;
	@NotNull
	private String class_schedule;
	@NotNull
	private int total_students;
	
	
	//Crea Tabla intermedia Course-Professor
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Course_Professor"
    	,joinColumns = @JoinColumn(name="course_id")
    	,inverseJoinColumns = @JoinColumn(name="professor_id")
    		)
    private Set<Professor> professors = new HashSet<>();
	
	

	
	public void addProfessor(Professor professor) {
		this.professors.add(professor);
	}
	

	public Set<Professor> getProfessors() {
		return professors;
	}
	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}
	
	
	//Get and Set
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClass_schedule() {
		return class_schedule;
	}
	public void setClass_schedule(String class_schedule) {
		this.class_schedule = class_schedule;
	}
	public int getTotal_students() {
		return total_students;
	}
	public void setTotal_students(int total_students) {
		this.total_students = total_students;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Materia=" + name + ", Horario=" + class_schedule;
	}
	
	
	//HashCode and Equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((class_schedule == null) ? 0 : class_schedule.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((professors == null) ? 0 : professors.hashCode());
		result = prime * result + total_students;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (class_schedule == null) {
			if (other.class_schedule != null)
				return false;
		} else if (!class_schedule.equals(other.class_schedule))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (professors == null) {
			if (other.professors != null)
				return false;
		} else if (!professors.equals(other.professors))
			return false;
		if (total_students != other.total_students)
			return false;
		return true;
	}
	
	
	
	
}
