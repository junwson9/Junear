package hasix.junear.portfolio.api.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PortFolioCreateApiRequest {

    private List<PortFolioAddApiRequest> portFolioAddApiRequestList;

    @Builder
    public PortFolioCreateApiRequest(List<PortFolioAddApiRequest> portFolioAddApiRequestList) {
        this.portFolioAddApiRequestList = portFolioAddApiRequestList;
    }

}
