package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Date;

public class Main {


    final static String hostname = "dns.o1j.me";

    public static void main(String[] args) {
        // only required for Java SE 5 and lower:
        //Security.setProperty("networkaddress.cache.ttl", "30");

        System.out.println(Security.getProperty("networkaddress.cache.ttl"));
        System.out.println(System.getProperty("networkaddress.cache.ttl"));
        System.out.println(Security.getProperty("networkaddress.cache.negative.ttl"));
        System.out.println(System.getProperty("networkaddress.cache.negative.ttl"));

        while(true) {
            int i = 0;
            try {
                makeRequest();
                InetAddress inetAddress = InetAddress.getLocalHost();
                System.out.println(new Date());
                inetAddress = InetAddress.getByName(hostname);
                displayStuff(hostname, inetAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5L*1000L);
            } catch(Exception ex) {}
            i++;
        }
    }

    public static void displayStuff(String whichHost, InetAddress inetAddress) {
        System.out.println("Which Host:" + whichHost);
        System.out.println("Canonical Host Name:" + inetAddress.getCanonicalHostName());
        System.out.println("Host Name:" + inetAddress.getHostName());
        System.out.println("Host Address:" + inetAddress.getHostAddress());
    }

    public static void makeRequest() {
        try {
            URL url = new URL("http://"+hostname+"/");
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            InputStreamReader ird = new InputStreamReader(is);
            BufferedReader rd = new BufferedReader(ird);
            String res;
            while((res = rd.readLine()) != null) {
                System.out.println(res);
                break;
            }
            rd.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
