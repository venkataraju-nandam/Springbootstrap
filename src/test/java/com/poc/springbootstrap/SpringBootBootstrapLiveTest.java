package com.poc.springbootstrap;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.List;

//import javax.xml.ws.Response;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.springbootstrap.entitymodel.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SpringBootBootstrapLiveTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
//    private static final String API_ROOT    = "http://localhost:8081/api/books";
	
     private static final String API_BASE_ROOT  = "http://localhost:";
    
    private String REST_METHOD_HELLO = "/hello";
    private String REST_METHOD_HOME = "/home";
    private String REST_METHOD_API_BOOKS = "/api/books";
	private  String API_ROOT    = API_BASE_ROOT + port + REST_METHOD_API_BOOKS;
    
	@Test
	public void simpleUriRoot()throws Exception{
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUriRoot() " + new URL(API_BASE_ROOT + port  + "/").toString());
		ResponseEntity<String> response = 
				 restTemplate.getForEntity(new URL(API_BASE_ROOT + port + "/").toString(), String.class);
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUriTest1() " + response.getBody());
		
		logger.error("Executing SpringBootBootstrapLiveTest -> simpleUriRoot() " + new URL(API_BASE_ROOT + port  + "/").toString());
		assertNotEquals("home", response.getBody());
//		assertEquals("home", response.getBody().contains("home"));
		
	}
	
	@Test
	public void simpleUriHome()throws Exception{
		String url = new URL(API_BASE_ROOT + port + REST_METHOD_HOME).toString();
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUriHome() " + url);
		ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUriHome() " + response.getBody());
		
		logger.error("Executing SpringBootBootstrapLiveTest -> simpleUriHome() " + url);
		assertEquals("HOME", response.getBody());
		
	}
	
	@Test
	public void simpleUriHello()throws Exception{
		String url = new URL(API_BASE_ROOT + port + REST_METHOD_HELLO).toString();
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUriHello() " + url);
		ResponseEntity<String> response =  restTemplate.getForEntity(url, String.class);
		logger.info("Executing SpringBootBootstrapLiveTest -> simpleUri() " + response.getBody());
		
		logger.error("Executing SpringBootBootstrapLiveTest -> simpleUriHello() " + url);
		assertEquals("Hello Service", response.getBody());
//		assertEquals("Hello Service", response.getBody().contains("Hello"));
		
		
	}
	
//	@Test
//	public void put_test() {
//		Response response = given()
//				.contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.body("{\"name\": \"Lisa Tamaki\",\"salary\": \"20000\"}")
//				.when()
//				.put(ROOT_URI + "/update/3");
//		System.out.println("PUT Response\n" + response.asString());
//		// tests
//		response.then().body("id", Matchers.is(3));
//		response.then().body("name", Matchers.is("Lisa Tamaki"));
//		response.then().body("salary", Matchers.is("20000"));
//	}
    
    
    private Book createRandomBook() {
        Book book = new Book();
        book.setTitle(RandomStringUtils.randomAlphabetic(10).toString());
        book.setAuthor(RandomStringUtils.randomAlphabetic(15));
		logger.info("-------------------------------------------------- book " + book);

        return book;
    }
 
    private String createBookAsUri(Book book) {
        Response response = RestAssured.given()
          .contentType(MediaType.APPLICATION_JSON_VALUE)
          .body(book)
          .post(API_ROOT);
        
        logger.info("-------------------------------------------------- response " + response.toString());
        
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
//
//    
//    @Test
//    public void whenGetAllBooks_thenOK() {
//        Response response = RestAssured.get(API_ROOT);
//      
//        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//    }
//     
//    @Test
//    public void whenGetBooksByTitle_thenOK() {
//        Book book = createRandomBook();
//        logger.info("-------------------------------------------------- whenGetBooksByTitle_thenOK book " + book);
//        createBookAsUri(book);
//        Response response = RestAssured.get(API_ROOT + "/title/" + book.getTitle());
//        logger.info("-------------------------------------------------- whenGetBooksByTitle_thenOK response " + response.toString());
//        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//        assertTrue(response.as(List.class)
//          .size() > 0);
//    }
//
//    
//    @Test
//    public void whenGetCreatedBookById_thenOK() {
//        Book book = createRandomBook();
//        String location = createBookAsUri(book);
//        Response response = RestAssured.get(location);
//         
//        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//        assertEquals(book.getTitle(), response.jsonPath()
//          .get("title"));
//    }
//     
//    @Test
//    public void whenGetNotExistBookById_thenNotFound() {
//        io.restassured.response.Response response = RestAssured.get(API_ROOT + "/" + RandomStringUtils.randomNumeric(4));
//         
//        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
//    }
//    
//    
//    
//	
//@Test
//public void whenCreateNewBook_thenCreated() {
//    Book book = createRandomBook();
//    Response response = RestAssured.given()
//      .contentType(MediaType.APPLICATION_JSON_VALUE)
//      .body(book)
//      .post(API_ROOT);
//     
//    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
//}
// 
//@Test
//public void whenInvalidBook_thenError() {
//    Book book = createRandomBook();
//    book.setAuthor(null);
//    Response response = RestAssured.given()
//      .contentType(MediaType.APPLICATION_JSON_VALUE)
//      .body(book)
//      .post(API_ROOT);
//     
//    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
//}
//@Test
//public void whenUpdateCreatedBook_thenUpdated() {
//    Book book = createRandomBook();
//    String location = createBookAsUri(book);
//    book.setId(Long.parseLong(location.split("api/books/")[1]));
//    book.setAuthor("newAuthor");
//    Response response = RestAssured.given()
//      .contentType(MediaType.APPLICATION_JSON_VALUE)
//      .body(book)
//      .put(location);
//     
//    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
// 
//    response = RestAssured.get(location);
//     
//    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
//    assertEquals("newAuthor", response.jsonPath()
//      .get("author"));
//}
//@Test
//public void whenDeleteCreatedBook_thenOk() {
//    Book book = createRandomBook();
//    String location = createBookAsUri(book);
//    Response response = RestAssured.delete(location);
//     
//    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
// 
//    response = RestAssured.get(location);
//    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
//}

    
}
