package com.dev;

import com.dev.objects.AssociationObject;
import com.dev.objects.SaleObject;
import com.dev.objects.StoreAssociation;
import com.dev.objects.StoreObject;
import com.dev.objects.UserAssociation;
import com.dev.objects.UserObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

@Service
public class Persist {
    private final SessionFactory sessionFactory;

    @Autowired
    public Persist (SessionFactory sf) {
        this.sessionFactory = sf;
    }


    public String getTokenByUsernameAndPassword(String username, String password) {
        String token = null;
        Session session = sessionFactory.openSession();
        UserObject userObject = (UserObject) session.createQuery("FROM UserObject WHERE username = :username AND password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .uniqueResult();
        session.close();
        if (userObject != null) {
            token = userObject.getToken();
        }
        return token;
    }

    public boolean addAccount (UserObject userObject) {
        boolean success = false;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(userObject);
        transaction.commit();
        session.close();
        if (userObject.getId() > 0) {
            success = true;
        }
        return success;
    }

    public boolean addAssociation(String token, int associationId){
        boolean success = false;
        UserObject userObject = getUserByToken(token);
        AssociationObject associationObject = getAssociationById(associationId);
        if(userObject != null && associationObject != null){
            UserAssociation userAssociation = new UserAssociation();
            userAssociation.setUserObject(userObject);
            userAssociation.setAssociationObject(associationObject);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(userAssociation);
            transaction.commit();
            session.close();
            if (userAssociation.getId() > 0) {
                success = true;
            }
        }

        return success;
    }
    public boolean removeAssociation(String token, int associationId){
        boolean success = false;
        UserObject userObject = getUserByToken(token);
        AssociationObject associationObject = getAssociationById(associationId);
        
        if(userObject != null && associationObject != null){
            UserAssociation userAssociation = getUserAssociation(userObject.getId(), associationId);
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(userAssociation);
            transaction.commit();
            session.close();
            if (userAssociation.getId() > 0) {
                success = true;
            }
        }

        return success;
    }


    public Integer getUserIdByToken (String token) {
        Integer id = null;
        Session session = sessionFactory.openSession();
        UserObject userObject = (UserObject) session.createQuery("FROM UserObject WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        if (userObject != null) {
            id = userObject.getId();
        }
        return id;
    }
    public UserObject getUserByToken (String token) {
        Session session = sessionFactory.openSession();
        UserObject userObject = (UserObject) session.createQuery("FROM UserObject WHERE token = :token")
                .setParameter("token", token)
                .uniqueResult();
        session.close();
        return userObject;
    }

    public AssociationObject getAssociationById(int associationId){
        Session session = sessionFactory.openSession();
        AssociationObject associationObject = (AssociationObject)session.createQuery("FROM AssociationObject WHERE id = :id")
            .setParameter("id", associationId)
            .uniqueResult();
        session.close();
        return associationObject;
    }
    public UserAssociation getUserAssociation(int userId, int associationId){
        String query = "FROM UserAssociation WHERE userId = :userId and associationId = :associationId";
        Session session = sessionFactory.openSession();
        UserAssociation userAssociation = (UserAssociation)session.createQuery(query)
            .setParameter("userId", userId)
            .setParameter("associationId", associationId)
            .uniqueResult();
        session.close();
        return userAssociation;
    }

    public List<StoreAssociation> getUserStores(String token){
        List<AssociationObject> associationList = getAssociations(token);
        List<StoreAssociation>  storeList = new LinkedList<>();

        for(AssociationObject association : associationList){
            List<StoreAssociation>  currentStoreList = getStores(association);
            storeList.addAll(currentStoreList);
        }
        return storeList;
    }

    public List<AssociationObject> getAssociations(){
        List<AssociationObject> associationObjects = null;
        Session session = sessionFactory.openSession();
        associationObjects = (List<AssociationObject>) session.createQuery(
            "FROM AssociationObject ")
            .list();
        session.close();
        return associationObjects;
    }

    public List<AssociationObject> getNonUserAssociations(String token){
        List<AssociationObject> allAssociations = getAssociations();
        List<AssociationObject> userAssociations = getAssociations(token);
        Set<AssociationObject> set = new HashSet<>();
        set.addAll(allAssociations);
        set.removeAll(userAssociations);

        List<AssociationObject> result = new ArrayList<>(set);

        return result;
    }

    public List<AssociationObject> getAssociations(String token){
        int userId = getUserIdByToken(token);
        List<AssociationObject> associationList = new LinkedList<>();
        List<UserAssociation> userAssociationList = null;

        Session session = sessionFactory.openSession();
        String query = "FROM UserAssociation where userId = :userId";
        userAssociationList = (List<UserAssociation>) session.createQuery(query)
        .setParameter("userId", userId).list();
        session.close();
        for(UserAssociation userAssociation : userAssociationList){
            associationList.add(userAssociation.getAssociationObject());
        }
        return associationList;
    }
     
    public List<StoreAssociation> getStores(AssociationObject association){
        List<StoreAssociation> storeList = null;

        Session session = sessionFactory.openSession();
        String query = "FROM StoreAssociation WHERE associationObject = :association";
        storeList = (List<StoreAssociation>) session.createQuery(query).setParameter("association", association).list();
        session.close();
        return storeList;
    }
     
    public StoreObject getStore(int storeId){
        StoreObject store = null;

        Session session = sessionFactory.openSession();
        String query = "FROM StoreObject WHERE id = :storeId";
        store = (StoreObject) session.createQuery(query).setParameter("storeId", storeId).uniqueResult();
        session.close();
        return store;
    }

    public List<StoreObject> getStores(){
        List<StoreObject> storeList = null;

        Session session = sessionFactory.openSession();
        String query = "FROM StoreObject ";
        storeList = (List<StoreObject>) session.createQuery(query).list();
        session.close();
        return storeList;
    }

    public Set<SaleObject> getStartingSales (String token){
        List<SaleObject> sales = getSales(token);
        Set<SaleObject> salesStartingToday = new HashSet<SaleObject>();
        LocalDate now = LocalDate.now();
        for(SaleObject sale : sales){  
            if(sale.getSaleStart().equals(now)){
                salesStartingToday.add(sale);
            }
        }
        List<SaleObject> globalSales = getGlobalSales();
        for(SaleObject sale : globalSales){  
            if(sale.getSaleStart().equals(now)){
                salesStartingToday.add(sale);
            }
        }         
        return salesStartingToday;
    }

    public Set<SaleObject> getEndingSales (String token){
        List<SaleObject> sales = getSales(token);
        Set<SaleObject> salesEndingToday = new HashSet<SaleObject>();
        LocalDate now = LocalDate.now();
        for(SaleObject sale : sales){  
            if(sale.getSaleEnd().equals(now)){
                salesEndingToday.add(sale);
            }
        }
        List<SaleObject> globalSales = getGlobalSales();
        for(SaleObject sale : globalSales){  
            if(sale.getSaleEnd().equals(now)){
                salesEndingToday.add(sale);
            }
        }        
        return salesEndingToday;
    }

    public List<SaleObject> getSales(String token){
        // TODO get user associations
        // TODO get all stores for each association
        // TODO get all sales for each stores

        List<AssociationObject> associations = getAssociations(token);
        List<SaleObject> allSales = new ArrayList<SaleObject>();

        for(AssociationObject association : associations){
            List<StoreAssociation> storeAssociations = null;
            Session session = sessionFactory.openSession();
            String query = "FROM StoreAssociation WHERE associationId = :associationId";
            storeAssociations = (List<StoreAssociation>) session.createQuery(query)
                .setParameter("associationId", association.getId()).list();
            session.close();
            for(StoreAssociation storeAssoc : storeAssociations){
                List<SaleObject> sales = getSales(storeAssoc.getStoreObject().getId());
                allSales.addAll(sales);
            }
        }
        return allSales;
    }

    public List<SaleObject> getSales(int storeId){
        List<SaleObject> storeList = null;

        Session session = sessionFactory.openSession();
        String query = "FROM SaleObject where storeId = :storeId";
        storeList = (List<SaleObject>) session.createQuery(query)
        .setParameter("storeId", storeId).list();
        session.close();
        return storeList;
    }  

    public List<SaleObject> searchSales(String text){
        List<SaleObject> storeList = null;


        Session session = sessionFactory.openSession();
        String query = "FROM SaleObject where description like :text";
        storeList = (List<SaleObject>) session.createQuery(query)
        .setParameter("text", "%"+text+"%").list();
        session.close();
        return storeList;
    }  
    
    public List<SaleObject> getGlobalSales(){
        List<SaleObject> storeList = null;

        Session session = sessionFactory.openSession();
        String query = "FROM SaleObject where isGlobal = true";
        storeList = (List<SaleObject>) session.createQuery(query).list();
        session.close();
        return storeList;
    } 


}
