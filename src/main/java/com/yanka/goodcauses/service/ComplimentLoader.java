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
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

//    @Scheduled(fixedDelay = 1000)
    private void requestCompliment() {
        long existingEntityCount = complimentManager.getExistingEntityCount();
        if (existingEntityCount >= 10000) {
            return;
        }
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpPost request = new HttpPost("http://online-generators.ru/ajax.php");
            request.setHeader("Host", "online-generators.ru");
            request.setHeader("User-Agent", USER_AGENT);
            request.setHeader("Accept", "*/*");
            request.setHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
            request.setHeader("Accept-Charset", "utf-8");
            request.setHeader("Connection", "keep-alive");
            request.setHeader("Referer", "http://online-generators.ru/compliments");
            request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            List<NameValuePair> postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("sex", "0"));
            postParams.add(new BasicNameValuePair("type_compl", "0"));
            postParams.add(new BasicNameValuePair("processor", "compliments"));
            request.setEntity(new UrlEncodedFormEntity(postParams));

            HttpResponse response = httpClient.execute(request);
            String msg = EntityUtils.toString(response.getEntity());
            if (StringUtils.isBlank(msg)) {
                return;
            }
            String[] splitMsg = msg.split("##");
            if (splitMsg.length == 0) {
                return;
            }
            String content = splitMsg[0];
            if (StringUtils.isBlank(content) || content.length() > 64) {
                return;
            }
            Compliment compliment = new Compliment();
            compliment.setContent(content);
            complimentManager.save(compliment);
        } catch (IOException e) {
            logError(e);
        }
    }
}
