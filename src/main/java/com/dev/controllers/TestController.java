package com.dev.controllers;

import com.dev.Persist;
import com.dev.objects.AssociationObject;
import com.dev.objects.PostObject;
import com.dev.objects.SaleObject;
import com.dev.objects.StoreAssociation;
import com.dev.objects.StoreObject;
import com.dev.objects.UserObject;
import com.dev.utils.Utils;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.List;


@RestController
@RequestMapping("user")
public class TestController {


    @Autowired
    private Persist persist;

    @PostConstruct
    private void init () {

    }

    @RequestMapping("sign-in")
    public String signIn (String username, String password) {
        String token = persist.getTokenByUsernameAndPassword(username, password);
        return token;
    }

    @RequestMapping("create-account")
    public boolean createAccount (String username, String password) {
        boolean success = false;
        boolean alreadyExists = persist.usernameExists(username);
        if (!alreadyExists) {
            UserObject userObject = new UserObject();
            userObject.setUsername(username);
            userObject.setPassword(password);
            String hash = Utils.createHash(username, password);
            userObject.setToken(hash);
            success = persist.addAccount(userObject);
        }

        return success;
    }

    @RequestMapping(value="add-association", method = RequestMethod.POST)
    public boolean addAssociation( String token, Integer associationId){
        boolean success = false;
        System.out.println(token);
        System.out.println(associationId);
        if(token == null){
            return success;
        }
        
        success = persist.addAssociation(token, associationId);
        return success;
    }
    @RequestMapping(value="remove-association", method = RequestMethod.POST)
    public boolean removeAssociation( String token, Integer associationId){
        boolean success = false;
        System.out.println(token);
        System.out.println(associationId);
        if(token == null){
            return success;
        }
        
        success = persist.removeAssociation(token, associationId);
        return success;
    }

   

    @RequestMapping("get-all-associations")
    public List<AssociationObject> getAssociation(){
        return persist.getAssociations();
    }

    @RequestMapping("get-associations")
    public List<AssociationObject> getAssociation(String token){
        return persist.getAssociations(token);
    }

    @RequestMapping("get-non-user-associations")
    public List<AssociationObject> getNonUserAssociation(String token){
        return persist.getNonUserAssociations(token);
    }

    @RequestMapping("get-store-details")
    public StoreObject getStore(int storeId){
        return persist.getStore(storeId);
    }

    @RequestMapping("get-stores")
    public List<StoreAssociation> getStore(String token){
        return persist.getUserStores(token);
    }

    @RequestMapping("get-all-stores")
    public List<StoreObject> getAllStore(String token){
        return persist.getStores();
    }

    @RequestMapping("get-store-sales")
    public List<SaleObject> getStoreSales(int storeId){
        return persist.getSales(storeId);
    }    
    @RequestMapping("get-sales")
    public List<SaleObject> getSales(String token){
        return persist.getSales(token);
    }
    @RequestMapping("search-sales")
    public List<SaleObject> getSales(String token, String search){
        return persist.searchSales(search);
    }

}
