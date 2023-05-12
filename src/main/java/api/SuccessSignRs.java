package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessSignRs {
    private boolean success;
    private String accessToken;
    private String refreshToken;

}
