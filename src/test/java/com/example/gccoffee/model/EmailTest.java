package com.example.gccoffee.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    @DisplayName("이메일 테스트 성공")
    void emailSuccessTest() throws Exception {

        //given
        Email email = new Email("sjun@asdf.com");

        //when -> then
        assertThat(email.getAddress()).isEqualTo("sjun@asdf.com");

    }

    @Test
    @DisplayName("이메일 테스트 실패")
    void emailTest() throws Exception {

        //when -> then
        assertThrows(IllegalArgumentException.class, () -> new Email("accc"));
    }
}