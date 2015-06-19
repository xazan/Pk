package ua.zp.eldorado.pk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Adapter for Event ListView
 * Created by xazan on 19.06.2015.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    private static class ViewHolder {
        TextView list_item_event_type;
        TextView list_item_event_name;
        TextView list_item_event_date_day;
        TextView list_item_event_date_month;
        TextView list_item_event_description;
        LinearLayout list_item_event_date_layout;
    }

    public EventAdapter(Context context, ArrayList<Event> resource) {
        super(context, 0, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_event, parent, false);
            viewHolder.list_item_event_type = (TextView) convertView.findViewById(R.id.list_item_event_type);
            viewHolder.list_item_event_name = (TextView) convertView.findViewById(R.id.list_item_event_name);
            viewHolder.list_item_event_description = (TextView) convertView.findViewById(R.id.list_item_event_description);
            viewHolder.list_item_event_date_day = (TextView) convertView.findViewById(R.id.list_item_event_date_day);
            viewHolder.list_item_event_date_month = (TextView) convertView.findViewById(R.id.list_item_event_date_month);
            viewHolder.list_item_event_date_layout = (LinearLayout) convertView.findViewById(R.id.list_item_event_date_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //TODO Change text to icon
        viewHolder.list_item_event_type.setText(event.getType());
        viewHolder.list_item_event_name.setText(event.getName());
        viewHolder.list_item_event_description.setText(event.getDescription().substring(0, 25).concat("..."));
        String today = new SimpleDateFormat("dd").format(new Date());
        String eventDate = event.getDate();
        String eventDay = eventDate.substring(8, 10);
        int delta = Integer.parseInt(eventDay) - Integer.parseInt(today) ;
        if (delta < 3 && delta > 0) {
            viewHolder.list_item_event_date_layout.setBackgroundResource(R.color.danger);
        } else if (delta < 7 && delta > 0) {
            viewHolder.list_item_event_date_layout.setBackgroundResource(R.color.warning);
        }
//        String eventMonth = ;
        viewHolder.list_item_event_date_day.setText(eventDay);
        viewHolder.list_item_event_date_month.setText(new DateFormatSymbols().getMonths()[Integer.parseInt(eventDate.substring(5, 7)) - 1]);
        return convertView;

    }
}
