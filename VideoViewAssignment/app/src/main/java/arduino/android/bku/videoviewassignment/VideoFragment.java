package arduino.android.bku.videoviewassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment{
    VideoView videoView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_video, container, false);

        videoView = view.findViewById(R.id.videoView);
        String path = getArguments().getString("link");
        videoView.setVideoPath(path);
        videoView.setMediaController(new MediaController(getActivity()));
        videoView.start();

        return view;
    }
}
