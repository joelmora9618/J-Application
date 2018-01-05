package com.example.jmora.webservicesoap.Models;

/**
 * Created by JMora on 09/10/2017.
 */

public class AppVersion {
    //Id, NumberVersion,StatusId, StatusDescription
    //[{"Id":1,"NumberVersion":"1.0.0","StatusId":2,"StatusDescription":"Error"}]

    public Long Id;
    public String NumberVersion;
    public int StatusId;
    public String StatusDescription;
}
