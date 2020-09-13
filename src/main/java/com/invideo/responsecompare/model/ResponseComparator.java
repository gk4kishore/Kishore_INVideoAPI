package com.invideo.responsecompare.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.invideo.responsecompare.IComparator;

public class ResponseComparator implements IComparator<Response, Response> {

	public boolean compare(Response x, Response y) {
		if (x.getData().equals(y.getData())) {
			System.out.print(" equals ");
			return true;

		} else {
			System.out.print(" not equals ");
			return false;
		}
	}

	public void getData(File file1, File file2) {

		FileInputStream fis1 = null, fis2 = null;
		BufferedReader reader1 = null, reader2 = null;

		try {
			fis1 = new FileInputStream(file1);
			fis2 = new FileInputStream(file2);
			reader1 = new BufferedReader(new InputStreamReader(fis1));
			reader2 = new BufferedReader(new InputStreamReader(fis2));
			String line = null, line2 = null;
			while ((line = reader1.readLine()) != null) {
				line2 = reader2.readLine();
				if (!line.trim().equals("") && !line2.trim().equals("")) {
					line = line.replaceAll("[^a-zA-Z0-9://.?=]", "");
					line2 = line2.replaceAll("[^a-zA-Z0-9://.?=]", "");

					System.out.print(line2);

					CloseableHttpClient httpClient = HttpClients.createDefault();

					HttpGet httpget = new HttpGet(line);

					HttpResponse httpresponse = httpClient.execute(httpget);

					String responseBody = EntityUtils.toString(httpresponse.getEntity(), StandardCharsets.UTF_8);

					HttpGet httpget2 = new HttpGet(line2);

					HttpResponse httpresponse2 = httpClient.execute(httpget2);

					String responseBody2 = EntityUtils.toString(httpresponse2.getEntity(), StandardCharsets.UTF_8);

					// System.out.println(responseBody);
					// System.out.println(responseBody2);

					Response r1 = new Response(responseBody);

					Response r2 = new Response(responseBody2);
					compare(r1, r2);

					System.out.println(line);

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (NullPointerException e) {
			System.out.println("URL not present");
		} catch (Exception i) {
			System.out.println("Unknown Exception");
		}

	}
}
