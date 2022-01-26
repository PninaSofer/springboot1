package com.dev.utils;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.dev.Persist;
import com.dev.objects.SaleObject;

@Component
public class MessagesHandler extends TextWebSocketHandler {

    // private static List<WebSocketSession> sessionList = new
    // CopyOnWriteArrayList<>();
    private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<String, WebSocketSession>();
    private static Map<String, WebSocketSession> tokenMap = new ConcurrentHashMap<String, WebSocketSession>();

    private LocalDate now = LocalDate.now();

    @Autowired
    private Persist persist;

    @PostConstruct
    public void init() {
        new Thread(() -> {
            while (true) {
                try {
                    // sendNewNotification();
                    LocalDate currentDate = LocalDate.now();
                    if (!currentDate.equals(now)) {
                        this.now = LocalDate.now();
                        sendSaleMessages();
                    }
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        Map<String, String> map = Utils.splitQuery(session.getUri().getQuery());
        String token = map.get("token");

        tokenMap.put(token, session);
        sessionMap.put(session.getId(), session);

        System.out.println("afterConnectionEstablished " + session);
        sendSaleMessage(token, session);

    }

    private void sendSaleMessages() {
        for (Map.Entry<String, WebSocketSession> entry : tokenMap.entrySet()) {
            sendSaleMessage(entry.getKey(), entry.getValue());
        }
    }

    private void sendSaleMessage(String token, WebSocketSession session) {
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        if (persist != null) {
            Set<SaleObject> salesStrting = persist.getStartingSales(token);
            for (SaleObject sale : salesStrting) {
                JSONObject jsonObject = createMessage("New sale is starting:", sale.getDescription());
                jsonObjects.add(jsonObject);
            }
            Set<SaleObject> salesEnding = persist.getEndingSales(token);
            for (SaleObject sale : salesEnding) {
                JSONObject jsonObject = createMessage("Hurry! Sale is ending today:", sale.getDescription());
                jsonObjects.add(jsonObject);
            }
        }
        sendNewNotification(jsonObjects, session);
    }

    private JSONObject createMessage(String title, String text) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("message", text);
        return jsonObject;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println("handleTextMessage");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionMap.remove(session.getId());
        Iterator<Map.Entry<String, WebSocketSession>> it = tokenMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, WebSocketSession> e = it.next();
            if (e.getValue().equals(session)) {
                it.remove();
            }

        }

        System.out.println("afterConnectionClosed");
    }

    public void sendNewNotification() {
        for (WebSocketSession session : sessionMap.values()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("test", "test");
            try {
                session.sendMessage(new TextMessage(jsonObject.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendNewNotification(List<JSONObject> messages, WebSocketSession session) {

        try {
            session.sendMessage(new TextMessage(messages.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}