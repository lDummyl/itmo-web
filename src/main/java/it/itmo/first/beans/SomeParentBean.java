package it.itmo.first.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("parent")
public class SomeParentBean {

    String name = "Some parent bean";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
