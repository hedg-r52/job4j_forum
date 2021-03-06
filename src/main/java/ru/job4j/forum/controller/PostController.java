package ru.job4j.forum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", new Post());
        modelAndView.setViewName("post");
        return modelAndView;
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute("post") Post post) {
        this.postService.add(post);
        return "redirect:/";
    }

    @GetMapping("/post/update")
    public ModelAndView showUpdateForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", postService.get(id));
        modelAndView.setViewName("edit");
        return modelAndView;
    }

    @PostMapping("/post/update")
    public String updatePost(@ModelAttribute("post") Post post) {
        this.postService.update(post);
        return "redirect:/";
    }

    @DeleteMapping("/post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("id") long id) {
        this.postService.delete(id);
    }
}
