package com.api.movies.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.movies.entities.Comment;
import com.api.movies.services.CommentService;

@RestController
@RequestMapping("/api/v1/comments") // Changed the endpoint to be more RESTful
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    /**
     * Obtiene todos los comentarios.
     * 
     * @return Lista de comentarios.
     */
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            List<Comment> comments = commentService.getAllComments();
            logger.info("Returned {} comments.", comments.size());
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting all comments.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * Obtiene un comentario por su ID.
     * 
     * @param id ID del comentario a obtener.
     * @return El comentario correspondiente al ID proporcionado, o un mensaje de
     *         error si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        try {
            Comment comment = commentService.findByID(id);
            logger.info("Returned comment with ID: {}", id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting comment with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Agrega un nuevo comentario.
     * 
     * @param comment El comentario a crear.
     * @return El comentario creado.
     */
    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> addComment(@RequestBody Comment comment) {
        try {
            commentService.addComments(comment);
            logger.info("Added new comment with ID: {}", comment.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while adding a new comment.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un comentario por su ID.
     * 
     * @param id ID del comentario a eliminar.
     * @return true si el comentario se elimina correctamente, false si no se
     *         encuentra el comentario con el ID proporcionado.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            logger.info("Deleted comment with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error while deleting comment with ID: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
