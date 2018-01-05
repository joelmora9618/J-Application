package com.example.jmora.webservicesoap.Services;

import org.apache.http.entity.ByteArrayEntity;

/**
 * Created by JMora on 08/08/2017.
 */

public class BufferEntity extends ByteArrayEntity {
    public BufferEntity(byte[] b) {
        super(b);
    }
}
