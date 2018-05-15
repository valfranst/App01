package teanlabsvalfran.app01.Remoto;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Valfran on 01/03/2018.
 */

public class HttpPostResponse extends AsyncTask<String, Void, String> {


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(String... params) {

        RequestBody body = RequestBody.create(JSON, params[1]);

        /*Request.Builder builder = new Request.Builder();
        builder.url(params[0]);
        Request request = builder.build();*/

        Request request = new Request.Builder().url(params[0]).post(body).build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;


    }







}
