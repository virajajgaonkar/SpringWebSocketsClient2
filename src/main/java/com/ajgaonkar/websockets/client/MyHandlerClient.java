package com.ajgaonkar.websockets.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class MyHandlerClient implements WebSocketHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final java.net.URI uri;
	private final WebSocketClient client;
	private WebSocketSession session;

	public MyHandlerClient() throws URISyntaxException {
		client = new StandardWebSocketClient();
		uri = new URI("ws://localhost:8080/myHandler");
	}

	public void connect() throws ExecutionException, InterruptedException {
		session = client.doHandshake(this, new WebSocketHttpHeaders(), uri).get();
	}

	public void sendMessage(String msg) throws IOException {
		if(!session.isOpen()){
			return;
		}
		session.sendMessage(new TextMessage(msg));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		LOGGER.info("MyHandlerClient - afterConnectionEstablished");

	}

	@Override
	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
		LOGGER.info("MyHandlerClient - handleTextMessage, session = {}, message = {}", webSocketSession, webSocketMessage.getPayload());

	}

	@Override
	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		LOGGER.info("MyHandlerClient - handleTransportError, session = {}", webSocketSession, throwable);

	}

	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
		LOGGER.info("MyHandlerClient - afterConnectionClosed, session = {}, closeStatus = {}", webSocketSession, closeStatus);

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
