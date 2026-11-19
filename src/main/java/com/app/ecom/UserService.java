package com.app.ecom;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList=new ArrayList<>();
    long count=1l;
    public List<User> FetchAllUser() {

        return userList;
    }
    public void addUser(User user){
        userList.add(user);
        user.setId(count);
        count++;

    }
    public Optional<User> getByIdNo(long id){
        /*for(User us:userList){
            if(us.getId().equals(id)){
                return us;
            }
        }*/

        return userList.stream().filter(u->u.getId().equals(id)).findFirst();

    }
    public boolean UpadateUser(long id,User user){
       /* for(User us:userList){
            if(us.getId().equals(id)){
                us.setFn(user.getFn());
                us.setLn(user.getLn());
                return us;


            }

        }*/
        return userList.stream().filter(us->us.getId().equals(id)).
                findFirst().
                map(u->{u.setFn(user.getFn());
                u.setLn(user.getLn());
                return true;}).orElse(false);

    }
}
