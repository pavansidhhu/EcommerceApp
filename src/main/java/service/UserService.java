package service;

import Repository.UserRepository;
import model.UserRole;
import model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private List<User> userList=new ArrayList<>();
    private final UserRepository userRepository;
    long count=1l;



    public List<User> FetchAllUser() {

        return userRepository.findAll();
    }
    public boolean addUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
          return false;
        }
        if(user.getUserRole()==null){
            user.setUserRole(UserRole.CUSTOMER);
        }

            userList.add(user);
            // user.setId(count);
            //count++;
            userRepository.save(user);
        return true;

    }
    public Optional<User> getByIdNo(long id){
        /*for(User us:userList){
            if(us.getId().equals(id)){
                return us;
            }
        }*/

       // return userList.stream().filter(u->u.getId().equals(id)).findFirst();
        return userRepository.findById(id);

    }
    public boolean UpadateUser(long id,User user){
       /* for(User us:userList){
            if(us.getId().equals(id)){
                us.setFn(user.getFn());
                us.setLn(user.getLn());
                return us;


            }

        }*/
     /*   return userList.stream().filter(us->us.getId().equals(id)).
                findFirst().
                map(u->{u.setFn(user.getFn());
                u.setLn(user.getLn());
                return true;}).orElse(false);*/
        return userRepository.findById(id).map(us -> {us.setFn(user.getFn());
        us.setLn(user.getFn());
        userRepository.save(us);
        return true;

        }).orElse(false);

    }

    public boolean deleteUserById(long id){
        boolean bh=userRepository.findById(id).isPresent();
        if(bh){
            userRepository.deleteById(id);
        }
        return bh;
    }
}
