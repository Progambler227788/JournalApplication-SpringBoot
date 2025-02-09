package com.talha.journal.cache;

import com.talha.journal.entity.ConfigJournal;
import com.talha.journal.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Can I've same name of package and inside it same class name
// yes you can have same package name and same class name inside a package
@Component
public class AppCache {
    // we will load our app cache config keys here to reduce latency time (req time, req process, req receive time)
    @Autowired
    ConfigRepository configRepository;

   public Map<String,String> APP_CACHE;

   @PostConstruct
   public void init(){

       APP_CACHE = new HashMap<>();
       List<ConfigJournal> configJournalList = configRepository.findAll();

       for (ConfigJournal cf : configJournalList){
           APP_CACHE.put(cf.getKey(), cf.getValue());
       }
   }


}
