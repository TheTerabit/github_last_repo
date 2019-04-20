package bs.pl.github_last_repo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private final String name;
    private final Date pushedAt;

    public Repository(@JsonProperty("name") String name,
                      @JsonProperty("pushed_at") Date pushedAt) {
        this.name = name;
        this.pushedAt = pushedAt;
    }
}
