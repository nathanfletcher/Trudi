package com.ketecode.trudi;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Route circle_Lapaz;
    List<Marker> allBusStops = new ArrayList<>();

    //This class deals with GPS Tracking
    public GPSTracker gpsTracker;
    LatLng user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        gpsTracker = new GPSTracker(getApplicationContext());
        circle_Lapaz = new Route();

        Toast.makeText(this,"Get to the nearest bus stop shown on the map",Toast.LENGTH_LONG);
        loadRouteManually();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        /**
         * Getting Location of User
         * */
        if(gpsTracker.canGetLocation()){
            user = new LatLng(gpsTracker.getLatitude(),gpsTracker.getLongitude());
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);

        //putting usr on map
        mMap.addMarker(new MarkerOptions().position(user)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.user));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));

        plotBusStops(circle_Lapaz);

    }

    /**
     * This loads the routes manually using the objects created i.e Route and Stop
     * I'm putting it here so that the onCreate method won't be bloated
     * the route object you're adding, it should be created as public in the class first
     * */
    public void loadRouteManually(){
        circle_Lapaz.setName("Circle to Lapaz");
        circle_Lapaz.busStops.add(new Stop("Terminal Circle", 5.56932, -0.215881));
        circle_Lapaz.busStops.add(new Stop("Car pack", 5.59145, -0.219412));
        circle_Lapaz.busStops.add(new Stop("New Fadama", 5.59966, -0.237133));
    }

    /**
     * This method is optimised to take any route and plot all it's bus stops on the map
     * @param theRoute The route to be plotted on the map.
     * */
    public void plotBusStops(Route theRoute){
        for(int i=0; i<theRoute.getBusStops().size(); i++){

            Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(theRoute.getBusStops().get(i).getLatitude(), theRoute.getBusStops().get(i).getLongitude()))
                    .title(theRoute.getBusStops().get(i).getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.busiconbeta)));

            allBusStops.add(marker);
        }
    }
}
