package ua.zp.eldorado.pk;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Event model
 * Created by xazan on 19.06.2015.
 */
public class Event implements Parcelable {
    /**
     * Event date
     */
    private String date;
    /**
     * Event type:
     * report or payment
     */
    private String type;
    /**
     * Short event description
     */
    private String name;
    /**
     * Detail event description
     */
    private String description;
    /**
     * Href to Decree that the event originated
     */
    private String decree;
    /**
     * The penalty for failure to comply
     */
    private String penalty;
    /**
     * Href to helpful information to comply
     */
    private String regulations;

    public Event() {
    }

    public Event(String date, String type, String name, String description, String decree, String penalty, String regulations) {
        this.date = date;
        this.type = type;
        this.name = name;
        this.description = description;
        this.decree = decree;
        this.penalty = penalty;
        this.regulations = regulations;
    }

    public Event(String date, String type, String name, String description, String decree) {
        this.date = date;
        this.type = type;
        this.name = name;
        this.description = description;
        this.decree = decree;
    }

    public Event(JSONObject object) {
        try {
            this.date = object.getString("date");
            this.type = object.getString("type");
            this.name = object.getString("name");
            this.description = object.getString("description");
            this.decree = object.getString("decree");
            this.penalty = object.getString("penalty");
            this.regulations = object.getString("regulations");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public static ArrayList<Event> fromJson(JSONArray jsonArray) {
//        ArrayList<Event> events = new ArrayList<Event>();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                events.add(new Event(jsonArray.getJSONObject(i)));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        return events;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecree() {
        return decree;
    }

    public void setDecree(String decree) {
        this.decree = decree;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }

    public static final Parcelable.Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            Event mEvent = new Event();
            mEvent.date = source.readString();
            mEvent.type = source.readString();
            mEvent.name = source.readString();
            mEvent.description = source.readString();
            mEvent.decree = source.readString();
            mEvent.penalty = source.readString();
            mEvent.regulations = source.readString();
            return mEvent;
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(decree);
        dest.writeString(penalty);
        dest.writeString(regulations);
    }
}
