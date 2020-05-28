package arduino.android.bku.videoviewassignment;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class ComedyFragment extends Fragment{
    HorizontalGridView gridViewComedy;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comedy, container, false);

        gridViewComedy = view.findViewById(R.id.gridViewComedy);

        final List<MediaModel> aList = new ArrayList<>();
        MediaModel aMedia = new MediaModel();
        aMedia.setMediaTitle("BigBuckBunny");
        aMedia.setMediaInfo("A big bunny short film");
        aMedia.setMediaThumbnail("https://i.pinimg.com/originals/ee/29/4f/ee294fa3c5bcb26bfed60c79662f3b16.jpg");
        aMedia.setPath("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Elephants Dream");
        aMedia.setMediaInfo("A short movie");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.elephants_dream);
        aMedia.setPath("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Minions");
        aMedia.setMediaInfo("Happy birthday from cute minions");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.bananaa);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.banana);
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("BigBuckBunny");
        aMedia.setMediaInfo("A big bunny short film");
        aMedia.setMediaThumbnail("https://i.pinimg.com/originals/ee/29/4f/ee294fa3c5bcb26bfed60c79662f3b16.jpg");
        aMedia.setPath("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Pacific Rim 2");
        aMedia.setMediaInfo("Maybe not comedy film!");
        aMedia.setMediaThumbnail("https://kenh14cdn.com/thumb_w/640/2018/1/25/photo1516817792603-1516817792603903289271.jpg");
        aMedia.setPath("https://ubc.sgp1.cdn.digitaloceanspaces.com/npnlab_files/HDONLINE/movie1.mp4");
        aList.add(aMedia);

        fragmentManager = getFragmentManager();
        VerticalListAdapter adapter = new VerticalListAdapter(getActivity(), aList, fragmentManager);
        gridViewComedy.setAdapter(adapter);

        return view;
    }
}