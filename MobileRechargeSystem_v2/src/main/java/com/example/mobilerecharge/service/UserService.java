package com.example.mobilerecharge.service;
import com.example.mobilerecharge.model.User; import com.example.mobilerecharge.repository.UserRepository;
import org.springframework.stereotype.Service; import java.util.Optional;
@Service public class UserService {
    private final UserRepository repo; public UserService(UserRepository repo){this.repo=repo;}
    public User save(User u){ return repo.save(u); } public Optional<User> findByEmail(String e){return repo.findByEmail(e);} public Optional<User> findById(Long id){return repo.findById(id);}
}
