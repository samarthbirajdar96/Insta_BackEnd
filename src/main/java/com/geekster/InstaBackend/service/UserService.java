package com.geekster.InstaBackend.service;



import com.geekster.InstaBackend.model.AuthenticationToken;
import com.geekster.InstaBackend.model.Post;
import com.geekster.InstaBackend.model.User;
import com.geekster.InstaBackend.repository.IUserRepo;
import com.geekster.InstaBackend.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;




    public String userSignUp(User newUser) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);


            // patient table - save patient
            userRepo.save(newUser);
            return "Insta user registered";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }


    public String userSignIn(String email, String password) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up


        User existingUser = userRepo.findFirstByUserEmail(email);

        if(existingUser == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                AuthenticationToken token  = new AuthenticationToken(existingUser);
                   authenticationService.createToken(token);
                  return token.getTokenValue();
                }


            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }
    }

    public String userSignOut(String email,String tokenValue) {
        if(authenticationService.authenticate(email,tokenValue)) {
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        }
        else {
            return "Un Authenticated access!!!";
        }
    }


    public String updatebyid(Integer id, String firstname) {
        User user=userRepo.findById(id).orElseThrow();

        if(user!=null){
            user.setUserFirstName(firstname);
            userRepo.save(user);
            return "user updated ";
        }else{
            return null;
        }

    }


        public String createInstaPost(String email, String tokenValue, Post instaPost) {

            if(authenticationService.authenticate(email,tokenValue)) {

                User existingUser = userRepo.findFirstByUserEmail(email);
                instaPost.setPostOwner(existingUser);

                postService.createInstaPost(instaPost);
                return  " posted!!";

            }
            else {
                return "Un Authenticated access!!!";
            }
        }
    }

