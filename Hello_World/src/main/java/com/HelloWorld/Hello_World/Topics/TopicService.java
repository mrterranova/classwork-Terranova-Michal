package com.HelloWorld.Hello_World.Topics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;


//    private List<Topic> topics = new ArrayList<>(Arrays.asList(
//        new Topic("Spring", "Spring Framework", "Spring Description"),
//                new Topic("Java", "Java", "Java Description"),
//                new Topic("Javascript", "JS", "JS Description")
//        ));

    public List<Topic> getAllTopics() throws NullPointerException{
        //return topics;
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(String id) {
//        return topics.stream().filter(n-> n.getId().equals(id)).findFirst().get();
//    return topicRepository.findOne(id);
        return topicRepository.findById(id);
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic) {
//        for(int i=0; i< topics.size(); i++){
//            Topic t = topics.get(i);
//            if(t.getId().equals(id)){
//                topics.set(i, topic);
//                return;
//            }
//        }
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
//        topics.removeIf(t-> t.getId().equals(id));
        topicRepository.deleteById(id);
    }
}
