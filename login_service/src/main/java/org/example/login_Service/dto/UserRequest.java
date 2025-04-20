package org.example.login_Service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record UserRequest (
        @NotNull(message = "User should be present")
        @NotEmpty(message = "User should be present")
        @NotBlank(message = "User should be present")
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message="User email is required")
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password
) {
}
