package com.yamuna.crypto;

import com.yamuna.crypto.dao.Coins;
import com.yamuna.crypto.dao.Transactions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class CoinbaseApplicationTest {

    public static String BASE_URL = "http://localhost:3030";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void addCoin() {
        given().urlEncodingEnabled(false)
                .body(new Coins(1L, "BTC", 8000.0, "Bitcoin"))
                .contentType(ContentType.JSON)
                .post(BASE_URL+"/crypto/coins")
                .then()
                .statusCode(302);

        given().urlEncodingEnabled(false)
                .body(new Coins(2L, "ETH", 5000.0, "Etherium"))
                .contentType(ContentType.JSON)
                .post(BASE_URL+"/crypto/coins")
                .then()
                .statusCode(302);
    }


    @Test
    public void getAllCoins() {
        given().when().get(BASE_URL+"/crypto/coins").
                then().statusCode(200);
    }

    @Test
    public void updateCoin() {
        given().urlEncodingEnabled(false)
                .body(new Coins(1L, "BTC", 8000.0, "Bitcoin"))
                .contentType(ContentType.JSON)
                .put(BASE_URL+"/crypto/coins")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBuyCoin() {
        given().urlEncodingEnabled(false)
                .body(new Transactions(1L, "BTC", 5L, "BUY", 10L))
                .contentType(ContentType.JSON)
                .post(BASE_URL+"/crypto/buy")
                .then()
                .statusCode(302);
    }

    @Test
    public void testSellCoin() {
        given().urlEncodingEnabled(false)
                .body(new Transactions(1L, "BTC", 5L, "SELL", 8L))
                .contentType(ContentType.JSON)
                .post(BASE_URL+"/crypto/sell")
                .then()
                .statusCode(302);
    }

    @Test
    public void testUpdateCoinTransaction() {
        given().urlEncodingEnabled(false)
                .body(new Transactions(1L, "BTC", 6L, "SELL", 8L))
                .contentType(ContentType.JSON)
                .post(BASE_URL+"/crypto/update")
                .then()
                .statusCode(302);
    }

    @Test
    public void testCancelTransaction() {
        given().urlEncodingEnabled(false)
                .body(new Transactions(1L, "BTC", 6L, "SELL", 8L))
                .contentType(ContentType.JSON)
                .delete(BASE_URL+"/crypto/cancel")
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetAllTransactions() {
        given().when()
                .get(BASE_URL+"/crypto/transactions")
                .then()
                .statusCode(200);
    }

}