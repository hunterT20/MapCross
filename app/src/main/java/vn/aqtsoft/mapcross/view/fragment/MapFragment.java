package vn.aqtsoft.mapcross.view.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import vn.aqtsoft.mapcross.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    private GoogleMap googleMap;
    private double longitudeCurrent, latitudeCurrent;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    @BindView(R.id.mapView)
    MapView mMapView;

    public MapFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ButterKnife.bind(this,view);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initGoogleMap();


        return view;
    }

    private void getCurrentLocation() {
        if(getActivity() == null){
            return;
        }
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5, locationListener);
        Location locationCurrent = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        if (locationCurrent != null){
            longitudeCurrent = locationCurrent.getLongitude();
            latitudeCurrent = locationCurrent.getLatitude();
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitudeCurrent = location.getLongitude();
            latitudeCurrent = location.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initGoogleMap(){
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                if(getActivity() == null) return;
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Yêu cầu")
                                .setContentText("Bạn cần cấp quyền truy cập vị trí để map có thể hoạt động!")
                                .setConfirmText("Bật quyền truy cập")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                                REQUEST_CODE_ASK_PERMISSIONS);
                                        sweetAlertDialog.dismiss();
                                    }
                                })
                                .setCancelText("Không")
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                })
                                .show();
                        return;
                    }
                    requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_ASK_PERMISSIONS);
                    return;
                }
                googleMap = mMap;
                googleMap.setMyLocationEnabled(true);
                getCurrentLocation();

                final LatLng start = new LatLng(latitudeCurrent, longitudeCurrent);
                googleMap.addMarker(new MarkerOptions().position(start).title("Vị trí hiện tại"));
                googleMap.addCircle(new CircleOptions()
                        .center(start)
                        .radius(500)
                        .strokeColor(Color.GREEN));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(start).zoom(16).tilt(45).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
