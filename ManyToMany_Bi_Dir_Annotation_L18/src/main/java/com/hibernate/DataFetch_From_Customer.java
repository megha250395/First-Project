package com.hibernate;

import java.util.Collection;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DataFetch_From_Customer {

	/**
	 * @author Amit Sinha
	 * fetching Data from Customer(main) along with Request(child)
	 * 
	 * Query & Result
	 * Hibernate: select customer0_.cid as cid1_1_0_, customer0_.email as email2_1_0_, customer0_.firstName as firstNam3_1_0_, customer0_.lastName as lastName4_1_0_, customer0_.phone as phone5_1_0_ from customers customer0_ where customer0_.cid=?
	 * Customer - Customer [cid=3, firstName=vikas, lastName=chandra, email=vikas@mail, phone=6546856468]
	 * Hibernate: select requests0_.st_FK as st_FK1_1_0_, requests0_.inv_FK as inv_FK2_0_0_, request1_.req_id as req_id1_2_1_, request1_.description as descript2_2_1_, request1_.reqDate as reqDate3_2_1_, request1_.status as status4_2_1_ from cust_req requests0_ inner join request request1_ on requests0_.inv_FK=request1_.req_id where requests0_.st_FK=?
	 * Request - Request [reqId=2, reqDate=2018-07-09, description=Items, status=Deleverd]
	 * Request - Request [reqId=1, reqDate=2018-07-07, description=Product, status=Pending]
	 * Request - Request [reqId=3, reqDate=2018-08-19, description=Food, status=shipped]

	 */
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Customer cust=(Customer)session.load(Customer.class, 3);
		System.out.println("Customer - "+cust);
		
		Set<Request> col=cust.getRequests();
		for(Request req:col)
		System.out.println("Request - "+req);
		
		tx.commit();
		session.close();

	}

}
