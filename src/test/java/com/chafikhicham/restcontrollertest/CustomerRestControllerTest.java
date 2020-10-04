package com.chafikhicham.restcontrollertest;

import com.chafikhicham.AbstractTest;
import com.chafikhicham.dataaccess.entities.Customer;

import com.chafikhicham.rest.dto.CustomerDTO;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.JVM)
public class CustomerRestControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createCustomer() throws Exception {
        String uri = "/rest/v1/customers";
        CustomerDTO customerDTO = new CustomerDTO().setName("John").setLastName("Doe").setPhoneNumber("123456").setInfected(false);
        String inputJson = super.mapToJson(customerDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void getCustomer() throws Exception {
        String uri = "/rest/v1/customers";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Customer[] customerlist = super.mapFromJson(content, Customer[].class);
        assertTrue(customerlist.length > 0);
    }

    @Test
    public void updateCustomer() throws Exception {
        String uri = "/rest/v1/customers/1";
        CustomerDTO customerDTO = new CustomerDTO().setName("Johnny").setLastName("Doe").setPhoneNumber("123456").setInfected(false);

        String inputJson = super.mapToJson(customerDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void deleteCustomer() throws Exception {
        String uri = "/rest/v1/customers/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
        String content = mvcResult.getResponse().getContentAsString();
    }
}
