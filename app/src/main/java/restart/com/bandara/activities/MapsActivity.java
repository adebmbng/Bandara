package restart.com.bandara.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import restart.com.bandara.MyApplication;
import restart.com.bandara.R;
import restart.com.bandara.services.APIService;

/**
 * Created by Debam on 2/22/17.
 */

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener{

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private Context ctx;
    private int h;

    @Inject
    SharedPreferences sp;

    @Inject
    APIService api;

    SupportMapFragment mapFragment;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    Dialog searchDialog;
    BottomSheetBehavior bsb;

    @BindView(R.id.footer)
    FrameLayout footer;
    @BindView(R.id.vaConfirmation)
    ViewAnimator va;
    @BindView(R.id.tvNext)
    TextView tvNext;
    @BindView(R.id.llBook)
    LinearLayout llbook;
    @BindView(R.id.radioNow)
    RadioButton rNow;
    @BindView(R.id.radioFlightCode)
    RadioButton rFlightCode;
    @BindView(R.id.radioPickTime)
    RadioButton rPickTime;
    @BindView(R.id.h1)
    EditText h1;
    @BindView(R.id.h2)
    EditText h2;
    @BindView(R.id.m1)
    EditText m1;
    @BindView(R.id.m2)
    EditText m2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_maps);

        ctx = this;
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getDc().inject(this);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();

        va.setDisplayedChild(1);

        int width = View.MeasureSpec.makeMeasureSpec(llbook.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        llbook.measure(width, height);
        h = llbook.getMeasuredHeight();

        bsb = BottomSheetBehavior.from(footer);
        bsb.setPeekHeight(h);

        bsb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        llbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bsb.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else if (bsb.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
//        createDialog();
    }

    @OnClick(R.id.tvNext)
    void next() {
        int state = 0;
        if (va.getDisplayedChild() == 1) {

            if (rNow.isChecked()) {
                state = 0;
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Intent intent = new Intent(MapsActivity.this, ConfirmationActivity.class);
                                startActivity(intent);
                                va.setDisplayedChild(1);
                            }
                        }, 3000);
            } else if (rPickTime.isChecked()) {
                state = 2;
            } else if (rFlightCode.isChecked()) {
                state = 3;
            }

            va.setDisplayedChild(state);
        } else if (va.getDisplayedChild() != 0) {
            va.setDisplayedChild(0);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Intent intent = new Intent(MapsActivity.this, ConfirmationActivity.class);
                            startActivity(intent);
                            va.setDisplayedChild(1);
                        }
                    }, 3000);
        }
    }

    @Override
    public void onBackPressed() {
        if (va.getDisplayedChild() != 1) {
            va.setDisplayedChild(1);
        } else
            super.onBackPressed();
    }

    private void createDialog() {
        searchDialog = new Dialog(ctx);
        searchDialog.setContentView(R.layout.dialog_book);
        searchDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        searchDialog.show();

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("IDN")
                .build();


//        autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        autocompleteFragment.setFilter(typeFilter);
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                Log.i(TAG, "Place: " + place.getName());
//            }
//
//            @Override
//            public void onError(Status status) {
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                mMap.setMyLocationEnabled(true);

            }
        } else {
            mMap.setMyLocationEnabled(true);
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);

        onLocationChanged(location);


    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
    }
}
