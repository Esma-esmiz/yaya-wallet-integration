package com.yaya.yaya.wallet.Auth;

import com.yaya.yaya.wallet.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(path = "api/v1/transaction/")
public class Controller {
  @Autowired
  private yayaAPiService yayaAPiService;
    @GetMapping(path = "find-by-user")
    public ResponseEntity<Object> getTransactionByUser(@RequestBody User user) throws NoSuchAlgorithmException, InvalidKeyException {
        serverTime time= yayaAPiService.getServertime();
        User userFetched= yayaAPiService.finByUser(user);
        return ResponseHandler.generateResponse("", HttpStatus.OK, user.getQuery()+" "+time.getTime() );
    }

    @PostMapping(path = "search")
 public ResponseEntity<Object> searchTransaction(@RequestBody User user) throws NoSuchAlgorithmException, InvalidKeyException {
        Flux<User> userFetched= yayaAPiService.search(user);
        return ResponseHandler.generateResponse("", HttpStatus.OK, userFetched);

    }
}
