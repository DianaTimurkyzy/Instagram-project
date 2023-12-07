package com.diana.instagram.controller;

import com.diana.instagram.controller.res.PostResponse;
import com.diana.instagram.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;


    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public void save(@RequestParam String description, @RequestParam long profileId, @RequestParam MultipartFile media) {
        service.save(description, profileId, media);
    }

    @GetMapping("/getAllArchived")
    public List<PostResponse> getAllArchivedPostsByProfileId(@RequestParam long profileId) {
        return service.getArchivedPostsById(profileId);
    }

}
