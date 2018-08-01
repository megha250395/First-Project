package com.hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Insert_Data {

	/**
	 * @author Amit Sinha
	 * OneToMany Bi Directional (Main) Customer -> (Child)Request
	 *
	 *             RESULT Query- 
	 * Hibernate: insert into customers (email, firstName, lastName, phone) values (?, ?, ?, ?)
	* Hibernate: insert into customers (email, firstName, lastName, phone) values (?, ?, ?, ?)
	* Hibernate: insert into customers (email, firstName, lastName, phone) values (?, ?, ?, ?)
	* Hibernate: insert into request (description, reqDate, status) values (?, ?, ?)
	* Hibernate: insert into request (description, reqDate, status) values (?, ?, ?)
	* Hibernate: insert into request (description, reqDate, status) values (?, ?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)
	* Hibernate: insert into cust_req (st_FK, inv_FK) values (?, ?)             
	 * 
	 */
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		Customer cust = new Customer();
		cust.setFirstName("amit");
		cust.setLastName("Sinha");
		cust.setEmail("amit@mail");
		cust.setPhone(8495036559L);
		
		Customer cust2 = new Customer();
		cust2.setFirstName("abhi");
		cust2.setLastName("Sinha");
		cust2.setEmail("abhi@mail");
		cust2.setPhone(98684685L);
		
		Customer cust3 = new Customer();
		cust3.setFirstName("vikas");
		cust3.setLastName("chandra");
		cust3.setEmail("vikas@mail");
		cust3.setPhone(6546856468L);
		session.save(cust);
		session.save(cust2);
		session.save(cust3);

		Request req = new Request();
		req.setDescription("Product");
		req.setReqDate("2018-07-07");
		req.setStatus("Pending");

		Request req1 = new Request();
		req1.setDescription("Items");
		req1.setReqDate("2018-07-09");
		req1.setStatus("Deleverd");
		
		Request req2 = new Request();
		req2.setDescription("Food");
		req2.setReqDate("2018-08-19");
		req2.setStatus("shipped");

		session.save(req);
		session.save(req1);
		session.save(req2);

		Set<Request> re1=new HashSet<Request>();
		re1.add(req1);
		re1.add(req);
		cust.setRequests(re1);
		
		Set<Request> re2=new HashSet<Request>();
		re2.add(req1);
		re2.add(req2);
		cust2.setRequests(re2);
		
		Set<Request> re3=new HashSet<Request>();
		re3.add(req2);
		re3.add(req);
		re3.add(req1);
		cust3.setRequests(re3);

		tx.commit();
		session.close();

	}

}
