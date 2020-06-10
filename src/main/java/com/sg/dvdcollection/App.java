/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection;

import com.sg.dvdcollection.controller.DVDCollectionController;
import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author travi
 */
public class App {
    public static void main(String[] args) throws DVDCollectionPersistenceException {
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDCollectionController controller = ctx.getBean("controller", DVDCollectionController.class);
        controller.run();
        
        //UserIO myIo = new UserIOConsoleImpl();        
        //DVDCollectionView myView = new DVDCollectionView(myIo); 
        //DVDCollectionDao myDao = new DVDCollectionDaoFileImpl();        
        //DVDCollectionController controller = 
                //new DVDCollectionController(myDao, myView);
        //controller.run();
    }
}
