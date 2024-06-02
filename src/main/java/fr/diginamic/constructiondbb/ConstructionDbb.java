package fr.diginamic.constructiondbb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConstructionDbb {
	
	
	
	public static void constructionDbbStart () {
		
		EntityManagerFactory EntityManagerFactory;
		EntityManager emConstruction = null;
		
		try {
			EntityManagerFactory = Persistence.createEntityManagerFactory("foot-create");
			emConstruction = EntityManagerFactory.createEntityManager();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (emConstruction!=null) {
				emConstruction.close();
			}
		}
	}

}
