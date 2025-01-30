package com.jsp.bsm.utility;

import lombok.*;

@Getter
@Setter
public class PageStructure<T> extends ResponseStructure<T>{

    private int page;
    private int totalPages;
    private int size;
}
