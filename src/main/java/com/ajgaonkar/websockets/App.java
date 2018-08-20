package com.ajgaonkar.websockets;

import com.ajgaonkar.websockets.client.BroadcastClient;
import com.ajgaonkar.websockets.client.MyHandlerClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 */
public class App {
	private final MyHandlerClient client;
	private final BroadcastClient broadcastClient;

	public App() throws URISyntaxException {
		client = new MyHandlerClient();
		broadcastClient = new BroadcastClient();
	}

	public void demo() throws ExecutionException, InterruptedException, IOException {
		client.connect();
		broadcastClient.connect();

		client.sendMessage("Hi This is Viraj Private!!");
		broadcastClient.sendMessage("Hi This is Viraj Public!!");

	}


	public static void main(String[] args) throws URISyntaxException, InterruptedException, ExecutionException, IOException {
		App app = new App();
		app.demo();
		new Scanner(System.in).nextLine(); // Don't close immediately.
	}
}
