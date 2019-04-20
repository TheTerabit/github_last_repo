package bs.pl.github_last_repo.service;

import bs.pl.github_last_repo.model.GithubResponse;
import bs.pl.github_last_repo.model.GithubRepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GithubServiceTest {

    @MockBean
    private GithubClient githubClient;

    @Autowired
    private GithubService githubService;

    @Test
    public void findLastUpdated_ProperDataGiven_ShouldReturnRepoName() {

        // given
        final GithubRepository hermes = GithubRepository.builder()
                .name("hermes")
                .pushedAt(new DateTime(2019, 4, 20, 12, 0).toDate())
                .build();

        final GithubRepository vaas = GithubRepository.builder()
                .name("vaas")
                .pushedAt(new DateTime(2018, 4, 20, 12, 0).toDate())
                .build();

        final GithubRepository turnilo = GithubRepository.builder()
                .name("turnilo")
                .pushedAt(new DateTime(2019, 4, 20, 13, 0).toDate())
                .build();

        final GithubRepository ralph = GithubRepository.builder()
                .name("ralph")
                .pushedAt(new DateTime(2019, 4, 19, 12, 0).toDate())
                .build();


        GithubResponse response = GithubResponse.builder()
                .totalCount(4L)
                .githubRepos(Arrays.asList(vaas, hermes, ralph, turnilo))
                .build();

        when(githubClient.getData())
                .thenReturn(response);

        assertEquals("turnilo",githubService.findLastUpdated());

        verify(githubClient).getData();
        verifyNoMoreInteractions(githubClient);
    }

    @Test
    public void findLastUpdated_EmptyListGiven_ShouldReturnNull() {

        // given
        GithubResponse response = GithubResponse.builder()
                .totalCount(0L)
                .githubRepos(new ArrayList<>())
                .build();

        when(githubClient.getData())
                .thenReturn(response);

        assertEquals(null, githubService.findLastUpdated());

        verify(githubClient).getData();
        verifyNoMoreInteractions(githubClient);
    }

}