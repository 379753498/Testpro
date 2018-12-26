package org.com.testpro.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class Result<T> {

    private String Message;
    private int Code;
    public T data;
}
