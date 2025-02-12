package az.atl.auto_mart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailRequest {
    private String to;
    private String subject;
    private String text;
}
