package org.example;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        // Validar datos antes de guardar
        // ...
        return userRepository.save(user);
    }

    // ... otros métodos
}

// ... otros servicios (ForumService, TopicService, CommentService)
