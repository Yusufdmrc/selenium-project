package backend;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.domsnapshot.model.StringIndex;
import org.openqa.selenium.devtools.v130.network.Network;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v130.network.model.RequestId;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static reporting.Logging.writeConsoleLog;

public class NetworkListener {
    public static DevTools createSessionAndEnable(RemoteWebDriver remoteWebDriver){
        if (remoteWebDriver instanceof FirefoxDriver){
            throw new SkipException("DevTools protocol is not supported in FirefoxDriver.");
        }
        WebDriver driver = new Augmenter().augment(remoteWebDriver);
        DevTools devTools=((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        return devTools;
    }

    public static ConcurrentMap<String,String> listenerResponseReceivedWithFilters(DevTools devTools, String requestName){
        final RequestId[] requestIds= new RequestId[1];
        final String[] regulatorSessionId = new String[1];
        final String[] regulatorTicketId = new String[1];
        final String[] gameSessionId = new String[1];
        final String[] playerId = new String[1];
        final String[] totalPages = new String[1];
        final String[] accountCode = new String[1];
        final String[] gameId = new String[1];
        final String[] licenseeId = new String[1];
        final String[] sessionToken = new String[1];
        AtomicInteger found = new AtomicInteger();
        final List<Map<String, Object>>[] contents = new List[]{new ArrayList()};

        ConcurrentMap<String,String> outputValues = new ConcurrentHashMap<>();
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String type = responseReceived.getType().toJson();
            writeConsoleLog("#### RESPONSE REQUEST ####");
            writeConsoleLog("SEND URL: " + responseReceived.getResponse().getUrl());
            writeConsoleLog("SEND METHOD: " + type);
            if (responseReceived.getResponse().getUrl().contains(requestName) && type.contains("XHR")) {
                writeConsoleLog("#### RESPONSE INFO ####");
                Gson gson = new Gson();
                Map<String, Object> jsonResponse = new HashMap<>();
                writeConsoleLog("Response Url: " + responseReceived.getResponse().getUrl());
                writeConsoleLog("Response Status: " + responseReceived.getResponse().getStatus());
                writeConsoleLog("Response Type: " + type);
                writeConsoleLog("Response Headers: ");
                responseReceived.getResponse().getHeaders().toJson().forEach((k, v) -> System.out.println((k + ":" + v)));
                requestIds[0] = responseReceived.getRequestId();
                String payload = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
                jsonResponse = gson.fromJson(payload, Map.class);
                writeConsoleLog("Response Body: \n" + payload + "\n",true);
                writeConsoleLog("-------------------------------------------------");
                if(StringUtils.containsIgnoreCase(requestName,"openSessionBuyin")){
                    regulatorSessionId[0] = jsonResponse.get("regulatorSessionId").toString();
                    regulatorTicketId[0] = jsonResponse.get("regulatorTicketId").toString();
                    gameSessionId[0] = jsonResponse.get("gameSessionId").toString();
                    Map<String, Object> sessionBalance = (Map<String, Object>) jsonResponse.get("sessionBalance");
                    playerId[0] = sessionBalance.get("playerId").toString();
                    outputValues.put("regulatorSessionId",regulatorSessionId[0]);
                    outputValues.put("regulatorTicketId",regulatorTicketId[0]);
                    outputValues.put("gameSessionId",gameSessionId[0]);
                    outputValues.put("playerId",playerId[0]);
                    writeConsoleLog("LISTENER - regulatorSessionId : "+outputValues.get("regulatorSessionId"));
                    writeConsoleLog("LISTENER - regulatorTicketId : "+outputValues.get("regulatorTicketId"));
                    writeConsoleLog("LISTENER - gameSessionId : "+outputValues.get("gameSessionId"));
                    writeConsoleLog("LISTENER - playerId : "+outputValues.get("playerId"));
                } else if(StringUtils.containsIgnoreCase(requestName,"wagerList")){
                    totalPages[0] = jsonResponse.get("totalPages").toString();
                    contents[0] = (List<Map<String, Object>>) jsonResponse.get("content");

                    writeConsoleLog(" PAYLOAD - TOTAL PAGES : "+totalPages[0]);
                    writeConsoleLog("PAYLOAD - NUMBER OF BETS FOR THIS PAGE : "+contents[0].size());
                    String urls = StringUtils.EMPTY;
                    for (Map<String, Object> content: contents[0]) {
                        urls +=content.get("detailLink").toString()+";";
                        writeConsoleLog("PAYLOAD - URL : "+ content.get("detailLink"));
                    }
                    outputValues.put("totalPages",totalPages[0]);
                    if(contents[0].size() > 0){
                        outputValues.put("detailLinks", urls.substring(0, urls.length()-1));
                    }else{
                        outputValues.put("detailLinks", StringUtils.EMPTY);
                    }
                }
            }
            if((StringUtils.endsWithIgnoreCase(responseReceived.getResponse().getUrl(),"schedina/")
                    && type.contains("XHR"))
                    || (StringUtils.endsWithIgnoreCase(responseReceived.getResponse().getUrl(),"sistema/")
                    && type.contains("XHR"))
                    || (StringUtils.endsWithIgnoreCase(responseReceived.getResponse().getUrl(),"quickpick/")
                    && type.contains("XHR"))
                    || (StringUtils.endsWithIgnoreCase(responseReceived.getResponse().getUrl(),"bacheca/")
                    && type.contains("XHR"))
                    || (StringUtils.containsIgnoreCase(responseReceived.getResponse().getUrl(),"sell/")
                    && type.contains("XHR"))
                    || (StringUtils.containsIgnoreCase(responseReceived.getResponse().getUrl(),"init")
                    && type.contains("XHR"))){
                writeConsoleLog("RECEIVED REQUEST URL: " + responseReceived.getResponse().getUrl());
                requestIds[0] = responseReceived.getRequestId();
                String payload = devTools.send(Network.getResponseBody(requestIds[0])).getBody();
                //jsonResponse = gson.fromJson(payload, Map.class);
                writeConsoleLog("Response Body: \n" + payload + "\n",true);
                writeConsoleLog("-------------------------------------------------");
                found.getAndIncrement();
                if(!StringUtils.containsIgnoreCase(payload,"\"codiceEsito\":\"-1\""))
                    outputValues.put("RESPONSE"+found, payload);
            }
        });
        return outputValues;
    }

    public static List<ConcurrentMap<String,String>> waitForListenerToRetrieveData(String requestName, ConcurrentMap<String, String> RequestDataReceived){
        boolean exit = false;
        long startTime = System.currentTimeMillis();

        List<ConcurrentMap<String,String>> localSaveDataReceived =  new CopyOnWriteArrayList<>();
        do{
            if(!RequestDataReceived.isEmpty()){
                localSaveDataReceived.add(RequestDataReceived);
                break;
            }
            if((System.currentTimeMillis() - startTime) > 35000){
                exit = true;
                if(StringUtils.equalsIgnoreCase(requestName,"schedina/") ||  StringUtils.equalsIgnoreCase(requestName,"sistema/")
                        ||  StringUtils.equalsIgnoreCase(requestName,"init")){
                    String projectName = System.getProperty("PROJECT");
                    if(projectName.contains("MILLI")){
                        throw new SkipException(" Skipping this scenario because the the response of " + requestName + " service is empty");
                    }else{
                        Assert.fail("The response of " + requestName + " service is empty");
                    }

                }
            }
        }
        while(!exit);


        return localSaveDataReceived;

    }


}
