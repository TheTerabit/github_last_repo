package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.entity.GithubResponse;
import bs.pl.github_last_repo.entity.Repository;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GithubServiceTest {

    @MockBean
    private GithubClient githubClient;

    @Autowired
    private GithubService githubService;

    @Test
    public void findLastUpdated() {

        // given
        final Repository hermes = Repository.builder()
                .name("hermes")
                .pushedAt(new DateTime(2019, 4, 20, 12, 0).toDate())
                .build();

        final Repository vaas = Repository.builder()
                .name("vaas")
                .pushedAt(new DateTime(2018, 4, 20, 12, 0).toDate())
                .build();

        final Repository turnilo = Repository.builder()
                .name("turnilo")
                .pushedAt(new DateTime(2019, 4, 20, 13, 0).toDate())
                .build();

        final Repository ralph = Repository.builder()
                .name("ralph")
                .pushedAt(new DateTime(2019, 4, 19, 12, 0).toDate())
                .build();

        final List<Repository> list = new ArrayList<Repository>();

        GithubResponse response = GithubResponse.builder()
                .totalCount(4L)
                .gitHubRepos(Arrays.asList(vaas, hermes, ralph, turnilo))
                .build();


        when(githubClient.getRepo())
                .thenReturn(response);


       assertEquals("turnilo",githubService.findLastUpdated());

    }
}