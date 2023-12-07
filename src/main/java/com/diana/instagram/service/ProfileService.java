package com.diana.instagram.service;

import com.diana.instagram.controller.request.ProfileDetailsRequest;
import com.diana.instagram.controller.res.ProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    ProfileResponse getByUsername(String username);

    void saveProfilePicture(String username, MultipartFile picture);

    void createProfile(String username);

    void editProfileDetails(ProfileDetailsRequest request);

    void deleteById(long id);
}
