package arduino.android.bku.videoviewassignment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VerticalListAdapter extends RecyclerView.Adapter<VerticalListAdapter.ViewHolder>{

    private Context context;

    private List<MediaModel> elements;
    FragmentManager fragmentManager;

    public VerticalListAdapter(Context c, List<MediaModel> list, FragmentManager fragmentManager) {
        this.context = c;
        this.elements = list;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.layout_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textName.setText(elements.get(position).getMediaTitle());
        holder.textInfo.setText(elements.get(position).getMediaInfo());

        Picasso.get()
                .load(elements.get(position).getMediaThumbnail())
                .into(holder.imgThumbnail);

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean b){
                holder.itemView.setSelected(b);
                if(b){
                    holder.itemView.setBackgroundColor(Color.CYAN);
                } else {
                    holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(position);
                if (position != -1) {
                    Fragment fragment = null;
                    System.out.println(elements.get(position).getPath());
                    Class fragmentClass = VideoFragment.class;
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putString("link", elements.get(position).getPath());
                        fragment.setArguments(bundle);
                        System.out.println(elements.get(position).getPath());

                        fragmentManager.beginTransaction().replace(R.id.fullScreen, fragment).commitAllowingStateLoss();
                    } catch (InstantiationException | IllegalAccessException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        TextView textInfo;
        ImageView imgThumbnail;

        public ViewHolder(View view) {
            super(view);

            textName = view.findViewById(R.id.txt_media_title);
            textInfo = view.findViewById(R.id.txt_media_info);
            imgThumbnail = view.findViewById(R.id.img_media_thumbnail);
        }
    }

}
