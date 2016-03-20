package com.springapp.mvc;

import com.springapp.entities.Spittle;
import com.springapp.services.SpitterService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeControllerTest {
    @Test
    public void shouldDisplayRecentSpittles(){
        List<Spittle> expectedSpittles = Arrays.asList(
                new Spittle(), new Spittle(), new Spittle());
        //dummy SpitterService
        SpitterService spitterService = Mockito.mock(SpitterService.class);
        Mockito.when(spitterService.getRecentSpittles(HomeController.DEFAULT_SPITTLES_PER_PAGE)).
                thenReturn(expectedSpittles);
        ///
        HomeController controller = new HomeController(spitterService);

        ModelMap model = new ModelMap();

        //call handler
        String viewName = controller.showHomePage(model);

        //check results
        Assert.assertEquals("home", viewName);
        Assert.assertSame(expectedSpittles, model.get("spittles"));
        Mockito.verify(spitterService).getRecentSpittles(HomeController.DEFAULT_SPITTLES_PER_PAGE);
    }
}
