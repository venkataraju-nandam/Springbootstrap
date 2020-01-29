package com.poc.springbootstrap.controller;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.poc.springbootstrap.aop.BookIdMismatchException;
import com.poc.springbootstrap.aop.BookNotFoundException;
import com.poc.springbootstrap.entitymodel.Book;
import com.poc.springbootstrap.repository.BookRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//http://localhost:8081/api/books/1

@RestController
@RequestMapping("/api/books")
public class BookControllerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private BookRepository bookRepository;
 
    @GetMapping("/findall-books")
    public @ResponseBody Iterable<Book> findAll_Books() {
        return bookRepository.findAll();
    }
        
    @GetMapping("/find-books")
    public Iterable<Book> findAll() {
    	logger.info("Find All : ............................... " );
    	logger.info(bookRepository.findAll().toString());
        return bookRepository.findAll();
    }
    
    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }
    
    @GetMapping("/{id}")
    public Optional<Book> findOne(@PathVariable Long id) {
        return bookRepository.findById(id);
//          .orElseThrow(BookNotFoundException::new);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	bookRepository.findById(id);
//        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }
    
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
//lombok repository automatically generate setter and getters for entity elements
        if (book.getId() != id) {
          throw new BookIdMismatchException(null, null);
        }
        bookRepository.findById(id);
//          .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
    
      
    
//    @PostMapping( value = "/createPerson", consumes = "application/json", produces = "application/json")
    @PostMapping(path= "/add-book", consumes = "application/json", produces = "application/json")
    public String addBook(@RequestBody Book book) 
//            throws Exception 
    {   
    	try {
    		
	    	 logger.info("Inserting Object : ............................... "+book );
	    	 Book addedBook = bookRepository.save(book);
	    	 logger.info("Inserted Object : ............................... " +addedBook);
	    	 return addedBook.toString();
//    	}catch(SQLException sqle) {
    	}catch(Exception sqle) {
//    		logger.info("Inserting Object : ............................... "+sqle.getErrorCode() );
    		logger.info("Inserting Object Error message : ............................... "+sqle.getMessage() );
    		logger.info("Inserting Object Error String : ............................... "+sqle.toString() );
    		logger.info("Inserting Object Error cause : ............................... "+sqle.getCause() );
    		return (sqle.toString() +"\n" + sqle.getMessage());
    	}

    }
    
//    @PostMapping("save-book")
//    public ResponseEntity<Void> addArticle(@RequestBody Book bookentity, UriComponentsBuilder builder) {
//           Book book = bookRepository.save(bookentity);
//           
//           boolean flag =  bookRepository.save(book);
//           if (flag == false) {
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//           }
//           HttpHeaders headers = new HttpHeaders();
//           headers.setLocation(builder.path("/article/{id}").buildAndExpand(book.getId).toUri());
//           return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
    
    
    
    
//    REST API with headers
//    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Object> addEmployee (
//            @RequestHeader(name = "X-COM-PERSIST", required = true) String headerPersist,
//            @RequestHeader(name = "X-COM-LOCATION", defaultValue = "ASIA") String headerLocation,
//            @RequestBody Employee employee ) throws Exception 
//    {       
//        //
//	 
//	
//  //Generate resource id
//  Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
//  employee.setId(id);
//   
//  //add resource
//  employeeDao.addEmployee(employee);
//   
//  //Create resource location
//  URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                              .path("/{id}")
//                              .buildAndExpand(employee.getId())
//                              .toUri();
//   
//  //Send location in response
//  return ResponseEntity.created(location).build();
//    }
    
    
}
