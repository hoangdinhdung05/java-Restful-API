package vn.hoangdung.controller;

import java.util.Date;
import java.util.List;
import org.springframework.validation.annotation.Validated;
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
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import vn.hoangdung.dto.request.UserRequestDTO;
import vn.hoangdung.dto.response.ResponseData;
import vn.hoangdung.dto.response.ResponseError;



@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    //Viết các API CRUD 

    @GetMapping("/{userId}")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseData<UserRequestDTO> getUser(@PathVariable @Min(1) int userId) {

        System.out.println("User ID: " + userId);

        return new ResponseData<>(HttpStatus.OK.value(), "user", new UserRequestDTO("Tay", "Java", "admin@tayjava.vn", "0123456789", new Date()));
    }

    @GetMapping("/list")
    // @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<UserRequestDTO>> getAllUser(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                           @Min(10) @RequestParam(defaultValue = "20", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(), "list user", List.of(new UserRequestDTO("Tay", "Java", "admin@tayjava.vn", "0123456789", new Date()),
        new UserRequestDTO("Leo", "Messi", "leomessi@email.com", "0123456456", new Date())));
    }

    // @Operation(summary = "summary", description = "description", responses = {
    //     @ApiResponse(responseCode = "201", description = "User created",
    //         content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
    //         examples = @ExampleObject(name = "ex name", summary = "ex summary", 
    //                                     value = """
    //                                             {
    //                                                 "status": 201,
    //                                                 "message": "Test API add user",
    //                                                 "data": 1
    //                                             }
    //                                             """)))
    
    // })
    //Cách 1: Dùng docstring
    //Cách 2: Dùng annotation
    //Cách 3: Dùng @Operation
    @PostMapping("/")
    public ResponseError addUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        System.out.println("User: " + userRequestDTO);

        //TH lỗi
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Call not create User");

        // return new ResponseData<>(HttpStatus.CREATED.value(), "Test API add user", 1);
    }

    // @Operation(summary = "summary", description = "description", responses = {
    //     @ApiResponse(responseCode = "202", description = "User updated",
    //         content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, 
    //         examples = @ExampleObject(name = "ex name", summary = "ex summary", 
    //                                     value = """
    //                                             {
    //                                                 "status": 202,
    //                                                 "message": "Test API put user",
    //                                                 "data": null
    //                                             }
    //                                             """)))
    
    // })
    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable int userId, @RequestBody UserRequestDTO userRequestDTO) {

        System.out.println("User ID: " + userId);

        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Test API update user");
    }


    @PatchMapping("/{userId}")
    // @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseData<?> changeStatus(@PathVariable int userId, @RequestParam(required = false) boolean status) {
        
        //localhost:8080/user/1?status=true

        System.out.println("User ID: " + userId);
        System.out.println("Status: " + status);

        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Test API patch user");
    }

    @DeleteMapping("/{userId}")
    // @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseData<?> deleteUser(@Min(1) @PathVariable int userId) {

        System.out.println("User ID: " + userId);

        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Test API delete user");
    }


}
