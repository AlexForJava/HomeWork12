package com.gmail.chernii.oleksii;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.CommentSnippet;
import com.google.api.services.youtube.model.CommentThread;

import java.util.List;

/**
 * Created by Space on 07.04.2019.
 */
public class YouTubeComments {
    private List<Comment> comments;

    private YouTubeCommentThread youTubeCommentThread;

    public YouTubeComments(String videoID, long maxSize) {
        youTubeCommentThread = new YouTubeCommentThread(videoID, maxSize);
    }

    public List<Comment> getComments() {
        if (youTubeCommentThread.getCommentThreadList() == null) {
            return null;
        }
        List<CommentThread> commentThreadList = youTubeCommentThread.getCommentThreadList();
        for (CommentThread commentThread : commentThreadList) {
            CommentSnippet snippet = commentThread.getSnippet().getTopLevelComment().getSnippet();
            Comment comment = getComment(snippet);
            comments.add(comment);
        }
        return comments;
    }

    private Comment getComment(CommentSnippet snippet) {
        String author = snippet.getAuthorDisplayName();
        String message = snippet.getTextDisplay();
        long likes = snippet.getLikeCount();
        DateTime dateTime = snippet.getPublishedAt();
        boolean edited = dateTime.equals(snippet.getUpdatedAt());
        return new Comment(author, message, likes, dateTime, edited);
    }
}
