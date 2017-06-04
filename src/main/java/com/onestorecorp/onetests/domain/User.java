package com.onestorecorp.onetests.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
public class User {

    @Id
    private String id;

    @CreatedDate
    private Date createdDate;

    @Indexed(unique = true)
    private String email;
    private String password;

    private String name;
    private String employeeNo;

    private List<String> roles;

}
