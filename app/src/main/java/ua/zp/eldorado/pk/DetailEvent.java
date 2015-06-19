package ua.zp.eldorado.pk;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormatSymbols;


public class DetailEvent extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        TextView detail_event_name = (TextView) findViewById(R.id.detail_event_name);
        TextView detail_event_day = (TextView) findViewById(R.id.detail_event_day);
        TextView detail_event_month = (TextView) findViewById(R.id.detail_event_month);
        TextView detail_event_description = (TextView) findViewById(R.id.detail_event_description);
        TextView detail_event_decree = (TextView) findViewById(R.id.detail_event_decree);
        TextView detail_event_penalty = (TextView) findViewById(R.id.detail_event_penalty);
        TextView detail_event_regulations = (TextView) findViewById(R.id.detail_event_regulations);

        Event event = (Event) getIntent().getParcelableExtra(MainActivityFragment.PK_PASS_KEY);
        detail_event_name.setText(event.getName());
        detail_event_description.setText(event.getDescription());
        detail_event_decree.setText(Html.fromHtml("<a href=" + event.getDecree() + ">" + event.getDecree() + "</a>"));
        detail_event_decree.setMovementMethod(LinkMovementMethod.getInstance());
        if (event.getPenalty() != null) {
            detail_event_penalty.setText(event.getPenalty());
        }
        if (event.getRegulations() != null) {
            detail_event_regulations.setText(event.getRegulations());
        }
        detail_event_day.setText(event.getDate().substring(8, 10));
        detail_event_month.setText(new DateFormatSymbols().getMonths()[Integer.parseInt(event.getDate().substring(5, 7)) - 1]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
