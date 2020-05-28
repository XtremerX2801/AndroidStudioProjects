package arduino.android.bku.videoviewassignment;

import android.os.Bundle;
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

public class ActionFragment extends Fragment {
    HorizontalGridView gridViewAction;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);

        gridViewAction = view.findViewById(R.id.gridViewAction);

        List<MediaModel> aList = new ArrayList<>();
        MediaModel aMedia = new MediaModel();
        aMedia.setMediaTitle("Avenger vs Thanos");
        aMedia.setMediaInfo("Captain, Iron Man and Thor vs Thanos");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.vs_thanos);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.avenger);
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Kingsman Final Fight");
        aMedia.setMediaInfo("Eggsy vs Gazelle");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.eggsy);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.kingsman);
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Thor - God of Thunder");
        aMedia.setMediaInfo("God of Thunder true power!");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.thor_ragnarok);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.thor);
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Wonder Woman");
        aMedia.setMediaInfo("Story of a warrior");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.wonder_woman);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.ww);
        aList.add(aMedia);

        aMedia = new MediaModel();
        aMedia.setMediaTitle("Harry Porter vs Voldemort");
        aMedia.setMediaInfo("Savior vs destroyer");
        aMedia.setMediaThumbnail("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.harry_porter);
        aMedia.setPath("android.resource://arduino.android.bku.videoviewassignment/" + R.raw.h_vs_v);
        aList.add(aMedia);

        fragmentManager = getFragmentManager();
        //Create Adapter
        VerticalListAdapter adapter = new VerticalListAdapter(getActivity(), aList, fragmentManager);
        gridViewAction.setAdapter(adapter);

        return view;
    }
}