package com.geekster.InstaBackend.service;


import com.geekster.InstaBackend.model.Post;
import com.geekster.InstaBackend.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public void createInstaPost(@RequestBody  Post instaPost) {

        postRepo.save(instaPost);
    }

    public Post getPostById(Integer postId) {
      return  postRepo.findById(postId).orElseThrow();

    }


    public Post getpost(Integer id) {
       return  postRepo.findById(id).orElseThrow();
    }

}

