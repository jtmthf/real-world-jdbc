package com.example.realworldjdbc.article;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleFavoriteRepository articleFavoriteRepository;

}
