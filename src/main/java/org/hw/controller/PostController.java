package org.hw.controller;

import com.google.gson.Gson;
import org.hw.model.Post;
import org.hw.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) {
        response.setContentType(APPLICATION_JSON);
        service.getById(id);
    }


    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);

        try {
            final var savedPost = service.save(post);
            response.getWriter().print(gson.toJson(savedPost));
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print(gson.toJson(e.getMessage()));
        }
    }


    public void removeById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        try {
            service.removeById(id);
            response.getWriter().print(gson.toJson("Пост с id= " + id + " был успешно удален"));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().print(gson.toJson("Пост с id=  " + id + " не был удален"));
        }
    }
}