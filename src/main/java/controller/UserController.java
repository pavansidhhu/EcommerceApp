package controller;

import model.User;
import service.UserService;
import lombok.RequiredArgsConstructor;
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
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user){


      boolean bh= userService.addUser(user);
       return bh?ResponseEntity.ok("User Added Successfully"):ResponseEntity.status(204).body("Data alreaddy exists");

    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id,@RequestBody User us){
       boolean bh=userService.UpadateUser(id,us);
       if(!bh){
           return ResponseEntity.status(404).body("Not Updated Sucessfully");
       }
       return ResponseEntity.ok("Update sucessfully");
    }
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable long id){


       boolean bh=userService.deleteUserById(id);
       return (bh) ? ResponseEntity.ok("UserDeleted" ): ResponseEntity.status(404).body("NotFound");
    }
}
