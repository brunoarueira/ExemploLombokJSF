package br.lombokjsf.example.persistence;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class NoticiaPersistence<E> {
    private EntityManager em;
    private String nameJNDI;
    
    public NoticiaPersistence() {
    	em = buscaEntityManager("ExemploLombokJSF");
    }
    
    public void save(E e) {
        em.persist(e);

        em.joinTransaction();
    }

    public void update(E e) {
        em.merge(e);
        em.joinTransaction();
    }

    public void delete(E e) {
        em.merge(e);

        em.remove(e);

        em.joinTransaction();
    }

    public E find(Class<E> c, Object pk) {
        return em.find(c, pk);

    }

    @SuppressWarnings("unchecked")
    public LinkedHashSet<E> findAll(String c, String tipo, String query) {

    	Query q = em.createQuery("select c from " + c + " c where c." + tipo +
    			" like '%" + query.trim() + "%' and c.status = 'A' order by c." + tipo).setHint("org.hibernate.cacheable", true);

    	LinkedHashSet<E> resultList = new LinkedHashSet(q.getResultList());
        em.clear();

        return resultList;
    }

    private EntityManager buscaEntityManager(String nameJNDI) {
        EntityManager result = null;
        this.setNameJNDI(nameJNDI);

        try {
            javax.naming.InitialContext ic = new javax.naming.InitialContext();
            EntityManagerFactory emf = (EntityManagerFactory) ic.lookup(
                    "java:/" + nameJNDI + "Factory");
            result = emf.createEntityManager();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public LinkedHashSet<E> findSQLCreate(LinkedHashMap<String, String> params) {
        Set keyParams = params.keySet();
        Iterator iTer = keyParams.iterator();

        String sqlFrom = " Select c from ";
        String sqlValue = " where c.status = 'A' and ";
        String key;
        String param;
      
        while (iTer.hasNext()) {
            key = (String) iTer.next();
            param = (String) params.get(key) + " ";

            if (key.equalsIgnoreCase("class")) {
                sqlFrom += (param + " c"); 
            } else {
                sqlValue += ("c." + key + " = '" + param.trim() + "' and ");
            }
        }

        sqlValue = sqlValue.substring(0, sqlValue.length() - 4);

        sqlFrom += sqlValue;
       
        Query query = em.createQuery(sqlFrom);

        LinkedHashSet<E> resultList = new LinkedHashSet(query.setHint("org.hibernate.cacheable", true).getResultList());
              
        return resultList;
    }

    @SuppressWarnings("unchecked")
    public LinkedHashSet<E> findSQLCreate(String params) {
    	LinkedHashSet<E> resultList = new LinkedHashSet(em.createQuery(params).setHint("org.hibernate.cacheable", true).getResultList());
        em.clear();

        return resultList;
    }

    public void setNameJNDI(String nameJNDI) {
        this.nameJNDI = nameJNDI;
    }

    public String getNameJNDI() {
        return this.nameJNDI;
    }

	public EntityManager getEm() {
		return em;
	}	
}