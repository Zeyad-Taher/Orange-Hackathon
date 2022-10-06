package com.example.orangehackathon.utlities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {

    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}