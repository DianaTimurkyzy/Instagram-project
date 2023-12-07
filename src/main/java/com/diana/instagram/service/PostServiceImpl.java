package com.diana.instagram.service;

import com.diana.instagram.controller.res.PostResponse;
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
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final ProfileRepository profileRepository;

    @Autowired
    public PostServiceImpl(PostRepository repository, ProfileRepository profileRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void save(String description, long profileId, MultipartFile media) {
        Profile profile = profileRepository.findById(profileId).get();
        try {
            Post post = new Post(description, media.getBytes(), profile);
            repository.save(post);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PostResponse> getArchivedPostsById(long profileId) {
        List<Post> posts = repository.findAllByProfileIdAndIsArchived(profileId, true);
        return posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getDescription(),
                        Base64.getEncoder().encodeToString(post.getMedia()),
                        post.getPostedDate()
                ))
                .collect(Collectors.toList());

    }

}
