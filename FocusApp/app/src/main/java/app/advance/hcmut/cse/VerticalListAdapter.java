package app.advance.hcmut.cse;

import android.content.Context;
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


    public VerticalListAdapter(Context c, List<MediaModel> list) {
        this.context = c;
        this.elements = list;
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
        // implementation 'com.squareup.picasso:picasso:2.71828'

        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean b){
                holder.itemView.setSelected(b);
                if(b){
                    holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                } else {
                    holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lb_tv_white));
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
