package explore.with.me.client.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hit {

    private Long id;
    private String app;
    private String uri;
    private String ip;
    private String timestamp;

}
