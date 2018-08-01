package com.hibernate;

import java.util.Collection;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DataFetch_From_Request {

	/**
	 * @author Amit Sinha
	 * fetching Data from Request(child) along with Customer(main)
	 * 
	 * Query & Result
	 * 
	 * Hibernate: select request0_.req_id as req_id1_2_0_, request0_.description as descript2_2_0_, request0_.reqDate as reqDate3_2_0_, request0_.status as status4_2_0_ from request request0_ where request0_.req_id=?
 	 * Request - Request [reqId=2, reqDate=2018-07-09, description=Items, status=Deleverd]
	 * Hibernate: select customer0_.inv_FK as inv_FK2_2_0_, customer0_.st_FK as st_FK1_0_0_, customer1_.cid as cid1_1_1_, customer1_.email as email2_1_1_, customer1_.firstName as firstNam3_1_1_, customer1_.lastName as lastName4_1_1_, customer1_.phone as phone5_1_1_ from cust_req customer0_ inner join customers customer1_ on customer0_.st_FK=customer1_.cid where customer0_.inv_FK=?
	 * Customer - Customer [cid=2, firstName=abhi, lastName=Sinha, email=abhi@mail, phone=98684685]
	 * Customer - Customer [cid=1, firstName=amit, lastName=Sinha, email=amit@mail, phone=8495036559]
	 * Customer - Customer [cid=3, firstName=vikas, lastName=chandra, email=vikas@mail, phone=6546856468]

	 */
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Request req=(Request)session.load(Request.class, 2);
		System.out.println("Request - "+req);
		
		Set<Customer> cust=req.getCustomer();
		for(Customer c:cust)
			System.out.println("Customer - "+c);
		
		
		
		tx.commit();
		session.close();

	}

}
