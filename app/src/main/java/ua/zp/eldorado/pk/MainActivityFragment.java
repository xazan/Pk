package ua.zp.eldorado.pk;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    EventAdapter mEventAdapter;

    public static final String PK_PASS_KEY = "ua.zp.eldorado.pk.key";

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        FetchPk fetchPk = new FetchPk();
        fetchPk.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_events);
        final ArrayList<Event> listOfEvents = new ArrayList<Event>();
        mEventAdapter = new EventAdapter(getActivity(), listOfEvents);
        listView.setAdapter(mEventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = mEventAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailEvent.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(PK_PASS_KEY, event);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

//        mEventAdapter.clear();
//        mEventAdapter.add(new Event("2015-06-30", "$", "ПДВ", "Останній день сплати податку на додану вартість за травень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-30", "$", "Акциз", "Останній день сплати акцизного податку за травень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-22", "П", "ПДВ", "Останній день подання декларації податку на додану вартість за травень 2015 року платниками, у яких податковий період дорівнює календарному місяцю", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-22", "П", "Акциз", "Останній день подання декларації акцизного податку за квітень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-22", "$", "Акциз", "Останній день авансової сплати акцизного податку за травень 2015 року (доплати (у разі потреби) податкового зобов’язання з акцизного податку на тютюнові вироби, розрахованої з урахуванням авансових платежів, здійснених при придбанні марок акцизного податку)*", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-22", "П", "Газ", "Останній день подання декларації зі збору у вигляді цільової надбавки до діючого тарифу на природний газ для споживачів усіх форм власності за травень 2015 року.", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
//        mEventAdapter.add(new Event("2015-06-22", "П", "Единий внесок", "Подання звіту по єдиному внеску, нарахованому за попередній календарний місяць роботодавцями за найманих працівників.", "http://zakon4.rada.gov.ua/laws/show/2464-17"));
//        mEventAdapter.add(new Event("2015-06-22", "$", "Единий внесок", "Сплата єдиного внеску, нарахованого за попередній календарний місяць роботодавцями за найманих працівників (крім гірничих підприємств)", "http://zakon4.rada.gov.ua/laws/show/2464-17"));
        return rootView;
    }
    //Fetching data from parce.com for eventAdapter
    public class FetchPk extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            BufferedReader bufferedReader = null;
            StringBuffer stringBuffer = new StringBuffer("");

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet();
                URI uri = new URI("https://api.parse.com/1/classes/Event");
                httpGet.setURI(uri);
                httpGet.addHeader("X-Parse-Application-Id", "0Lk7D52brIMlH6sZgqWw1WZahGWSckzB9UBBFZNY");
                httpGet.addHeader("X-Parse-REST-API-Key", "fhlAop60CRCeHNh7iOlj8WGzv1cKz8lufHipnDvH");
                HttpResponse httpResponse = httpClient.execute(httpGet);

                InputStream inputStream = httpResponse.getEntity().getContent();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String readLine = bufferedReader.readLine();
                while (readLine != null) {
                    stringBuffer.append(readLine);
                    stringBuffer.append("\n");
                    readLine = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    if (jsonArray != null) {
                        mEventAdapter.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.d("test", "i= " + Integer.toString(i) + " JSONObject= " + jsonArray.getJSONObject(i).toString());
                            mEventAdapter.add(new Event(jsonArray.getJSONObject(i)));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
