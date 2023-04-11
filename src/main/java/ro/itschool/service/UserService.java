package ro.itschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Book;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.MyUser;
import ro.itschool.repository.BookRepository;
import ro.itschool.repository.RoleRepository;
import ro.itschool.repository.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BookRepository bookRepository;

    public void saveUser(MyUser user) {
        user.getRoles()
                .forEach(role -> {
                            MyRole roleByName = roleRepository.findByName(role.getName());
                            role.setId(roleByName.getId());
                        }
                );
        userRepository.save(user);
    }

    public List<MyUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<MyUser> findById(Integer id) {
        return userRepository.findById(id);
    }

    public void buyBook(Book book) {
        MyUser principal = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<MyUser> optionalLoggedInUser = userRepository.findByEmail(principal.getEmail());

        optionalLoggedInUser.ifPresent(user -> {
            Optional<Book> optionalBook = bookRepository.findByTitle(book.getTitle());
            optionalBook.ifPresentOrElse(foundBook -> book.setId(foundBook.getId()),
                    () -> book.setId(bookRepository.save(book).getId()));
            user.getBooks().add(book);
            userRepository.save(user);
        });
    }
}
