package com.example.appecommercephp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class sendnotifire extends AsyncTask<Void,Void,String> {
    private static final String SERVER_KEY = "AAAAaJfQYf8:APA91bFcy_LjDpGRE_b77aEmlceKcxlsn3-ZrNTX5NXddzPErPyQWlIxNEQJ2QlZzuyRoOPuv_Ic2_78P4dC4oJxMy8QSP9QOzLz3RQPy7RYReUNo_BWQt10_TR2iRjDCMO7eLW_3Gsv"; // Thay YOUR_SERVER_KEY bằng API Key của bạn

    String tokenB = "cDcsqckETbKsp8ilfLorG7:APA91bEQE1j-V4FvRq5K5l9NOM-bxV43zx8bLRG034sJPAM_pyTe7N7dtOiiDQD6VidRcODYaPU5KpwKDeJgPQoXDH12MMyGb2L3mBNvGSG4DqHALm0kRD-lTmhJkZOr-tzuwQY93vEt";
    String title = "Thông báo từ thiết bị A";
    String message = "Nội dung thông báo";

    @Override
    protected String doInBackground(Void... voids) {
        String result = "o";

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse( "application/json");
        JSONObject jsonNotif = new JSONObject();
        JSONObject wholeObj = new JSONObject();
        try {
            jsonNotif.put(  "title", tokenB);
            jsonNotif.put(  "body","Gui thong bao ne");
            wholeObj.put( "to", tokenB);
            wholeObj.put(  "notification", jsonNotif);
        } catch (JSONException e) {
            Log.d("lỗi rồi bạn ei", e.toString());
        }

        RequestBody rBody = RequestBody.create(mediaType, wholeObj.toString());
        Request request = new Request.Builder().url("https://fcm.googleapis.com/fcm/send")
                .post(rBody)
                .addHeader( "Authorization", "Bearer " + SERVER_KEY)
                .addHeader("Content-Type","application/json").build();

        try {
            Response response = client.newCall (request).execute();
        }catch (Exception e)
        {
            result = e.toString() ;
            Log.d("foshfosdfhosfis",e.toString());
        }
        return result ;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("Kết quả",s);
    }
}
