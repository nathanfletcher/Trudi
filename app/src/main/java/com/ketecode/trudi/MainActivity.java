package com.ketecode.trudi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public GoogleMap map;
    public GPSTracker gps;
    public Route circle_Lapaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);

                Snackbar.make(view, "Loading bus stop", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        gps = new GPSTracker(MainActivity.this);

        loadRouteManually();

    }

    /**
    * Function to get the users location. This will be plotted on the map */
    public void getUserLocation() {
        LatLng userLocation;
        //getting user location

        if(gps.canGetLocation()){
            userLocation = new LatLng(gps.getLatitude(),gps.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15));
            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)).position(userLocation).title("You").draggable(true));
            map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent ;

        if (id == R.id.nav_report) {
            intent = new Intent(this, ReportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pay) {

        }  else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.putExtra("Tell everyone !","Check out Trudi ! She's the best trotro mate EVER!!!"); //fill with url later

        } else if (id == R.id.nav_directions) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This loads the routes manually using the objects created i.e Route and Stop
     * I'm putting it here so that the onCreate method won't be bloated
     * the route object you're adding, it should be created as public in the class first
     * */
    public void loadRouteManually(){
        circle_Lapaz.setName("Circle to Lapaz");
        circle_Lapaz.busStops.add(new Stop("Terminal Circle", 5.56932, -0.215881));
        circle_Lapaz.busStops.add(new Stop("Car pack",5.59145,-0.219412));
        circle_Lapaz.busStops.add(new Stop("New Fadama",5.59966,-0.237133));

    }
}
