package com.springboot.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    //스프링 시큐리티에서 권한코드는 ROLE_로 시작
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;
}
