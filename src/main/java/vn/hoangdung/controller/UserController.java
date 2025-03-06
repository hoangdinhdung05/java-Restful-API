package vn.hoangdung.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoangdung.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/user")
public class UserController {
    
    //Viết các API CRUD 

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable int userId) {

        System.out.println("User ID: " + userId);
        return new UserRequestDTO("Hoang", "Dung", "0123456789", "NzjBv@example.com");
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getUserList(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNO, 
            @RequestParam(defaultValue = "10") int pageSize) {

        return Arrays.asList(
            new UserRequestDTO("Hoang", "Dung", "0123456789", "NzjBv@example.com"),
            new UserRequestDTO("Hoang", "Dung", "0123456789", "NzjBv@example.com"),
            new UserRequestDTO("Hoang", "Dung", "0123456789", "NzjBv@example.com")
        );
    }

    @PostMapping("/")
    // @RequestMapping(path = "/", method = POST)
    public String addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {

        return "Test API add user";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody UserRequestDTO userRequestDTO) {

        System.out.println("User ID: " + userId);

        return "Test API update user";
    }

    @PatchMapping("/{userId}")
    public String changeStatus(@PathVariable int userId, @RequestParam(required = false) boolean status) {
        
        //localhost:8080/user/1?status=true

        System.out.println("User ID: " + userId);
        System.out.println("Status: " + status);

        return "Test API change status";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId) {

        System.out.println("User ID: " + userId);

        return "Test API delete user";
    }


}
