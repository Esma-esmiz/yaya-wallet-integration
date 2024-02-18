package com.yaya.yaya.wallet.Auth;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
@Service
public class yayaAPiService {
    private String baseUrl="https://yayawallet.com/api/en/";
    private String url;
    private final String API_Key = "key-test_493e9539-3765-493a-864d-1082e2636168";
    private final String API_Secret = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJhcGlfa2V5Ijoia2V5LXRlc3RfNDkzZTk1MzktMzc2NS00OTNhLTg2NGQt" +
            "MTA4MmUyNjM2MTY4Iiwic2VjcmV0IjoiMDUyOTNjMGU1MDlhOWE4ODRiMDVh" +
            "MWYwZjkzYjdiNjMzMmE1NDUwMSJ9.is7GgbMLZ_ZUT1He9DG1dtEs5CxfpkVlCco0Xo6mHQY";
    private serverTime timeStamp;
    private  String method;
    public serverTime getServertime(){
      WebClient.Builder builder = WebClient.builder();
      serverTime time= builder.build().get()
                      .uri(baseUrl+"time")
                      .retrieve().bodyToMono(serverTime.class)
                      .block();
      return  time;
  }

  public User finByUser(User user) throws NoSuchAlgorithmException, InvalidKeyException {
        url= baseUrl+"transaction/find-by-user";
      timeStamp=getServertime();
      method="GET";
      String path="api/en/transaction/find-by-user";

      WebClient.Builder builder = WebClient.builder();

      String hmacAlgorithm = "HmacSHA256";

      String signedMessage = hmacEncription(hmacAlgorithm, timeStamp.getTime()+method+path+user.toString(), API_Secret);


      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", "application/json");
      headers.add("YAYA-API-KEY", API_Key);
      headers.add("YAYA-API-TIMESTAMP", timeStamp.getTime());
      headers.add("YAYA-API-SIGN", signedMessage);

      serverTime time= builder.build().get()
              .uri(url)
              .headers(h->h.addAll(headers))
              .retrieve().bodyToMono(serverTime.class)
              .block();
       System.out.println("body="+user.toString());
        return user;
  }


    public static String hmacEncription(String algorithm, String data, String key) {
        String hmac = new HmacUtils(algorithm, key).hmacHex(data);
        return hmac;
    }

    public Flux<User> search(User user) throws NoSuchAlgorithmException, InvalidKeyException {
        url= baseUrl+"transaction/search";
        timeStamp=getServertime();
        method="POST";
        String path="api/en/transaction/search";

        WebClient builder = WebClient.create();
        String hmacAlgorithm = "HmacSHA256";
        JSONObject userJson = new JSONObject();
        userJson.put("query", user.getQuery());
        String signedMessage = hmacEncription(hmacAlgorithm, timeStamp.getTime()+method+path+userJson, API_Secret);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("YAYA-API-KEY", API_Key);
        headers.add("YAYA-API-TIMESTAMP", timeStamp.getTime());
        headers.add("YAYA-API-SIGN", signedMessage);
//System.out.println(userJson);
      User userResponse;
       return builder.post()
                  .uri(url)
                  .headers(h->h.addAll(headers))
                   .accept(MediaType.APPLICATION_JSON)
                  .bodyValue(userJson)
//                  .retrieve()
                   .exchangeToFlux(this::handleResponse);
//                   .subscribe();

    }


    private Flux<User> handleResponse(ClientResponse response) {

        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToFlux(User.class);
        }
        else if (response.statusCode().is4xxClientError()) {
            return Flux.error(new Error("user not found"));
        }
        else if (response.statusCode().is4xxClientError()) {
            return Flux.error(new Error("unauthorized error"));
        }
        else if (response.statusCode().is5xxServerError()) {
            // Handle server errors (e.g., 500 Internal Server Error)
            return Flux.error(new RuntimeException("Server error"));
        }
        else {
            // Handle other status codes as needed
            return Flux.error(new RuntimeException("Unexpected error"));
        }
    }
}
