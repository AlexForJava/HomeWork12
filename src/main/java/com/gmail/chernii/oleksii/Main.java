package com.gmail.chernii.oleksii;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Space on 04.04.2019.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String videoID = getVideoID();
        YouTubeComments youTubeComments = new YouTubeComments(videoID, 10L);
        List<Comment> comments = youTubeComments.getComments();
        printList(comments);
    }

    private static String getVideoID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter video ID");
        return scanner.nextLine();
    }

    private static void printList(List<Comment> list) {
        list.forEach(e -> {
            System.out.println("------------------------");
            System.out.println(e.getAuthor());
            System.out.println(e.getMessage());
            System.out.println(e.getNumberOfLikes());
            System.out.println(e.getDate());
            System.out.println(e.isEdited());
        });
    }
}
