package com.daimler.postsales.automation.dto;

import lombok.Data;

@Data
public abstract class AbstractPageableRequest {
    private Integer page = 0;
    
    private Integer size = 50;
}
