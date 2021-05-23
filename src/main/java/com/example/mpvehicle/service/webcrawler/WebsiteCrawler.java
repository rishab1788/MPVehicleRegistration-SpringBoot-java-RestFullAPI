package com.example.mpvehicle.service.webcrawler;

import org.jsoup.Connection;

import java.io.IOException;

public interface WebsiteCrawler {
    Connection getURLConnection(String URL);

    Connection.Response getURLResponse(String URL, int Timeout, Connection.Method methodType) throws IOException;

    String[] getDesiredResponse(Connection.Response resp, String... filterParams) throws IOException;
}
