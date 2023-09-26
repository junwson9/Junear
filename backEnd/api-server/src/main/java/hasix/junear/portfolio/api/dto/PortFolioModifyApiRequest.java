package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PortFolioModifyApiRequest {

    @NotNull(message = "기업 ID를 반드시 입력하여야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long corporationId;

    @NotEmpty(message = "수량을 반드시 입력해야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long stockCount;

    @NotEmpty(message = "평단가를 반드시 입력해야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long averagePrice;

}
