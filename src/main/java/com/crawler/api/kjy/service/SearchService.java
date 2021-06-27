package com.crawler.api.kjy.service;

import com.crawler.api.kjy.vo.search.SearchRequest;
import com.crawler.api.kjy.vo.search.SearchResponse;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;

import java.util.List;

public interface SearchService {

    public GDSSearchResponseDTO search(SearchRequest searchRequest, GDSSearchRequestDTO gdsSearchRequestDTO);
}
