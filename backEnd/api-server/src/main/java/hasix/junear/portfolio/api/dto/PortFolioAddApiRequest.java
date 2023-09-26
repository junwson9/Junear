package hasix.junear.portfolio.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class PortFolioAddApiRequest {

    @NotNull(message = "기업 ID를 반드시 입력하여야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long corporationId;

    @NotNull(message = "주식 개수를 반드시 입력하여야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long stockCount;

    @NotNull(message = "평단가를 반드시 입력하여야 합니다.")
    @Positive(message = "양수의 숫자만 입력하세요.")
    private Long averagePrice;

}
