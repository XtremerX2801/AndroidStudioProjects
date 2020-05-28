package app.advance.hcmut.cse;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    HorizontalGridView gridViewHome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridViewHome = view.findViewById(R.id.gridViewHome);

        List<MediaModel> aList = new ArrayList<>();
        MediaModel aMedia = new MediaModel();
        aMedia.setMediaTitle("Flower");
        aMedia.setMediaInfo("The pink flower");
        aMedia.setMediaThumbnail("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/colorful-of-dahlia-pink-flower-in-beautiful-garden-royalty-free-image-825886130-1554743243.jpg");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("The battle cats");
        aMedia.setMediaInfo("A game");
        aMedia.setMediaThumbnail("https://upload.wikimedia.org/wikipedia/en/b/b6/TBCATSLogo.png");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Facebook");
        aMedia.setMediaInfo("Social media");
        aMedia.setMediaThumbnail("https://www.facebook.com/images/fb_icon_325x325.png");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Watch");
        aMedia.setMediaInfo("G70812");
        aMedia.setMediaThumbnail("https://carnival1986.vn/wp-content/uploads/2018/05/g70802-204-212.jpg");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Window");
        aMedia.setMediaInfo("Window background");
        aMedia.setMediaThumbnail("https://www.desktopbackground.org/p/2013/07/06/602948_cool-windows-7-size-wallpaper-hq-backgrounds_1600x1000_h.jpg");
        aList.add(aMedia);

        VerticalListAdapter adapter = new VerticalListAdapter(getActivity(), aList);
        gridViewHome.setAdapter(adapter);

        return view;
    }

}
