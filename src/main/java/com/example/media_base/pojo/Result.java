package com.example.media_base.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;  // 0 for success, 1 for failure
    private String message;
    private T data;

//    public Result(Integer code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }

    public static <E> Result<E> success(E data) {
        return new Result<E>(0, "success", data);
    }

    public static Result success() {
        return new Result(0, "success", null);
    }

    public static Result failure(String message) {
        return new Result(1, message, null);
    }
}
