package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostApiController {

    private final PostRepository repo;

    public PostApiController(PostRepository repo) {
        this.repo = repo;
    }

    // 목록(검색 + 페이징 + 정렬)
    @GetMapping("/api/posts")
    public Page<Post> posts(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        PageRequest pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
                        .and(Sort.by(Sort.Direction.DESC, "id"))
        );

        if (query == null || query.trim().isEmpty()) {
            return repo.findAll(pageable);
        }
        return repo.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query, pageable);
    }

    // 상세
    @GetMapping("/api/posts/{id}")
    public ResponseEntity<Post> postDetail(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 글 작성
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostCreateRequest req) {
        Post p = new Post();
        p.setTitle(req.title);
        p.setContent(req.content);
        return repo.save(p);
    }

    // 삭제
    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    static class PostCreateRequest {
        public String title;
        public String content;
    }
}