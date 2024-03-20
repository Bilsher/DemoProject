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
public class MasterController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }


    @Autowired
    private PostRepository postRepository;
    @GetMapping("/Test")
    public String test(Model model) {
        model.addAttribute("title", "Test page");

        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);

        return "Test";
    }
    //доделать
    @PostMapping("/Test")
    public String testAdd(@RequestParam String text, @RequestParam String name, Model model) {
        Post post = new Post(name,text);
        postRepository.save(post);
        return "redirect:/Test";
    }

    @GetMapping("/Test/{id}")
    public String testInfo(@PathVariable(value = "id") long postId, Model model) {
        Optional<Post> post = postRepository.findById(postId);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "Test-details";
    }

    @PostMapping("/Test/{id}/remove")
    public String testDelete(@PathVariable(value = "id") long postId, Model model){
        Post post = postRepository.findById(postId).orElseThrow();
        postRepository.delete(post);
        return "redirect:/Test";
    }






    @GetMapping("/Test1")
    public String test1(Model model) {
        model.addAttribute("title", "Test1 page");
        return "Test1";
    }

    @GetMapping("/Test2")
    public String test2(Model model) {
        model.addAttribute("title", "Test2 page");
        return "Test2";
    }
    @GetMapping("/Test3")
    public String test3(Model model) {
        model.addAttribute("title", "Test3 page");
        return "Test3";
    }


}
