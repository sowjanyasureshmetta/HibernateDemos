package org.gelm.client;
import org.gelm.entities.Agent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateMain {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      
      try {
         factory = new Configuration().configure("hibernate-cfg.xml").buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      Agent agent = new Agent();

      /*agent.setAgentId("GELM099"); 
      agent.setAgentName("Sachin Tendulkar");
      agent.setAgentBranch("Mumbai");*/
      agent.setAgentId("GELM098"); 
      agent.setAgentName("Sourav Ganguly");
      agent.setAgentBranch("Kolkata"); 

      Session session = factory.openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(agent); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      
   }
   
}