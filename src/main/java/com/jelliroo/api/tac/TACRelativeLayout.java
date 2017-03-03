package com.jelliroo.api.tac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by roger on 3/3/2017.
 *
 */

public class TACRelativeLayout extends RelativeLayout {

    private TextView headingTextView, smallMessageTextView;
    private Button agreeButton;
    private ImageView logoImageView;

    private OnClickListener onTacClickedListener;

    public TACRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public TACRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TACRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        @SuppressLint("InflateParams") RelativeLayout tac = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.tac, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(tac, params);

        headingTextView = (TextView) findViewById(R.id.toc_heading);
        smallMessageTextView = (TextView) findViewById(R.id.toc_small_text_message);
        agreeButton = (Button) findViewById(R.id.toc_agree);
        logoImageView = (ImageView) findViewById(R.id.toc_app_logo);
    }

    public void setOnAgreeListener(OnClickListener onAgreeListener){
        if(onAgreeListener == null) return;
        agreeButton.setOnClickListener(onAgreeListener);
    }

    public void setOnTacClickedListener(OnClickListener onTacClickedListener){
        if(onTacClickedListener == null) return;
        this.onTacClickedListener = onTacClickedListener;
    }

    public void initViews(final Context context,
                          int headingResId,
                          int smallMessageResId,
                          int tacNameId,
                          int agreeButtonMessageResId,
                          int appLogoResId){

        String headingMessage;
        String smallMessage;
        String agreeButtonMessage;
        String tacName;

        try {
            headingMessage = context.getString(headingResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            headingMessage = getContext().getString(R.string.default_heading_message);

        }

        try {
            smallMessage = context.getString(smallMessageResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            smallMessage = getContext().getString(R.string.default_small_text_view_message_format);
        }

        try {
            agreeButtonMessage = context.getString(agreeButtonMessageResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            agreeButtonMessage = getContext().getString(R.string.default_agree_button_message);
        }

        try {
            tacName = context.getString(tacNameId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            tacName = getContext().getString(R.string.default_tac_name);
        }

        headingTextView.setText(headingMessage);

        SpannableString ss = new SpannableString(smallMessage + " " + tacName);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //click here
                if(onTacClickedListener!=null){
                    onTacClickedListener.onClick(textView);
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, smallMessage.length() + 1, smallMessage.length() + tacName.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        smallMessageTextView.setText(ss);
        smallMessageTextView.setMovementMethod(LinkMovementMethod.getInstance());
        smallMessageTextView.setHighlightColor(Color.TRANSPARENT);

        agreeButton.setText(agreeButtonMessage);

        try {
            logoImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), appLogoResId, null));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            logoImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_album_black_24dp, null));
        }
    }
}
