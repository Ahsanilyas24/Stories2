package com.technologies.gimick.stories.social;

/*
This is class for sharing stories on facebook

 */

import android.app.Activity;
import android.net.Uri;

import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;


public class FacebookSharing {
    private final Activity activity;

    public FacebookSharing(Activity activity) {
        this.activity = activity;
    }

    /**
     * Facebook post sharing
     *
     * @param appUrl
     * @param title
     * @param contentDescp
     */
    public void sharePost(String appUrl, String title, String contentDescp) {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(appUrl))
                .setContentTitle(title)
                .setContentDescription(contentDescp)
                .build();
        ShareDialog.show(activity, content);
    }

    /**
     * For app invites
     *
     * @param appUrl
     * @param imageUrl
     */
    public void inviteForApp(String appUrl, String imageUrl) {
        if (AppInviteDialog.canShow()) {
            AppInviteContent content = new AppInviteContent.Builder()
                    .setApplinkUrl(appUrl)
                    .setPreviewImageUrl(imageUrl)
                    .build();
            AppInviteDialog.show(activity, content);
        }
    }
}
