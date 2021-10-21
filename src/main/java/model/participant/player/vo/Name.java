package model.participant.player.vo;

import java.util.Objects;

public class Name {
    private final String value;

    public static Name create(final String name) {
        return new Name(name);
    }

    private Name(final String name) {
        if(name.isBlank()) {
            throw new IllegalArgumentException("공백으로 이름을 생성할 수 없습니다.");
        }
        this.value = name;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}