package tech.mahadi.moneybook.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.mahadi.moneybook.controller.UserController;
import tech.mahadi.moneybook.entity.*;
import tech.mahadi.moneybook.enumeration.CashFlowType;
import tech.mahadi.moneybook.repository.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MoneyBookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final ContactRepository contactRepository;
    private final CashFlowRepository cashFlowRepository;
    //Book Service
    public String createNewBook(Book book) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow();
        book.setUser(user);
        bookRepository.save(book);
        return "New Book Successfully Created!";
    }
    public List<Book> fetchAllBooks() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElse(null);
        if(user == null) return null;
        return bookRepository.findAllBookByUserId(user.getId());
    }

    public Book fetchBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public String updateBook(Long id, Book book) {
        Book b = bookRepository.findById(id).get();
        if(Objects.nonNull(book.getName()) && !"".equalsIgnoreCase(book.getName())){
            b.setName(book.getName());
        }
        if(Objects.nonNull(book.getCashIn())){
            b.setCashIn(book.getCashIn());
        }
        if(Objects.nonNull(book.getCashOut())){
            b.setCashOut(book.getCashOut());
        }
        if(Objects.nonNull(book.getBalance())){
            b.setBalance(book.getBalance());
        }
        bookRepository.save(b);
        return "Book Successfully Updated!";
    }

    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        return "Book Successfully Deleted!";
    }

    //Category Service
    public String createNewCategory(Category category) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow();
        category.setUser(user);
        categoryRepository.save(category);
        return "New Category Successfully Created!";
    }
    public List<Category> fetchAllCategories() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElse(null);
        if(user == null) return null;
        return categoryRepository.findAllCategoryByUserId(user.getId());
    }
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category Successfully Deleted!";
    }

    //Contact Service
    public String createNewContact(Contact contact) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElseThrow();
        contact.setUser(user);
        contactRepository.save(contact);
        return "New Contact Successfully Created!";
    }
    public List<Contact> fetchAllContacts() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElse(null);
        if(user == null) return null;
        return contactRepository.findAllContactByUserId(user.getId());
    }
    public String deleteContact(Long id) {
        contactRepository.deleteById(id);
        return "Contact Successfully Deleted!";
    }

    //CashFlow Service
    public String createNewCashFlow(CashFlow cashFlow) {
        final Integer val;
        final Long book_id = cashFlow.getBook().getId();
        Book book = bookRepository.findById(book_id).get();
        if(cashFlow.getCashFlowType().equals(CashFlowType.CashIn)){
            val = cashFlow.getAmount()+book.getCashIn();
            book.setCashIn(val);
        } else{
            val = cashFlow.getAmount()+book.getCashOut();
            book.setCashOut(val);
        }
        book.setBalance(book.getCashIn()-book.getCashOut());
        bookRepository.save(book);
        cashFlow.setBook(book);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElse(null);
        cashFlow.setDateTime(new Date());
        cashFlow.setUser(user);
        cashFlowRepository.save(cashFlow);
        return "New CashFlow Successfully added!";
    }

    public List<CashFlow> fetchAllCashFlows() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username).orElse(null);
        if(user == null) return null;
        return cashFlowRepository.findAllCashFlowsByUserId(user.getId());
    }

    public CashFlow fetchCashFlow(Long id) {
        return cashFlowRepository.findById(id).get();
    }

    public String updateCashFlow(Long id, CashFlow cashFlow) {
        CashFlow cf = cashFlowRepository.findById(id).get();
        if(Objects.nonNull(cashFlow.getDetails()) && !"".equalsIgnoreCase(cashFlow.getDetails())){
            cf.setDetails(cashFlow.getDetails());
        }
        if(Objects.nonNull(cashFlow.getAmount()) && cf.getAmount() != cashFlow.getAmount()){
            final Integer val = cashFlow.getAmount() - cf.getAmount();
            final Integer val1;
            cf.setAmount(cashFlow.getAmount());
            final Long book_id = cashFlow.getBook().getId();
            Book book = bookRepository.findById(book_id).get();
            if(cashFlow.getCashFlowType().equals(CashFlowType.CashIn)){
                val1 = book.getCashIn()+val;
                book.setCashIn(val1);
            } else{
                val1 = book.getCashOut()+val;
                book.setCashOut(val1);
            }
            book.setBalance(book.getCashIn()-book.getCashOut());
            bookRepository.save(book);
            cashFlow.setBook(book);
        }
        cf.setPaymentMethod(cashFlow.getPaymentMethod());
        cf.setCategory(cashFlow.getCategory());
        cf.setContact(cashFlow.getContact());
        cashFlowRepository.save(cf);
        return "CashFlow Successfully Updated!";
    }

    public String deleteCashFlow(Long id) {
        CashFlow cashFlow = cashFlowRepository.findById(id).get();
        Book book = cashFlow.getBook();
        final Integer val;
        if(cashFlow.getCashFlowType().equals(CashFlowType.CashIn)){
            val = book.getCashIn()-cashFlow.getAmount();
            book.setCashIn(val);
        } else{
            val = book.getCashOut()-cashFlow.getAmount();
            book.setCashOut(val);
        }
        book.setBalance(book.getCashIn()-book.getCashOut());
        bookRepository.save(book);
        cashFlowRepository.deleteById(id);
        return "CashFlow Successfully Deleted!";
    }
}
