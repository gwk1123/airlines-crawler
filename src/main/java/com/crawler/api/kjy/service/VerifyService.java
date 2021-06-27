package com.crawler.api.kjy.service;

import com.crawler.api.kjy.vo.verify.VerifyRequest;
import com.crawler.api.request.GDSVerifyRequestDTO;
import com.crawler.api.response.GDSVerifyResponseDTO;

public interface VerifyService {

    public GDSVerifyResponseDTO verify(VerifyRequest verifyRequest, GDSVerifyRequestDTO gdsVerifyRequestDTO);
}
