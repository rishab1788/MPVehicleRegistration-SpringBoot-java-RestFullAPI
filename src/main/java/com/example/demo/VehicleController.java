package com.example.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.Date;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;  


@RestController
public class VehicleController {
    @Autowired
	private VehicleService vehicalService;
	
	
	
	
	
	
	
	
	
	
	
	
	    @SuppressWarnings("restriction")
	@CrossOrigin(origins ="http://localhost:4200")
    @RequestMapping("/sendmail") // takes the parameter in which url it is used to
	public String sendmail() throws IOException {
		String url = "http://mis.mptransport.org/MPLogin/eSewa/VehicleSearch.aspx";

	//	send("rishab1788@gmail.com","9589852323","rishab1788@gmail.com","hey this is rishabh","How r u?");     
	
		
		
		
		
		
		try {
		String host ="smtp.gmail.com" ;
        String user = "rishab1788@gmail.com";
        String pass = "9589852322";
        String to = "rishab178@gmail.com";
        String from = "WeatherHTML";
        String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
        String messageText = "Your Is Test Email :";
        boolean sessionDebug = false;

        Properties props = System.getProperties();

        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");

        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Session mailSession = Session.getDefaultInstance(props, null);
        mailSession.setDebug(sessionDebug);
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] address = {new InternetAddress(to)};
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject); msg.setSentDate(new Date());
        msg.setText(messageText);

       Transport transport=mailSession.getTransport("smtp");
       transport.connect(host, user, pass);
       transport.sendMessage(msg, msg.getAllRecipients());
       transport.close();
       System.out.println("message send successfully");
    }catch(Exception ex)
    {
        System.out.println(ex);
    }

		
		
		
		
		
		
		
		
		
		return url;
	
    }

	
	
	
	
	
	
	
	
	
    @CrossOrigin(origins ="http://localhost:4200")
	@RequestMapping("/MIS/{id}") // takes the parameter in which url it is used to
	public VehicleInformation sayHi(@PathVariable String id) throws IOException {
		String url = "http://mis.mptransport.org/MPLogin/eSewa/VehicleSearch.aspx";
		Connection.Response resp = Jsoup.connect(url) //
				.timeout(5000) //
				.method(Connection.Method.GET) //
				.execute();
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
		textBoxNameSearch.val(id);

		// ** Submit the form
		Document searchResults = form.submit().cookies(resp.cookies()).post();

		// * Extract results (entity numbers in this sample code)
//		for (Element entityNumber : searchResults.select("table[id$=SearchResults_Corp] > tbody > tr > td:first-of-type:not(td[colspan=5])")) {
		// System.out.println(entityNumber.text());
		// }

		Elements tds = searchResults.select(".GridItem");
		String s[] = new String[15];
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
		 */	//	System.out.println(number + name + city + data + Colour + type + bikemaker + model);
     
		return vehicalService.GetVehicalInfo(s);
	}

	private void checkElement(String name, Element elem) {
		if (elem == null) {
			throw new RuntimeException("Unable to find " + name);
		}

	}

}
