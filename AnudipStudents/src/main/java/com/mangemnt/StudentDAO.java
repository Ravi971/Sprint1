package com.mangemnt;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StudentDAO {  //DAO -Data Access object

	private EntityManager em;

	public StudentDAO(final EntityManager em) {

		this.em = em;
	}

	public void save(final Student student) {
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction(); // Return the resource-level EntityTransaction object

			if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
			{
				tx.begin(); //Start a resource transaction. 

			}
//			em.persist(student);
			Student mergedStudent = em.merge(student);

			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Optional<Student> findById(int id) {
//A container object which may or may not contain a non-null value.If a value is present, 
//		isPresent() returns true. If novalue is present, 
//		the object is considered empty and isPresent() returns false. 
		Student s = em.find(Student.class, id);

		if (s != null) {
			return Optional.of(s);//Returns an Optional describing the given non-nullvalue.
		} else {
			return Optional.empty();//Returns an empty Optional instance. No value is present for this Optional.
		}

	}

	public List<Student> findAll() {

		List<Student> s1 = em.createQuery("from Student", Student.class).getResultList();

		return s1;

	}

	
	public void updateStudent( int id, String newfirstName, String newLastName, String newemail, String newphoneNum, String newgender) {
		EntityTransaction tx = null; 
		try {
			tx = em.getTransaction(); // Return the resource-level EntityTransaction object

			if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
			{
				tx.begin();
			}
			Student student = em.find(Student.class, id) ;
			if(student !=null) {
				student.setFirstName(newfirstName);
				student.setLastName(newLastName);
				student.setEmail(newemail);
				student.setPhoneNum(newphoneNum);
				student.setGender(newgender);
				
				em.merge(student); //merger the changes
				tx.commit(); 
			}
		  	
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		
	}
	
	
	
	public void remove(int id) {  //removing  based on id 
		EntityTransaction tx = null; 
		
		Student s = em.find(Student.class, id);  
		
		try {
			tx = em.getTransaction(); // Return the resource-level EntityTransaction object

			if (!tx.isActive()) // Indicate whether a resource transaction is in progress.
			{
				tx.begin();
			}
			em.remove(s); //  Remove the entity instance.

			tx.commit();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
