package com.geekster.InstaBackend.controller;

import com.geekster.InstaBackend.model.Post;
import com.geekster.InstaBackend.model.User;
import com.geekster.InstaBackend.service.PostService;
import com.geekster.InstaBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @PostMapping("userSignUp")
    public String userSignUp(@RequestBody User newUser) {
        return userService.userSignUp(newUser);


    }
       @PostMapping("userSignIn/email/{email}/password/{password}")
        public String userSignIn(@PathVariable String email,@PathVariable String password)
        {
            return userService.userSignIn(email,password);
        }


    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }


    @PutMapping("userSignIn/id/{id}/firstname/{firstname}")
    public String updatebyid(@PathVariable Integer id,@PathVariable String firstname)
    {
        return userService.updatebyid(id,firstname);
    }

    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return userService.createInstaPost(email,tokenValue,instaPost);
    }


    @GetMapping("Instapost/{postId}")

    public Post getpost(@PathVariable Integer postId){
       return postService.getpost(postId);



    }



}


