package tech.mahadi.moneybook.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tech.mahadi.moneybook.entity.Book;
import tech.mahadi.moneybook.entity.CashFlow;
import tech.mahadi.moneybook.entity.Category;
import tech.mahadi.moneybook.entity.Contact;
import tech.mahadi.moneybook.service.MoneyBookService;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/moneybook")
@RequiredArgsConstructor
public class MoneyBookController {
    private final MoneyBookService moneyBookService;
    @GetMapping("/test")
    public String test(){
        System.out.println(new Date());
        return "Welcome Home!";
    }

    //Book Controller
    @PostMapping("/book/create")
    public String createNewBook(@RequestBody Book book){
        return moneyBookService.createNewBook(book);
    }
    @GetMapping("/book/all")
    public List<Book> fetchAllBooks() { return moneyBookService.fetchAllBooks(); }

    @GetMapping("/book/{id}")
    public Book fetchBookById(@PathVariable("id") Long id){
        return moneyBookService.fetchBookById(id);
    }
    @PutMapping("/book/update/{id}")
    public String updateBook(@PathVariable ("id") Long id, @RequestBody Book book){
        return moneyBookService.updateBook(id, book);
    }
    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        return moneyBookService.deleteBook(id);
    }

    //CashFlow Controller
    @PostMapping("/cashflow/create")
    public String createNewCashFlow(@RequestBody CashFlow cashFlow){
        return moneyBookService.createNewCashFlow(cashFlow);
    }
    @GetMapping("/cashflow/all")
    public List<CashFlow> fetchAllCashFlows(){
        return moneyBookService.fetchAllCashFlows();
    }
    @GetMapping("/cashflow/{id}")
    public CashFlow fetchCashFlow(@PathVariable("id") Long id){
        return moneyBookService.fetchCashFlow(id);
    }
    @PutMapping("/cashflow/update/{id}")
    public String updateCashFlow(@PathVariable("id") Long id, @RequestBody CashFlow cashFlow){
        return moneyBookService.updateCashFlow(id, cashFlow);
    }
    @DeleteMapping("/cashflow/delete/{id}")
    public String deleteCashFlow(@PathVariable("id") Long id){
        return moneyBookService.deleteCashFlow(id);
    }

    //Category Controller
    @PostMapping("/category/create")
    public String createNewCategory(@RequestBody Category category){
        return moneyBookService.createNewCategory(category);
    }
    @GetMapping("/category/all")
    public List<Category> fetchAllCategories(){
        return moneyBookService.fetchAllCategories();
    }
    @DeleteMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        return moneyBookService.deleteCategory(id);
    }

    //Contact Controller
    @PostMapping("/contact/create")
    public String createNewContact(@RequestBody Contact contact){
        return moneyBookService.createNewContact(contact);
    }
    @GetMapping("/contact/all")
    public List<Contact> fetchAllContacts(){
        return moneyBookService.fetchAllContacts();
    }
    @DeleteMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable("id") Long id){
        return moneyBookService.deleteContact(id);
    }
}
