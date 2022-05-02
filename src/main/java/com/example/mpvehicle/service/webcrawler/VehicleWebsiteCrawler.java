package com.example.mpvehicle.service.webcrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;

@Service
public class VehicleWebsiteCrawler implements WebsiteCrawler {

    @Override
    public Connection getURLConnection(String url) {
        return Jsoup.connect(url);
    }

    @Override
    public Connection.Response getURLResponse(String URL, int Timeout, Connection.Method methodType) throws IOException {
        return getURLConnection(URL) //
                .timeout(5000)
                .method(Connection.Method.GET)
                .execute();
    }

    @Override
    public String[] getDesiredResponse(Connection.Response resp, String... vehicleFilterParams) throws IOException {
        String vehicleId;
        if (vehicleFilterParams != null && vehicleFilterParams.length != 0) {
            vehicleId = vehicleFilterParams[0];
        }
        // * Find the form
        Document responseDocument = resp.parse();
        Element potentialForm = responseDocument.select("form#aspnetForm").first();
        checkElement("form element", potentialForm);
        FormElement form = (FormElement) potentialForm;
        /*
         * // * Fill in the form and submit it // ** Search Type Element
         * radioButtonListSearchType =
         * form.select("[name$=RadioButtonList_SearchType]").first();
         * checkElement("search type radio button list", radioButtonListSearchType);
         * radioButtonListSearchType.attr("checked", "checked");
         */

        // ** Name search
        Element textBoxNameSearch = form.select("[name$=ctl00$ContentPlaceHolder1$txtRegNo]").first();
        checkElement("name search text box", textBoxNameSearch);
        textBoxNameSearch.val();

        // ** Submit the form
        Document searchResults = form.submit().cookies(resp.cookies()).post();

        // * Extract results (entity numbers in this sample code)
//		for (Element entityNumber : searchResults.select("table[id$=SearchResults_Corp] > tbody > tr > td:first-of-type:not(td[colspan=5])")) {
        // System.out.println(entityNumber.text());
        // }

        Elements tds = searchResults.select(".GridItem");
        String[] s = new String[15];
        int i = 0;
        for (Element td : tds.select("td")) {
            s[i] = td.select("td").text();
            System.out.println(s[i] + "" + i);
            i++;
        }
        /*
         * String number = s[2]; String name = s[5]; String city = s[6]; String data =
         * s[7]; String Colour = s[11]; String type = s[12]; String bikemaker = s[13];
         * String model = s[14];
         */    //	System.out.println(number + name + city + data + Colour + type + bikemaker + model);

        return s;
    }

    private void checkElement(String name, Element elem) {
        if (elem == null) {
            throw new RuntimeException("Unable to find " + name);
        }
    }
}
