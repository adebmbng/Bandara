package restart.com.bandara.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import restart.com.bandara.MyApplication;
import restart.com.bandara.R;
import restart.com.bandara.models.Profile;
import restart.com.bandara.presenters.profiles.ProfilePresenter;
import restart.com.bandara.presenters.profiles.ProfilePresenters;
import restart.com.bandara.presenters.profiles.ProfileView;
import restart.com.bandara.services.APIService;

/**
 * Created by lenovo on 2/7/2017.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileView{

    @BindView(R.id.img_profileUser)SelectableRoundedImageView ivProfile;
    @BindView(R.id.textViewName)TextView tvName;
    @BindView(R.id.textViewHP)TextView tvMsisdn;
    @BindView(R.id.textViewEmail)TextView tvEmail;
    @BindView(R.id.btn_editProfile)AppCompatButton btnEdit;

    private Context ctx;
    private ProfilePresenter presenters;

    @Inject
    SharedPreferences sp;

    @Inject
    APIService api;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_profile);
        ((MyApplication) getApplication()).getDc().inject(this);
        ctx = this;
        ButterKnife.bind(this);

        presenters = new ProfilePresenters(this, api, sp);
    }

    @OnClick(R.id.btn_editProfile)
    void gotoEdit(){
        Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void setProfile(Profile p) {
        tvName.setText(p.getName());
        tvEmail.setText(p.getEmail());
        tvMsisdn.setText(p.getMsisd());
        Glide.with(this).load(p.getImgUrl()).into(ivProfile);
    }

    @Override
    public void onFailed(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.profile_not_found))
                .setPositiveButton("Kembali", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        dialog.show();
    }

    @Override
    public void setPresenter(ProfilePresenter presenter) {
        if(presenter!=null){
            presenters = presenter;
        } else {
            presenters = new ProfilePresenters(this, api, sp);
        }
    }
}
