package com.ssafy.fitty.model.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fitty.model.dao.board.CommentDao;
import com.ssafy.fitty.model.dto.board.Comment;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    
    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    
    @Override
    public void writeComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public void removeComment(int comment_num) {
        commentDao.deleteComment(comment_num);
    }

    @Override
    public void modifyComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public List<Comment> getCommentList(int board_num) {
        return commentDao.searchComment(board_num);
    }
}
