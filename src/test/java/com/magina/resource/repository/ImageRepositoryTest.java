package com.magina.resource.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.magina.resource.domain.CloudDisk;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {
    
    @Autowired
    private CloudDiskRepository repo;
    
    @Test
    public void test() {
        Page<CloudDisk> page = repo.findByNameLike("余罪%", new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id")));
        page.getContent().forEach(System.out::println);
    }
    
}