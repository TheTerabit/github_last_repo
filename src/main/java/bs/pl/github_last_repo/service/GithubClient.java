package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.model.GithubResponse;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.WebTarget;

import static javax.ws.rs.client.ClientBuilder.newClient;

@Service
public class GithubClient {

    private final WebTarget target = newClient().target("https://api.github.com/search/repositories?q=user:allegro&per_page=1000");

    public GithubResponse getData(){
        return target.request()
                .get(GithubResponse.class);
    }
}
