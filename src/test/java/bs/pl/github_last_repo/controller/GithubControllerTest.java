package bs.pl.github_last_repo.controller;

import bs.pl.github_last_repo.service.GithubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GithubControllerTest {

    @MockBean
    private GithubService githubService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLastUpdatedName_ProperDataGiven_ShouldReturnRepoName() throws Exception {
        // given
        final String vaas = "vaas";

        when(githubService.findLastUpdated())
                .thenReturn(vaas);

        mockMvc
                // when
                .perform(get("/api/repo"))
                // then
                .andExpect(status().isOk())
                .andExpect(content().string("vaas"));

        verify(githubService).findLastUpdated();
        verifyNoMoreInteractions(githubService);

    }

    @Test
    public void getLastUpdatedName_NullGiven_ShouldReturnNull() throws Exception {
        // given
        final String emptyList = null;

        when(githubService.findLastUpdated())
                .thenReturn(emptyList);

        mockMvc
                // when
                .perform(get("/api/repo"))
                // then
                .andExpect(content().string(""));

        verify(githubService).findLastUpdated();
        verifyNoMoreInteractions(githubService);

    }

}