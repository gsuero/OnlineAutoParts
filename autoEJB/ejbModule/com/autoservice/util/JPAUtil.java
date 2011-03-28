package com.autoservice.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {

	protected EntityManagerFactory emf;

	public EntityManagerFactory getEMF (){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("myAutoService");
        }
        return emf;
    }
}

