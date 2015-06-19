package ua.zp.eldorado.pk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String PK_PASS_KEY = "ua.zp.eldorado.pk.key";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listview_events);
        ArrayList<Event> listOfEvents = new ArrayList<Event>();
        final EventAdapter eventAdapter = new EventAdapter(getActivity(), listOfEvents);
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = eventAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailEvent.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(PK_PASS_KEY, event);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
        //TODO Data for debug
        eventAdapter.clear();
        Date newDate;
        eventAdapter.add(new Event("2015-06-30", "$", "ПДВ", "Останній день сплати податку на додану вартість за травень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-30", "$", "Акциз", "Останній день сплати акцизного податку за травень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-22", "П", "ПДВ", "Останній день подання декларації податку на додану вартість за травень 2015 року платниками, у яких податковий період дорівнює календарному місяцю", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-22", "П", "Акциз", "Останній день подання декларації акцизного податку за квітень 2015 року", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-22", "$", "Акциз", "Останній день авансової сплати акцизного податку за травень 2015 року (доплати (у разі потреби) податкового зобов’язання з акцизного податку на тютюнові вироби, розрахованої з урахуванням авансових платежів, здійснених при придбанні марок акцизного податку)*", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-22", "П", "Газ", "Останній день подання декларації зі збору у вигляді цільової надбавки до діючого тарифу на природний газ для споживачів усіх форм власності за травень 2015 року.", "http://zakon4.rada.gov.ua/laws/show/2755-17/"));
        eventAdapter.add(new Event("2015-06-22", "П", "Единий внесок", "Подання звіту по єдиному внеску, нарахованому за попередній календарний місяць роботодавцями за найманих працівників.", "http://zakon4.rada.gov.ua/laws/show/2464-17"));
        eventAdapter.add(new Event("2015-06-22", "$", "Единий внесок", "Сплата єдиного внеску, нарахованого за попередній календарний місяць роботодавцями за найманих працівників (крім гірничих підприємств)", "http://zakon4.rada.gov.ua/laws/show/2464-17"));
        return rootView;
    }
}
