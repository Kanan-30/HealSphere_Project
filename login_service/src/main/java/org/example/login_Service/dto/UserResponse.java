package org.example.login_Service.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse (
        @JsonProperty("first_name")
        String firstName,
        @JsonProperty("last_name")
        String lastName,
        @JsonProperty("email")
        String email

) {
}
