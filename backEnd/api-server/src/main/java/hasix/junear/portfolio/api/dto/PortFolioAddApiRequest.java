package hasix.junear.portfolio.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortFolioAddApiRequest {

    private Long corporation_id;
    private Long stock_count;
    private Long average_price;

    @Builder
    public PortFolioAddApiRequest(Long corporation_id, Long stock_count, Long average_price) {
        this.corporation_id = corporation_id;
        this.stock_count = stock_count;
        this.average_price = average_price;
    }

}
