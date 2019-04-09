package com.example.realworldjdbc.domain;

import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

import lombok.Value;

@Value
public class Tag implements NamingStrategy {

    String name;

    @Override
    public String getReverseColumnName(RelationalPersistentProperty property) {
        return "article_id";
    }
}
