package com.api.movies;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.movies.controllers.CommentController;
import com.api.movies.entities.Comment;
import com.api.movies.services.CommentService;

@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    void getAllComments_shouldReturnListOfComments() {
        // Arrange
        List<Comment> mockComments = Arrays.asList(new Comment(), new Comment());
        when(commentService.getAllComments()).thenReturn(mockComments);

        // Act
        ResponseEntity<List<Comment>> response = commentController.getAllComments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockComments, response.getBody());
    }

    @Test
    void getCommentById_shouldReturnComment() {
        // Arrange
        long commentId = 1L;
        Comment mockComment = new Comment();
        when(commentService.findByID(commentId)).thenReturn(mockComment);

        // Act
        ResponseEntity<?> response = commentController.getCommentById(commentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockComment, response.getBody());
    }

    // Similar tests for addComment and deleteComment...

    @Test
    void addComment_shouldReturnCreated() {
        // Arrange
        Comment mockComment = new Comment();
        // Assuming your addComment method returns void, adjust accordingly
        doNothing().when(commentService).addComments(mockComment);

        // Act
        ResponseEntity<?> response = commentController.addComment(mockComment);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deleteComment_shouldReturnNoContent() {
        // Arrange
        long commentId = 1L;
        // Assuming your deleteComment method returns void, adjust accordingly
        doNothing().when(commentService).deleteComment(commentId);

        // Act
        ResponseEntity<?> response = commentController.deleteComment(commentId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
