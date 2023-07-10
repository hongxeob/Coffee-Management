package com.example.gccoffee.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.springframework.util.Assert.isTrue;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    private String address;

    private static final Pattern EMAIL_REGEX = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");

    public Email(String address) {
        Assert.notNull(address, "이메일이 비었습니다.");
        isTrue(address.length() >= 4 && address.length() <= 50, "이메일 주소 길이가 알맞지 않습니다");
        isTrue(checkAddress(address), "이메일 주소가 잘못되었습니다.");
        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return EMAIL_REGEX.matcher(address).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }
}
