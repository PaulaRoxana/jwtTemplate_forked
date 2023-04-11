package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.entity.Book;
import ro.itschool.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-user")
public class MyUserController {

    private final UserService userService;

    @PostMapping("/buy-book")
    public void buyBook(@RequestBody Book book) {
        userService.buyBook(book);
    }
}
