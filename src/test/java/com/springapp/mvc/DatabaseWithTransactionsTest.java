package com.springapp.mvc;

import com.springapp.entities.Spitter;
import com.springapp.entities.Spittle;
import com.springapp.services.DefaultSpitterService;
import com.springapp.services.SpitterService;
import org.junit.After;
import org.junit.Assert;
import org.hibernate.TransactionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import sun.security.provider.ConfigFile;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
"file:src/main/resources/dataSource-context.xml",
        "file:src/main/resources/persistence-context.xml"})
public class DatabaseWithTransactionsTest {
    //private MockMvc mockMvc;not needed yet
    @After
    public void print() {
        System.out.println(stringWriter.toString());
        stringWriter.getBuffer().setLength(0);
    }

    /*@SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
        //stringWriter = new StringWriter();
        //new PrintWriter(stringWriter);
    }not needed yet too
*/
    /*@Test
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"));
    }not needed
    */
    @Autowired
    TestTransactions testTransactions;
    StringWriter stringWriter = new StringWriter();
    PrintWriter stream = new PrintWriter(stringWriter);
    @Autowired
    SpitterService spitterService;

    //@Test
    public void fillDataBase(){
        if (false){
            Spitter spitter1 = new Spitter();
            List<Spittle> spittleList1 = new ArrayList<Spittle>(Arrays.asList(
                    new Spittle("sp123 ", spitter1), new Spittle("s1234", spitter1),
                    new Spittle("sp124 ", spitter1), new Spittle("spsa", spitter1)
            ));
            spitter1.setUsername("spitter4");
            spitter1.setSpittles(spittleList1);
            spitterService.saveSpitter(spitter1);
            Spitter spitter2 = new Spitter();
            List<Spittle> spittleList2 = new ArrayList<Spittle>(Arrays.asList(
                    new Spittle("sp5", spitter1), new Spittle("sp6", spitter1),
                    new Spittle("sp7", spitter1), new Spittle("sp8", spitter1)
            ));
            spitter2.setUsername("spitter3");
            spitter2.setSpittles(spittleList2);
            spitterService.saveSpitter(spitter2);
            for (Spittle spittle : spittleList1) {
                spitterService.saveSpittle(spittle);
            }
            for (Spittle spittle : spittleList2) {
                spitterService.saveSpittle(spittle);
            }

        }
    }

    @Test
    public void testTransactionWithException() {
        Spitter savedSpitter = new Spitter("Spitter for test transaction with exception", null);
        Spitter gotSpitter;
        //savedSpitter has no id
        stream.println("savedSpitter before trying to save: " + savedSpitter);
        try {
            //testing transaction
            testTransactions.transactionWithException(savedSpitter, stream);
        } catch (TransactionException e) {
            //catching exception
            //it must be thrown
            stream.println("Cached exception: " + e);
        }
        //savedSpitter got an id
        stream.println("savedSpitter after trying to save: " + savedSpitter);
        //getting spitter that equals savedSpitter
        gotSpitter = spitterService.getSpitterById(savedSpitter.getId());
        stream.println("gotSpitter: " + gotSpitter);
        //gotSpitter must equals null
        Assert.assertEquals(null, gotSpitter);
    }

    @Test
    public void testTransactionWithoutException() {
        Spitter savedSpitter = new Spitter("Spitter for test transaction without exception", null);
        Spitter gotSpitter;
        //saved spitter has no id
        stream.println("savedSpitter before trying to save: " + savedSpitter);
        //transaction
        testTransactions.saveSpitterInTransactionWithoutException(savedSpitter, stream);
        //getting savedSpitter
        stream.println("savedSpitter:" + savedSpitter);
        //gotSpitter must equals savedSpitter
        gotSpitter = spitterService.getSpitterById(savedSpitter.getId());
        //checking if gotSpitter equals savedSpitter
        Assert.assertEquals(true, gotSpitter.equals(savedSpitter));
        stream.println("gotSpitter:" + gotSpitter);
        //deleting savedSpitter (and gotSpitter, they must equals,
        //of course, if everything all is OK)
        spitterService.deleteSpitter(savedSpitter);
        //getting savedSpitter again, after deleting
        gotSpitter = spitterService.getSpitterById(savedSpitter.getId());
        stream.println("After deleting savedSpitter, gotSpitter: " + gotSpitter);
        //checking if savedSpitter has been deleted
        Assert.assertEquals(null, gotSpitter);

    }
}
