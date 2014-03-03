package com.yanka.goodcauses.service;

import com.yanka.goodcauses.common.LoggedClass;
import com.yanka.goodcauses.model.Compliment;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Component
public class ComplimentLoader extends LoggedClass {

    private static final String USER_AGENT = "Mozilla/5.0";
    @Autowired
    private ComplimentManager complimentManager;

    @Scheduled(fixedDelay = 1000)
    private void requestCompliment() {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost request = new HttpPost("http://online-generators.ru/ajax.php");
            request.setHeader("Host", "online-generators.ru");
            request.setHeader("User-Agent", USER_AGENT);
            request.setHeader("Accept", "*/*");
            request.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
            request.setHeader("Connection", "keep-alive");
            request.setHeader("Referer", "http://online-generators.ru/compliments");
            request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            List<NameValuePair> postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("sex", "0"));
            postParams.add(new BasicNameValuePair("type_compl", "0"));
            postParams.add(new BasicNameValuePair("processor", "compliments"));
            request.setEntity(new UrlEncodedFormEntity(postParams));

            HttpResponse response = httpClient.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            String msg = result.toString();
            if (StringUtils.isBlank(msg)) {
                return;
            }
            String[] splitMsg = msg.split("##");
            if (splitMsg.length == 0) {
                return;
            }
            Compliment compliment = new Compliment();
            compliment.setContent(splitMsg[0]);
            complimentManager.save(compliment);
        } catch (IOException e) {
            logError(e);
        }
    }
}
