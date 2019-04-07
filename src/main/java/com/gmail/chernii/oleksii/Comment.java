package com.gmail.chernii.oleksii;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Space on 04.04.2019.
 */
@AllArgsConstructor
@Setter
@Getter
public class Comment {
    private String author;
    private String message;
    private long numberOfLikes;
    private DateTime date;
    private boolean edited;
}
