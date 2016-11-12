package in.getsimpler.simpleragentapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import in.getsimpler.simpleragentapp.VolleyCall.*;

public class TabbedMainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static String responsetodisplay = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View rootView = inflater.inflate(R.layout.fragment_tabbed_main, container, false);
            LinearLayout rlfragment_tabbed = (LinearLayout) rootView.findViewById(R.id.rlfragment_tabbed);
            View orders;
            Intent intent = getActivity().getIntent();
            responsetodisplay = intent.getStringExtra("response");
            OrdersClass ordersClass = new OrdersClass();
            //======================================GET THE RESPONSE IN A GSON OBJECT========================================
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject(responsetodisplay);
                Log.v("testjsonarray", jsonObject.toString());

                jsonArray = jsonObject.getJSONArray("result");
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1 = jsonArray.getJSONObject(0);
                Log.v("testjsonarray", jsonObject1.toString());

                Gson gson = new Gson();
                ordersClass = gson.fromJson(jsonObject1.toString(), OrdersClass.class);
            }catch (Exception e)
            {
                //=====================DISPLAY ERROR MESSAGE====================================
            }



            switch (getArguments().getInt(ARG_SECTION_NUMBER))
            {
                case 1:
                    //===================REMOVE FOR LOOP ONCE REAL CALL IS RECEIVED BY VOLLEY================================
                    for (int i=0;i<4;i++) {

                        orders = getLayoutInflater(savedInstanceState).inflate(R.layout.order_section_deliveries, null);
                        rlfragment_tabbed.addView(orders);


                        final TextView tvordersection_uncollapsed = (TextView) orders.findViewById(R.id.tvordersection_uncollapsed);
                        final TextView tvordersection_collapsed = (TextView) orders.findViewById(R.id.tvordersection_collapsed);
                        final ImageView ivdownarrow = (ImageView)orders.findViewById(R.id.ivdownarrow);
                        final ImageView ivuparrow = (ImageView)orders.findViewById(R.id.ivuparrow);
                        //==========================SET FONT==========================================
                        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"font/Roboto-Light.ttf");
                        tvordersection_collapsed.setTypeface(typeface);
                        tvordersection_uncollapsed.setTypeface(typeface);

                        tvordersection_uncollapsed.setText(ordersClass.FormatOrderDisplayStringUncollapsed());
                        tvordersection_collapsed.setText(ordersClass.FormatOrderDisplayStringCollapsed());
                        orders.setTag(i);
                        //=====================================HANDLE CLICK EVENTS================================================
                        ivdownarrow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.GONE);
                                tvordersection_collapsed.setVisibility(View.VISIBLE);
                                ivdownarrow.setVisibility(View.GONE);
                                ivuparrow.setVisibility(View.VISIBLE);
                            }
                        });
                        ivuparrow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.VISIBLE);
                                tvordersection_collapsed.setVisibility(View.GONE);
                                ivdownarrow.setVisibility(View.VISIBLE);
                                ivuparrow.setVisibility(View.GONE);
                            }
                        });
                        tvordersection_collapsed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.VISIBLE);
                                tvordersection_collapsed.setVisibility(View.GONE);
                                ivdownarrow.setVisibility(View.VISIBLE);
                                ivuparrow.setVisibility(View.GONE);
                            }
                        });
                        tvordersection_uncollapsed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.GONE);
                                tvordersection_collapsed.setVisibility(View.VISIBLE);
                                ivdownarrow.setVisibility(View.GONE);
                                ivuparrow.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    break;
                case 2:
                        // TextView tvordersection_uncollapsed=null, tvordersection_collapsed=null;
                    for (int i=0;i<2;i++) {
                        orders = getLayoutInflater(savedInstanceState).inflate(R.layout.order_section_deliveries, null);
                        rlfragment_tabbed.addView(orders);

                        final TextView tvordersection_uncollapsed = (TextView) orders.findViewById(R.id.tvordersection_uncollapsed);
                        final TextView tvordersection_collapsed = (TextView) orders.findViewById(R.id.tvordersection_collapsed);
                        final ImageView ivdownarrow = (ImageView)orders.findViewById(R.id.ivdownarrow);
                        final ImageView ivuparrow = (ImageView)orders.findViewById(R.id.ivuparrow);
                        //==========================SET FONT==========================================
                        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(),"font/Roboto-Light.ttf");
                        tvordersection_collapsed.setTypeface(typeface);
                        tvordersection_uncollapsed.setTypeface(typeface);

                        tvordersection_uncollapsed.setText(ordersClass.FormatOrderDisplayStringUncollapsed());
                        tvordersection_collapsed.setText(ordersClass.FormatOrderDisplayStringCollapsed());
                        orders.setTag(i);
                        //=====================================HANDLE CLICK EVENTS================================================
                        ivdownarrow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.GONE);
                                tvordersection_collapsed.setVisibility(View.VISIBLE);
                                ivdownarrow.setVisibility(View.GONE);
                                ivuparrow.setVisibility(View.VISIBLE);
                            }
                        });
                        ivuparrow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.VISIBLE);
                                tvordersection_collapsed.setVisibility(View.GONE);
                                ivdownarrow.setVisibility(View.VISIBLE);
                                ivuparrow.setVisibility(View.GONE);
                            }
                        });
                        tvordersection_collapsed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.VISIBLE);
                                tvordersection_collapsed.setVisibility(View.GONE);
                                ivdownarrow.setVisibility(View.VISIBLE);
                                ivuparrow.setVisibility(View.GONE);
                            }
                        });
                        tvordersection_uncollapsed.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvordersection_uncollapsed.setVisibility(View.GONE);
                                tvordersection_collapsed.setVisibility(View.VISIBLE);
                                ivdownarrow.setVisibility(View.GONE);
                                ivuparrow.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    break;
            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Pickups";
                case 1:
                    return "Deliveries";
                }
            return null;
        }
    }
}
