package com.gmail.chernii.oleksii;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CommentThread;
import com.google.api.services.youtube.model.CommentThreadListResponse;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;

/**
 * Created by Space on 07.04.2019.
 */
public class YouTubeCommentThread {
    private static YouTube youTube;

    private String videoID;
    private long maxSize;

    public YouTubeCommentThread(String videoID, long maxSize) {
        this.videoID = videoID;
        this.maxSize = maxSize;
    }

    private void initialize() {
        try {
            List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube.force-ssl");
            Credential credential = Auth.authorize(scopes, "commentthread");
            youTube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, credential)
                    .setApplicationName("comment-listener").build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CommentThread> getCommentThreadList() {
        initialize();
        List<CommentThread> list = null;
        try {
            CommentThreadListResponse commentThreadListResponse = youTube
                    .commentThreads()
                    .list("snippet")
                    .setVideoId(videoID)
                    .setMaxResults(maxSize)
                    .setTextFormat("plainText")
                    .execute();
            list = commentThreadListResponse.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
