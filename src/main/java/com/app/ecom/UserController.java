package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    //long count=1;
   @RequestMapping(value="/user",method= RequestMethod.GET)

    public ResponseEntity<List<User>> getAllUsers(){

      //  return new ResponseEntity<>(userService.FetchAllUser(), HttpStatus.OK);
       return  ResponseEntity.ok(userService.FetchAllUser());
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
       /*User user=userService.getByIdNo(id);
       if(user==null){
          return ResponseEntity.notFound().build();
       }*/

       return userService.getByIdNo(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }
    @PostMapping("/api/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user){


       userService.addUser(user);
       return ResponseEntity.ok("User Added Successfully");

    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id,@RequestBody User us){
       boolean bh=userService.UpadateUser(id,us);
       if(!bh){
           return ResponseEntity.status(404).body("Not Updated Sucessfully");
       }
       return ResponseEntity.ok("Update sucessfully");
    }
}
