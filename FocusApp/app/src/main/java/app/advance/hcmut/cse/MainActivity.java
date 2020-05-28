package app.advance.hcmut.cse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener{

    private ImageButton btnVoice, btnHighlight, btnSearch, btnPlaylist,
                        btnHistory, btnFavorite, btnSetting;
    private TextView txtVoiceStatus, txtHighlightStatus, txtSearchStatus, txtPlaylistStatus,
                        txtHistoryStatus, txtFavoriteStatus, txtSettingStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVoice = findViewById(R.id.btnVoice);
        btnHighlight = findViewById(R.id.btnHighlight);
        btnSearch = findViewById(R.id.btnSearch);
        btnPlaylist = findViewById(R.id.btnPlaylist);
        btnHistory = findViewById(R.id.btnHistory);
        btnFavorite = findViewById(R.id.btnFavorite);
        btnSetting = findViewById(R.id.btnSetting);

        btnVoice.setOnFocusChangeListener(this);
        btnSetting.setOnFocusChangeListener(this);
        btnHighlight.setOnFocusChangeListener(this);
        btnSearch.setOnFocusChangeListener(this);
        btnPlaylist.setOnFocusChangeListener(this);
        btnHistory.setOnFocusChangeListener(this);
        btnFavorite.setOnFocusChangeListener(this);

        txtVoiceStatus = findViewById(R.id.txtVoiceStatus);
        txtSettingStatus = findViewById(R.id.txtSettingStatus);
        txtHighlightStatus = findViewById(R.id.txtHighlightStatus);
        txtSearchStatus = findViewById(R.id.txtSearchStatus);
        txtPlaylistStatus = findViewById(R.id.txtPlaylistStatus);
        txtHistoryStatus = findViewById(R.id.txtHistoryStatus);
        txtFavoriteStatus = findViewById(R.id.txtFavoriteStatus);

        selectFragment(0);
    }

    @Override
    public void onFocusChange(View v, boolean focused) {
        if (v.getId() == R.id.btnVoice) {
            if(focused) {
                txtVoiceStatus.setVisibility(View.VISIBLE);
                selectFragment(0);
            }
            else txtVoiceStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnHighlight) {
            if(focused) {
                txtHighlightStatus.setVisibility(View.VISIBLE);
                selectFragment(1);
            }
            else txtHighlightStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnSearch) {
            if(focused) {
                txtSearchStatus.setVisibility(View.VISIBLE);
                selectFragment(2);
            }
            else txtSearchStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnPlaylist) {
            if(focused) {
                txtPlaylistStatus.setVisibility(View.VISIBLE);
                selectFragment(3);
            }
            else txtPlaylistStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnHistory) {
            if(focused) {
                txtHistoryStatus.setVisibility(View.VISIBLE);
                selectFragment(4);
            }
            else txtHistoryStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnFavorite) {
            if(focused) {
                txtFavoriteStatus.setVisibility(View.VISIBLE);
                selectFragment(5);
            }
            else txtFavoriteStatus.setVisibility(View.INVISIBLE);
        }
        if (v.getId() == R.id.btnSetting) {
            if(focused) {
                txtSettingStatus.setVisibility(View.VISIBLE);
                selectFragment(6);
            }
            else txtSettingStatus.setVisibility(View.INVISIBLE);
        }
    }

    Fragment fragment = null;
    String fragmentTag = "";

    private void selectFragment(int position){
        Class fragmentClass = null;
        switch (position){
            case 0:
                fragmentClass = HomeFragment.class;
                fragmentTag = "HomeFragment";
                break;
            case 1:
                fragmentClass = HighlightFragment.class;
                fragmentTag = "HighlightFragment";
                break;
            case 2:
                fragmentClass = SearchFragment.class;
                fragmentTag = "SearchFragment";
                break;
            case 3:
                fragmentClass = PlaylistFragment.class;
                fragmentTag = "PlaylistFragment";
                break;
            case 4:
                fragmentClass = HistoryFragment.class;
                fragmentTag = "HistoryFragment";
                break;
            case 5:
                fragmentClass = FavoriteFragment.class;
                fragmentTag = "FavoriteFragment";
                break;
            case 6:
                fragmentClass = SettingFragment.class;
                fragmentTag = "SettingFragment";
                break;
            default:
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();

            Bundle bundle = new Bundle();

            bundle.putString("fragmentTag", fragmentTag);


            fragment.setArguments(bundle);
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            //Log.e(TAG, "selectFragment " + e.getMessage());
        }
    }
}
