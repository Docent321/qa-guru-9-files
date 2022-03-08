package tests.domain;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;

import java.util.List;

public class TeacherJackson {
    public String name;
    public String surname;
    public Integer age;
    @JsonProperty("favorite_music")
    public List<String> favoriteMusic;
    public Address address;
    public List<String> children;

}
