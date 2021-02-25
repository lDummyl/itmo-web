package it.itmo.first.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("child")
public class SomeChildBean extends SomeParentBean{

    String name = "Some child bean";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}