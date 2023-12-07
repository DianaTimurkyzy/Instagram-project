package com.diana.instagram.service;

import com.diana.instagram.controller.res.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {

    void save(String description, long profileId, MultipartFile media);

    List<PostResponse> getArchivedPostsById(long profileId);
}
