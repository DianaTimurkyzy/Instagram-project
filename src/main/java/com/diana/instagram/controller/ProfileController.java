package com.diana.instagram.controller;

import com.diana.instagram.controller.request.ProfileDetailsRequest;
import com.diana.instagram.controller.res.ProfileResponse;
import com.diana.instagram.service.ProfileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/getByUsername")
    public ProfileResponse getByUsername(@RequestParam String username) {
        return service.getByUsername(username);
    }

    @PostMapping("/saveProfilePicture")
    public void saveProfilePicture(@RequestParam String username, @RequestParam MultipartFile picture) {
        service.saveProfilePicture(username, picture);
    }

    @PostMapping("/createProfile")
    public void createProfile(@RequestParam String username) {
        service.createProfile(username);
    }

    @PutMapping("/editProfileDetails")
    public void editProfileDetails(@RequestBody ProfileDetailsRequest request) {
        service.editProfileDetails(request);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam long id) {
        service.deleteById(id);
    }


}
