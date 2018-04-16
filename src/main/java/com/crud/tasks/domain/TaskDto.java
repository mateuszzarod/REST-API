package com.crud.tasks.domain;


import lombok.Getter;

//nie działa mi ani import ani adnotacja
@Getter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;

}
