package com.technologies.gimick.stories.adapters;

/*
This is custom class for swipe or flip feature. This class is used for flipping purpose of user stories

*/

import android.app.Activity;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.cocosw.bottomsheet.BottomSheet;
import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.databinding.SwipeRowBinding;
import com.technologies.gimick.stories.models.StoriesDto;
import com.technologies.gimick.stories.social.FacebookSharing;
import com.technologies.gimick.stories.social.TwitterSharing;
import com.technologies.gimick.stories.utils.Constants;
import com.technologies.gimick.stories.utils.LoginPrefrences;
import com.technologies.gimick.stories.utils.UiAlertDialog;

import java.util.ArrayList;


public class SwipeStoriesAdapter extends BaseAdapter {
    private final ArrayList<StoriesDto> data;
    private final Activity activity;
    SwipeRowBinding binding;
    View v;

    public SwipeStoriesAdapter(Activity activity, ArrayList<StoriesDto> data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public StoriesDto getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        v = convertView;
        if (convertView == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_row, null);
            binding = DataBindingUtil.bind(v);
        }
        binding.setStoryDto(getItem(position));
        binding.fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!new LoginPrefrences(activity).getLoginFlag()) {
                    binding.fabMenu.toggle();
                    UiAlertDialog.showAltertDialogForLogin(activity, parent.getContext(), "Login", "Please Login");
                } else {
                    binding.fabMenu.toggle();
                    Toast.makeText(v.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!new LoginPrefrences(activity).getLoginFlag()) {
                    binding.fabMenu.toggle();
                    UiAlertDialog.showAltertDialogForLogin(activity, parent.getContext(), "Login", "Please Login");
                } else {
                    binding.fabMenu.toggle();
                    showBottomSheet(getItem(position));
                }
            }
        });
        return v;
    }

    private void showBottomSheet(final StoriesDto item) {
        new BottomSheet.Builder(activity).title("Share Story on Social Network").sheet(R.menu.bottom_menu).listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case R.id.fb:
                        new FacebookSharing(activity).sharePost(Constants.IMAGE_URL + item.image_url, item.title, item.description);
                        break;
                    case R.id.twitter:
                        new TwitterSharing(activity.getApplicationContext()).shareTweet(item.title, item.description);
                        break;
                }
            }
        }).show();
    }
}
