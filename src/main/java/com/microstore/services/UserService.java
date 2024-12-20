    package com.microstore.services;

    import com.microstore.entity.Users;
    import com.microstore.DTO.UserDTO;
    import com.microstore.repository.UserRepository;
    import jakarta.transaction.Transactional;
    import org.springframework.stereotype.Service;

    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class UserService {

        private final UserRepository userRepository;

        //Dependency Injection
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        //Metodo per effettuare il login
        public boolean login(String user, String password) {

            List<Users> list = userRepository.findByEmail(user);

            //Controlla che il campo user non sia vuoto
            if(user == null) {
                return false;
            }

            if (!list.isEmpty()){
                Users users = list.get(0);
                if (users.getPassword().equals(password)){
                    return true;
                }
            } else {
                return false;
            }
            return false;
        }

        @Transactional
        public boolean createUser(UserDTO request){

            List<Users> existingUsers = userRepository.findByEmail(request.getEmail());
            if (!existingUsers.isEmpty()) {
                return false; // Utente già esistente
            }

            Users entityUser = new Users();

            entityUser.setCreated_at(new Date());
            entityUser.setName(request.getName());
            entityUser.setEmail(request.getEmail());
            entityUser.setPassword(request.getPassword());
            entityUser.setPhone(request.getPhone());
            userRepository.save(entityUser);

            return true;
        }


        @Transactional
        public boolean updateUser(Long id, UserDTO userDTO){
            Optional<Users> usersOptional = userRepository.findById(id);
            if (usersOptional.isPresent()) {
                Users entityUser = usersOptional.get();

                entityUser.setName(userDTO.getName());
                entityUser.setEmail(userDTO.getEmail());
                entityUser.setPhone(userDTO.getPhone());
                entityUser.setPassword(userDTO.getPassword());
                entityUser.setRole(userDTO.getRole());
                entityUser.setCreated_at(new Date());
                userRepository.save(entityUser);
                return true;
            } else {
                return false;
            }
        }

        @Transactional
        public boolean deleteUser(Long id){
            Optional<Users> usersOptional = userRepository.findById(id);
            if (usersOptional.isPresent()) {
                Users entityUser = usersOptional.get();
                userRepository.delete(entityUser);
                return true;
            } else {
                return false;
            }
        }
    }
