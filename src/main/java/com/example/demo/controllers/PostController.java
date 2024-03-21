package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/postcreator")
    public String postShow(Model model) {
        model.addAttribute("title", "Post creator");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "postcreator";
    }
    @PostMapping("/postcreator")
    public String postAdd(@RequestParam String text, @RequestParam String name, Model model) {
        Post post = new Post(name,text);
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

}
