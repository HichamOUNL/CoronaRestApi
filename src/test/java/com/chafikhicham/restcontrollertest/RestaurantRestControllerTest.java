package com.chafikhicham.restcontrollertest;

import com.chafikhicham.AbstractTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.chafikhicham.dataaccess.entities.Restaurant;
import com.chafikhicham.rest.dto.RestaurantDTO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@FixMethodOrder(MethodSorters.JVM)
public class RestaurantRestControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createRestaurant() throws Exception {
        String uri = "/rest/v1/restaurants";
        RestaurantDTO restaurantDTO = new RestaurantDTO().setName("WokToGo").setAddress("Ontwikkelstraat 42, 1051AE Moerdijk");
        String inputJson = super.mapToJson(restaurantDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getRestaurant() throws Exception {
        String uri = "/rest/v1/restaurants";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Restaurant[] restaurantlist = super.mapFromJson(content, Restaurant[].class);
        assertTrue(restaurantlist.length > 0);
    }

    @Test
    public void updateRestaurant() throws Exception {
        String uri = "/rest/v1/restaurants/1";
        RestaurantDTO restaurantDTO = new RestaurantDTO().setName("NewName").setAddress("Ontwikkelstraat 42, 1051AE Moerdijk");

        String inputJson = super.mapToJson(restaurantDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void deleteRestaurant() throws Exception {
        String uri = "/rest/v1/restaurants/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
    }
}
