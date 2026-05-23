package com.example.awsdeploy.exception;

public class UnauthorizedPostAccessException extends RuntimeException {

    public UnauthorizedPostAccessException() {
        super("해당 글을 수정하거나 삭제할 권한이 없습니다.");
    }
}
