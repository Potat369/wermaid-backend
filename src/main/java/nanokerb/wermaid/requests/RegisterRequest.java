package nanokerb.wermaid.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotNull @Size(min = 2, max = 32) String username, @NotNull @Size(min = 2, max = 24) String password, @NotNull @Size(min = 4, max = 64) String displayName) {
}
