package com.diana.instagram.controller.request;

import com.diana.instagram.dto.PostDTO;
import lombok.Data;

@Data
public class PostRequest {

    private PostDTO dto;

    private long profileId;

}
