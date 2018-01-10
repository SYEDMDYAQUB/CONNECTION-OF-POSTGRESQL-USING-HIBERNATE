import org.hibernate.SQLQuery;

import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;

import javax.imageio.spi.ServiceRegistry;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Supplier{
 @Id
 @	Column(name="Supplier_id")
 @GeneratedValue(strategy=GenerationType.AUTO)
 private int supplierId;
 @	Column(name="Supplier_Phone_number")
 private int supplierphoneno; 
 public int getSupplierId() {
	return supplierId;
}
public void setSupplierId(int supplierId) {
	this.supplierId = supplierId;
}
public int getSupplierphoneno() {
	return supplierphoneno;
}
public void setSupplierphoneno(int supplierphoneno) {
	this.supplierphoneno = supplierphoneno;
}

//<---->DISPLAY THE CONTENTS OF THE TABLE
public static void print1(Session session, String query)
{
	SQLQuery qry = session.createSQLQuery(query);
	List l =qry.list();
	 System.out.println("Total Number Of Records : "+l.size());
	 Iterator it = l.iterator();
	 
	 
	 while(it.hasNext())
	 {
	 Object o[] = (Object[])it.next();
	 
	 for(Object item:o)
	 {
		 System.out.print(item+"   |   ");
	 }
	 System.out.println();
	 //System.out.println("Supplierid : "+o[0]+ "\t Supplierphoneno : "+o[1]);
	 
	 } 
}


//<----->INSERTING IN THE TABLE
public static void insert(Session session) 
{
	Supplier suppi=new Supplier();
	 //suppi.setSupplierId(110);
	 suppi.setSupplierphoneno(12345);
	 session.save(suppi);
	 session.update(suppi);
}

//<----->DELETION FROM A TABLE
public static void delete(Session session)
{
	
	/*Supplier cat = new Supplier();
	 cat.setSupplierId(10);
	 session.delete(cat);*/
	 //ORRR
	 SQLQuery del=session.createSQLQuery("delete from supplier where supplier_id > 0");
     del.executeUpdate();
     
}
	public static void main(String[] args){
 try
 {
	 SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
	 Session session = factory.openSession();
     Transaction tx=session.beginTransaction();
	 
	 //insert(session);
	 	 

	String query1="select * from \"supplier\" ";
	 //print1(session,query1);
	 
	delete(session);
	insert(session);
	 
	 print1(session,query1);
	 
 tx.commit();
 session.flush();

 session.close();
 factory.close();
 }
 catch(Exception e) {
	 System.out.println(e.getMessage());
 } 
}

}