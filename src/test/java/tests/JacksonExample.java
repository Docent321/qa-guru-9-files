package tests;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tests.domain.TeacherJackson;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JacksonExample {
    String pathFileJson = "src/test/resources/files/simple.json";

    @Test
    void parseJsonJackson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(pathFileJson);
            assertTrue(file.exists());
            TeacherJackson teacher = mapper.readValue(file, TeacherJackson.class);
            assertThat(teacher.name).isEqualTo("Alex");
            assertThat(teacher.surname).isEqualTo("Tarasov");
            assertThat(teacher.age).isEqualTo(36);
            assertThat(teacher.children).contains("Маша");  //не смог найти оператор включения с is
            assertThat(teacher.address.street).isEqualTo("Bor");
            assertThat(teacher.address.house).isEqualTo(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
