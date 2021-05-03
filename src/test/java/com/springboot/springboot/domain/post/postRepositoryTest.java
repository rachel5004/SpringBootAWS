package com.springboot.springboot.domain.post;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class postRepositoryTest {

    @Autowired
    postRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }
    @Test
    public void getpost(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(post.builder()
                            .title(title)
                            .content(content)
                            .author("koiil@gmail.com")
                            .build());
        //when
        List<post> postList = postRepository.findAll();

        //then
        post post = postList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
    @Test
    public void BaseTimeEntity_upload(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postRepository.save(post.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<post> postList = postRepository.findAll();
        //then
        post post = postList.get(0);
        System.out.println(">>>>>> createDate="+post.getCreatedDate()
                +", modifiedDate"+post.getModifiedDate());
        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
