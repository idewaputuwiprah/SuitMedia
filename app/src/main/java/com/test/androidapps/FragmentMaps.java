package com.test.androidapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    private ArrayList<EventModel> models;
    ViewPager viewPager;
    MapAdapter mapAdapter;
    View view;
    MapView mapView;

    public FragmentMaps(ArrayList<EventModel> items){
        this.models = items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_maps, container, false);

        mapAdapter = new MapAdapter(getContext(), models);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(mapAdapter);
        viewPager.setPadding(50, 30, 50, 30);
        viewPager.setPageMargin(50);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                String name = models.get(position).getTitle();
//                Toast.makeText(getContext(),name+"",Toast.LENGTH_LONG).show();

                final double lat = models.get(position).getLat();
                final double longg = models.get(position).getLongitude();

                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        gMap = googleMap;
                        gMap.clear();

                        LatLng monas = new LatLng(lat, longg);
                        gMap.addMarker(new MarkerOptions().position(monas).title("Marker in Monas"));
                        gMap.moveCamera(CameraUpdateFactory.newLatLng(monas));
                    }
                });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;

                LatLng monas = new LatLng(-6.175339, 106.827279);
                gMap.addMarker(new MarkerOptions().position(monas).title("Marker in Monas"));
                gMap.moveCamera(CameraUpdateFactory.newLatLng(monas));
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
