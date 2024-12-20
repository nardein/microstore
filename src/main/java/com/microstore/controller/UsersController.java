package com.microstore.controller;

import com.microstore.model.usermod.LoginRequest;
import com.microstore.model.usermod.LoginResponse;
import com.microstore.DTO.UserDTO;
import com.microstore.model.usermod.UserResponse;
import com.microstore.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //READ
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        boolean check = userService.login(request.getUser(), request.getPassword());

        if(check){
            LoginResponse response = new LoginResponse();
            response.setMessage("Login successful");
            response.setToken("Valid Token");
            return ResponseEntity.ok(response);
        }else{
            LoginResponse response = new LoginResponse();
            response.setMessage("Login failed");
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(response);
        }
    }

    //CREATE - Crea un nuovo utente
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO user){

        boolean isCreated = userService.createUser(user);

        if(isCreated){
            UserResponse response = new UserResponse();
            response.setMessage("User successfully created");
            return ResponseEntity.status(HttpStatus.CREATED).body("User created succesfully");
        } else {
            UserResponse response = new UserResponse();
            response.setMessage("User creation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        }
    }

    //UPDATE - Aggiorna un utente
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestParam Long id,@Valid @RequestBody UserDTO user){
        boolean isUpdated = userService.updateUser(id, user);
        if(isUpdated){
            return ResponseEntity.ok("User successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User update failed");
        }
    }

    //DELETE - Elimina un utente
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam Long id){
        boolean isDeleted = userService.deleteUser(id);
        if(isDeleted){
            return ResponseEntity.ok("User successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User delete failed");
        }
    }


}
