package com.backend.assignment.indpro.backend.assignment.controller;

import com.backend.assignment.indpro.backend.assignment.dto.CommentDTO;
import com.backend.assignment.indpro.backend.assignment.dto.GetCommentDTO;
import com.backend.assignment.indpro.backend.assignment.dto.PostDTO;
import com.backend.assignment.indpro.backend.assignment.entity.Comment;
import com.backend.assignment.indpro.backend.assignment.entity.Post;
import com.backend.assignment.indpro.backend.assignment.entity.User;
import com.backend.assignment.indpro.backend.assignment.repository.CommentRepository;
import com.backend.assignment.indpro.backend.assignment.repository.PostRepository;
import com.backend.assignment.indpro.backend.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    // POST /posts - Create a new blog post
    @PostMapping
    public Post createPost(@RequestBody PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        User user=userRepository.findById(postDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));


        post.setAuthor(user); // Set t
        return postRepository.save(post);
    }

    // POST /posts/{id}/comments - Add a comment to a post
    @PostMapping("/{id}/comments")
    public Comment addCommentToPost(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        User user=userRepository.findById(commentDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));
        Comment comment=new Comment();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent(commentDTO.getContent());

        return commentRepository.save(comment);
    }

    // GET /posts/{id}/comments - Fetch all comments for a post
    @GetMapping("/{id}/comments")
    public List<GetCommentDTO> getCommentsByPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        List<Comment> comments= commentRepository.findAllCommentByPostId(id);

       return comments.stream().map(comment -> {
           GetCommentDTO dto = new GetCommentDTO();
           dto.setId(comment.getId());
           dto.setContent(comment.getContent());
           dto.setAuthorId(comment.getAuthor().getId());
           dto.setCreatedAt(comment.getCreatedAt());
           return dto;
       }).collect(Collectors.toList());

    }
}
