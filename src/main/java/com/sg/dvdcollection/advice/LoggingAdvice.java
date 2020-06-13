/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdcollection.advice;

import com.sg.dvdcollection.dao.DVDCollectionAuditDao;
import com.sg.dvdcollection.dao.DVDCollectionPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author travi
 */
public class LoggingAdvice {
    
    DVDCollectionAuditDao auditDao;
    
    public LoggingAdvice(DVDCollectionAuditDao auditDao){
        this.auditDao = auditDao;
    }
    
     public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName()+ ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDCollectionPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
