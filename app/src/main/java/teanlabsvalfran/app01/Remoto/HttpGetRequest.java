package teanlabsvalfran.app01.Remoto;


import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Valfran on 14/01/2018.
 */

public class HttpGetRequest extends AsyncTask<String, Void, String> {



    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    OkHttpClient client = new OkHttpClient();

    @Override
    protected String doInBackground(String...params) {

        Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }







}
