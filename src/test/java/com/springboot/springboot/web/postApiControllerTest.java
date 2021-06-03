package com.springboot.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springboot.domain.post.post;
import com.springboot.springboot.domain.post.postRepository;
import com.springboot.springboot.web.dto.postSaveRequestDto;
import com.springboot.springboot.web.dto.postUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class postApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private postRepository postRepository;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }
    @Test
    @WithMockUser(roles = "USER")
    public void postupload() throws Exception{
        //given
        String title = "title";
        String content = "content";
        postSaveRequestDto requestDto = postSaveRequestDto.builder()
                            .title(title).content(content).author("author").build();
        String url = "http://localhost:"+port+"/api/v1/post";
        //when
        //ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,requestDto,Long.class);
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);


    }
    @Test
    @WithMockUser(roles = "USER")
    public void postupdate() throws Exception{
        //given
        post savePost = postRepository.save(post.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());
        Long updateId = savePost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        postUpdateRequestDto requestDto = postUpdateRequestDto.builder()
                    .title(expectedTitle).content(expectedContent).build();
        String url = "http://localhost:"+port+"/api/v1/post/"+updateId;
        HttpEntity<postUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        //when
        //ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);
        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
