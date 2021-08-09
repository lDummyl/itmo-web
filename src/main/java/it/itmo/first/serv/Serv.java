package it.itmo.first.serv;

import it.itmo.first.beans.Face;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Serv {


    Face face;


    @PostConstruct
    public void init(){
        System.out.println(face);
    }

    public void setFace(Face face) {
        this.face = face;
    }
}
