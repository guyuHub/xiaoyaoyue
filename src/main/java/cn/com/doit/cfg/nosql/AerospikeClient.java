package cn.com.doit.cfg.nosql;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;

//@ConfigurationProperties(prefix = "nosql.as")
public class AerospikeClient {
     private String url;
     //@ConfigurationProperties和@CrossOrigin整理
}
