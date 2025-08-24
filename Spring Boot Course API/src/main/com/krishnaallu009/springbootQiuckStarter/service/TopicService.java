package com.krishnaallu009.springbootQiuckStarter.service;

import com.krishnaallu009.springbootQiuckStarter.entity.Topic;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TopicService {

    private List<Topic> topics = new ArrayList<>( Arrays.asList(
            new Topic("spring", "Spring Framework", "Spring Framework Description"),
            new Topic("java", "Core Java", "Core Java Description"),
            new Topic("javascript", "JavaScript", "JavaScript Description")
    ));

    public List<Topic> getAllTopics(){
        return topics;
    }

    public Topic getTopic(String id) {
        return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().orElse(new Topic());
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        topics.stream().filter(t -> t.getId().equals(id)).findFirst().ifPresent(t -> {
            t.setName(topic.getName());
            t.setDescription(topic.getDescription());
        });
    }

    public void deleteTopic(String id) {
        topics.removeIf(t -> t.getId().equals(id));
    }
}
