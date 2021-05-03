package com.springboot.springboot.web;

import com.springboot.springboot.domain.post.post;
import com.springboot.springboot.domain.post.postRepository;
import com.springboot.springboot.web.dto.postSaveRequestDto;
import com.springboot.springboot.web.dto.postUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class postApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private postRepository postRepository;
    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }
    @Test
    public void postupload() throws Exception{
        //given
        String title = "title";
        String content = "content";
        postSaveRequestDto requestDto = postSaveRequestDto.builder()
                            .title(title).content(content).author("author").build();
        String url = "http://localhost:"+port+"/api/v1/post";
        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,requestDto,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }
    @Test
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
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
