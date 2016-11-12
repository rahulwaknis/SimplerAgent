package in.getsimpler.simpleragentapp;

import android.text.Html;
import android.text.Spanned;

/**
 * Created by Rahul on 11/1/2016.
 */
public class OrdersClass {

    public String jobid, id, requestid, location, address, pickuptime, username, phonenumber;

    public OrdersClass(){
        jobid = "";
        id = "";
        requestid = "";
        location = "";
        address = "";
        pickuptime = "";
        username = "";
        phonenumber = "";
    }
    public Spanned FormatOrderDisplayStringUncollapsed()
    {
        Spanned spanned = null;
        spanned = Html.fromHtml("<b>Job id : "+this.jobid+"<br><b>Location : "+this.location+"<br><b>Pick-up time : "+this.pickuptime);

        return spanned  ;
    }
    public Spanned FormatOrderDisplayStringCollapsed()
    {
        Spanned spanned = null;
        spanned = Html.fromHtml("<b>Job id : "+this.jobid+"<br><b>Location : "+this.location+"<br><b>Pick-up time : "+this.pickuptime+
                "<br><b>Address : "+this.address+"<br><b>Phone : "+this.phonenumber+"<br><b>Username : "+this.username);

        return spanned  ;
    }
}
