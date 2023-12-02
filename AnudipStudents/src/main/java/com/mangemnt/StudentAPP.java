package com.mangemnt;
 
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class StudentAPP {

	public static void main(String[] args) {
		EntityManagerFactory factory = null;
		
		try { //connecting to database using persistence unit
			factory  = Persistence.createEntityManagerFactory("vk");
			EntityManager em = factory.createEntityManager();
			
			System.out.println("-----WELCOME TO STUDENT DATABAES-----");
			
			Student s1 = new Student(1, "Anushka", "Kohli", "anu@gmail.com", "7956546445", "Female");
			Student s2 = new Student(2, "Yuvaraj", "singh", "uv@gmail.com", "8096855522", "Male");
			Student s3 = new Student(3, "Jyothika", "surya", "jyoti@gmail.com", "8098585522", "Female");
			
			StudentDAO sDAO = new StudentDAO(em);
			sDAO.save(s1);
			sDAO.save(s2);
			sDAO.save(s3);
			
			System.out.println("Data added successfully");

			System.out.println("--------------------------");
			
			System.out.println("Student  details based on the id :");
			Optional<Student> studntById = sDAO.findById(1);
			System.out.println(studntById);
			 
			
			System.out.println("--------------------------");
			
			System.out.println("Details of all the students");	 
			List<Student> allStud = sDAO.findAll();
			System.out.println(allStud);
			
			
			
			System.out.println("------------------");
		
			
			int  newid = 3;
			String newfirstName ="Nagesh" ;
			String newLastName = "Dhamodhar" ;
			String newemail = "nagesh@gmail.com";
			String newphoneNum = "8921465132" ;
			String newgender  ="Male";
			
			sDAO.updateStudent(newid, newfirstName, newLastName, newemail, newphoneNum, newgender);
			
			System.out.println("Data  updated  sucessfully");
			
			
			System.out.println("------------------"); 
			
			System.out.println("Removeing based on the id :");
			
			sDAO.remove(2);
			
			System.out.println("2nd record is removed");
			
			
		}
		catch (HibernateException e) {
			 e.printStackTrace();
		}
		catch (Exception e) {
		 e.printStackTrace();
		}

	}

}
