package com.springapp.mvc;

import com.springapp.entities.Spitter;
import com.springapp.services.SpitterService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.ConfigFile;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class TestTransactions {
    @Autowired
    SpitterService spitterService;
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = TransactionException.class)
    public void saveSpitterInTransactionWithoutException(Spitter spitterToSave, PrintWriter stream){
        stream.println("<br/>transaction without exception<br/>");
        spitterService.saveSpitter(spitterToSave);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = TransactionException.class)
    public void transactionWithException(Spitter spitterToSave, PrintWriter stream){
        stream.println("<br/>transaction with exception<br/>");
        spitterService.saveSpitter(spitterToSave);
        throw new TransactionException("This exception should rollback transaction");
    }
}
