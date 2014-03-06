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
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Component
public class ComplimentLoader extends LoggedClass {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final long MAX_COUNT = 7000;
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private ComplimentManager complimentManager;
    private AtomicLong counter;
    private ScheduledFuture<?> future;

    @PostConstruct
    public void init() {
        future = taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                requestCompliment();
            }
        }, new PeriodicTrigger(1000));
    }

    private void requestCompliment() {
        if (counter == null) {
            counter = new AtomicLong(complimentManager.getExistingEntityCount());
        }
        if (counter.getAndIncrement() >= MAX_COUNT) {
            logInfo("Target size reached");
            future.cancel(true);
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
