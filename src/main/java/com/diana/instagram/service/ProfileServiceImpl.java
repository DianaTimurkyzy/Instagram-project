package com.diana.instagram.service;

import com.diana.instagram.controller.request.ProfileDetailsRequest;
import com.diana.instagram.controller.res.PostResponse;
import com.diana.instagram.controller.res.ProfileResponse;
import com.diana.instagram.entity.Post;
import com.diana.instagram.entity.Profile;
import com.diana.instagram.repository.PostRepository;
import com.diana.instagram.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {


    private final ProfileRepository repository;
    private final PostRepository postRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @Override
    public ProfileResponse getByUsername(String username) {
        Profile profile = repository.findByUsername(username).get();
        List<Post> posts = postRepository.findAllByProfileIdAndIsArchived(profile.getId(), false);
        List<PostResponse> postResponses = posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getDescription(),
                        Base64.getEncoder().encodeToString(post.getMedia()),
                        post.getPostedDate()
                ))
                .collect(Collectors.toList());

        var response = new ProfileResponse();
        response.setProfileId(profile.getId());
        response.setUsername(profile.getUsername());
        response.setName(profile.getName());
        response.setDescription(profile.getDescription());
        response.setGender(profile.getGender());
        response.setPicture(Base64.getEncoder().encodeToString(profile.getPicture()));
        response.setPosts(postResponses);
        return response;
    }


    @Override
    public void saveProfilePicture(String username, MultipartFile picture) {
        Profile profile = repository.findByUsername(username).get();

        try {
            profile.setPicture(picture.getBytes());
            repository.save(profile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void createProfile(String username) {
        Profile profile = new Profile(username);
        repository.save(profile);
    }

    @Override
    public void editProfileDetails(ProfileDetailsRequest request) {
        Profile profile = repository.findById(request.getProfileId()).get();
        profile.setName(request.getName());
        profile.setDescription(request.getDescription());
        profile.setGender(request.getGender());
        repository.save(profile);
    }

    @Override
    public void deleteById(long id) {

        Profile profile = repository.findById(id).get();

        repository.delete(profile);
    }


}
