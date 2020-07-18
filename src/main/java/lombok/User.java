package lombok;

import java.time.LocalDateTime;
/*
pola prywatne
gettery
settery
konstruktory
toString

pobierane automatycznie z lombok
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class User {
    private int userId;
    private String email;
    private String password;
    private LocalDateTime registrationDateTime;
    private boolean status;
    private boolean removed;
}
