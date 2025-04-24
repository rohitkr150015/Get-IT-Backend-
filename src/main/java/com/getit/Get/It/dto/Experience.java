package com.getit.Get.It.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {


    private String title;
    private String company;
    private String description;
    private String location;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Boolean working;
}
