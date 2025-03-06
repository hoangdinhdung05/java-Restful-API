package vn.hoangdung.controller;

import java.util.Date;
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
import jakarta.validation.constraints.Min;
import vn.hoangdung.dto.request.UserRequestDTO;

@RestController
@RequestMapping("/user")
public class UserController {
    
    //Viết các API CRUD 

    @GetMapping("/{userId}")
    public UserRequestDTO getUser(@PathVariable int userId) {

        System.out.println("User ID: " + userId);
        return new UserRequestDTO("Hoang", "Dung", "0123456789", "NzjBv@example.com", null);
    }

    @GetMapping("/list")
    public List<UserRequestDTO> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                           @Min(10) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        return List.of(new UserRequestDTO("Tay", "Java", "admin@tayjava.vn", "0123456789", new Date()),
                new UserRequestDTO("Leo", "Messi", "leomessi@email.com", "0123456456", new Date()));
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
    public String deleteUser(@Min(1) @PathVariable int userId) {

        System.out.println("User ID: " + userId);

        return "Test API delete user";
    }


}
