package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;



import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/postcreator")
    public String postShow( Post post,
                           Model model) {
        model.addAttribute("title", "Post creator");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "postcreator";
    }
    @PostMapping("/postcreator")
    public String postAdd( @Valid Post post,
                          Model model, BindingResult errors) {

        if (errors.hasErrors()) {
            return "postcreator";
        }

        postRepository.save(post);
        return "redirect:/postcreator";
    }
    @GetMapping("/postcreator/{id}")
    public String postInfo(@PathVariable(value = "id") long postId, Model model) {
        model.addAttribute("title", "Post details");
        Optional<Post> post = postRepository.findById(postId);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "post-details";
    }
    @PostMapping("/postcreator/{id}/remove")
    public String postDelete(@PathVariable(value = "id") long postId, Model model){
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
        return "redirect:/postcreator";
    }

    @GetMapping("/postcreator/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long postId, Model model){
        Optional<Post> post = postRepository.findById(postId);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "post-edit";
    }

    @PostMapping("/postcreator/{id}/edit")
    public String postEditUpdate(@PathVariable(value = "id") long postId,@Valid Post post,  Model model) {
        //@RequestParam String text, @RequestParam String name,
//        Post editedPost = postRepository.findById(postId).orElseThrow();
//        editedPost.setName(name);
//        editedPost.setText(text);
//        postRepository.save(editedPost);

        post.setId(postId);
        this.postRepository.save(post);

        return "redirect:/postcreator";
    }



}
