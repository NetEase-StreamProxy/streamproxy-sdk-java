package com.netease.cloud.test;

import com.netease.cloud.client.StreamProxyClient;
import com.netease.cloud.util.json.JSONArray;
import com.netease.cloud.util.json.JSONObject;

public class StreamProxySample {

    private static String accessKey = "4ed7b756f11f4c1983ab897a48af91ec";
    private static String secretKey = "1ca9a1ba8ec24348a54a9f49101c69b7";

    public static void main(String[] args) throws Exception {

        String subscriptionName = "test201612121010.statetest-combloghzx";
        String positionType = "EARLIEST";
        StreamProxyClient client = null;

        try {
            // get subscription position
            client = new StreamProxyClient(accessKey, secretKey);
            String ret = client.getSubscriptionPosition(positionType, subscriptionName);
            System.out.println(ret);

            // get needed logs
            JSONObject retObject = new JSONObject(ret);
            String logsPosition = retObject.getJSONObject("result").getString("position");
            long limit = 1;
            String logs = client.getLogs(logsPosition, limit, subscriptionName);
            System.out.println(logs);

            // cal number of needs logs
            JSONObject logsObject = new JSONObject(logs);
            JSONArray subscription_logs =
                    logsObject.getJSONObject("result").getJSONArray("subscription_logs");
            System.out.println(subscription_logs.length());
        } catch (Exception e) {
            System.out.println("Execute error " + e.getMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }

    }

}
