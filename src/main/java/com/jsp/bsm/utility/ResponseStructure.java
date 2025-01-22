package com.jsp.bsm.utility;

import lombok.Data;

@Data
public class ResponseStructure<T> {

    private int status;
    private String message;
    private T data;

    public static <T> ResponseStructure<T> create(int status, String message, T data) {
        ResponseStructure<T> structure = new ResponseStructure<T>();
        structure.setStatus(status);
        structure.setMessage(message);
        structure.setData(data);

        return structure;

    }

}
