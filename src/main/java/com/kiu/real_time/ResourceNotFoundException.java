// 사용자 정의 예외 클래스

package com.kiu.real_time;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// ResourceNotFoundException.java
// 리소스를 찾지 못했을 때 발생시키는 사용자 정의 예외 처리
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

